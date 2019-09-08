package com.bakalov.xmlprocessing.services.implementations;

import com.bakalov.xmlprocessing.domains.dtos.binds.UserSeedDto;
import com.bakalov.xmlprocessing.domains.dtos.binds.roots.UserSeedRootDto;
import com.bakalov.xmlprocessing.domains.dtos.views.*;
import com.bakalov.xmlprocessing.domains.entities.User;
import com.bakalov.xmlprocessing.repositories.UserRepository;
import com.bakalov.xmlprocessing.services.UserService;
import com.bakalov.xmlprocessing.utils.ValidatorUtil;
import com.bakalov.xmlprocessing.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;
  private final UserRepository userRepository;
  private final XmlParser xmlParser;

  @Autowired
  public UserServiceImpl(ValidatorUtil validator, ModelMapper mapper, UserRepository userRepository, XmlParser xmlParser) {
    this.validatorUtil = validator;
    this.mapper = mapper;
    this.userRepository = userRepository;
    this.xmlParser = xmlParser;
  }

  @Override
  public void seedUsers(BufferedReader reader) {
    UserSeedRootDto uncheckedUserSeedRootDto = this.xmlParser.parseXml(UserSeedRootDto.class, reader);

    UserSeedRootDto checkedUserSeedRootDto = new UserSeedRootDto();

    for (UserSeedDto userSeedDto : uncheckedUserSeedRootDto.getUserSeedDtos()) {
      if (!this.validatorUtil.isValid(userSeedDto)) {
        this.validatorUtil.violations(userSeedDto)
                .forEach(v -> System.out.println(v.getMessage()));
        continue;
      }
      checkedUserSeedRootDto.getUserSeedDtos().add(userSeedDto);
    }

    checkedUserSeedRootDto.getUserSeedDtos()
            .stream()
            .map(userSeedDto -> this.mapper.map(userSeedDto, User.class))
            .forEach(this.userRepository::saveAndFlush);
  }


  @Override
  public List<SuccessfullySellersDto> getSuccessfulSellers() {

    return this.userRepository
            .getAllBySoldProductsContainsProduct_Buyer()
            .stream()
            .map(user -> {
              SuccessfullySellersDto sellersDto =
                      this.mapper.map(user, SuccessfullySellersDto.class);

              sellersDto.setSaleDetailDto(
                      user.getSoldProducts()
                              .stream()
                              .filter(sale -> sale.getBuyer() != null)
                              .map(sale -> this.mapper.map(sale, SaleDetailsDto.class))
                              .collect(Collectors.toSet()));

              return sellersDto;
            })
            .collect(Collectors.toList());
  }

  @Override
  public UsersWithSalesListDto getSellsByUser() {
    List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users = this.userRepository
            .getAllBySoldProductsContainsProduct_Buyer()
            .stream()
            .map(user -> {
              UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto userDto =
                      this.mapper.map(user, UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto.class);

              SoldProductsDto soldProductsDto = new SoldProductsDto();

              soldProductsDto.setSoldProduct(user
                      .getSoldProducts()
                      .stream()
                      .filter(sale -> sale.getBuyer() != null)
                      .map(sale -> this.mapper.map(sale, ProductNameAndPriceDto.class))
                      .collect(Collectors.toSet()));

              soldProductsDto.setCount(soldProductsDto.getSoldProduct().size());

              userDto.setSoldProducts(soldProductsDto);

              return userDto;
            })
            .sorted((u1, u2) -> {
              int result = u2.getSoldProducts().getCount() - u1.getSoldProducts().getCount();
              if (result == 0) {
                result = u1.getLastName().compareTo(u2.getLastName());
              }
              return result;
            })
            .collect(Collectors.toList());

    final UsersWithSalesListDto usersWithSalesListDto = new UsersWithSalesListDto();
    usersWithSalesListDto.setSoldProducts(users);
    usersWithSalesListDto.setUserCount(users.size());

    return usersWithSalesListDto;
  }
}
