package com.bakalov.jsonexr.services.implementations;

import com.bakalov.jsonexr.domains.dto.*;
import com.bakalov.jsonexr.domains.entities.User;
import com.bakalov.jsonexr.repositories.UserRepository;
import com.bakalov.jsonexr.services.UserService;
import com.bakalov.jsonexr.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(ValidatorUtil validator, ModelMapper mapper, UserRepository userRepository) {
    this.validatorUtil = validator;
    this.mapper = mapper;
    this.userRepository = userRepository;
  }

  @Override
  public void seedUser(UserSeedDto[] userSeedDtos) {


    for (UserSeedDto userSeedDto : userSeedDtos) {
      if (!validatorUtil.isValid(userSeedDto)) {
        this.validatorUtil.violations(userSeedDto)
                .forEach(v -> System.out.println(v.getMessage()));

        continue;
      }
      User user = this.mapper.map(userSeedDto, User.class);
      this.userRepository.saveAndFlush(user);
    }
  }

  @Override
  public List<SuccessfullySellersDto> getSuccessfulSellers() {

    return this.userRepository
            .getAllBySoldProductsContainsProduct_Buyer()
            .stream()
            .map(user -> {
              SuccessfullySellersDto sellersDto =
                      this.mapper.map(user, SuccessfullySellersDto.class);

              sellersDto.setSoldProducts(
                      user.getSoldProducts()
                              .stream()
                              .filter(sale -> sale.getBuyer() != null)
                              .map(sale -> this.mapper.map(sale, ProductNamePriceAndBuyerNamesDto.class))
                              .collect(Collectors.toSet()));

              return sellersDto;
            })
            .collect(Collectors.toList());
  }

  @Override
  public UsersWithSalesListDto getSellsByUser() {
    final List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users = this.userRepository
            .getAllBySoldProductsContainsProduct_Buyer()
            .stream()
            .map(user -> {
              final UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto userDto =
                      this.mapper.map(user, UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto.class);

              final SoldProductsDto soldProductsDto = new SoldProductsDto();

              soldProductsDto.setSoldProducts(user
                      .getSoldProducts()
                      .stream()
                      .filter(sale -> sale.getBuyer() != null)
                      .map(sale -> this.mapper.map(sale, ProductNameAndPriceDto.class))
                      .collect(Collectors.toSet()));

              soldProductsDto.setCount(soldProductsDto.getSoldProducts().size());

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
    usersWithSalesListDto.setUsers(users);
    usersWithSalesListDto.setUsersCount(users.size());

    return usersWithSalesListDto;
  }
}
