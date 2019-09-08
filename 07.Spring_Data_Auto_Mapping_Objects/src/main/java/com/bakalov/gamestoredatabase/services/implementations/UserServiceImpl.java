package com.bakalov.gamestoredatabase.services.implementations;

import com.bakalov.gamestoredatabase.domains.dtos.UserLoginDto;
import com.bakalov.gamestoredatabase.domains.dtos.UserRegisterDto;
import com.bakalov.gamestoredatabase.domains.entities.Role;
import com.bakalov.gamestoredatabase.domains.entities.User;
import com.bakalov.gamestoredatabase.repositories.UserRepository;
import com.bakalov.gamestoredatabase.services.UserService;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends BaseService implements UserService {

  @Autowired
  public UserServiceImpl(StringBuilder builder,
                         ModelMapper mapper,
                         Validator validator,
                         UserRepository userRepository) {
    super(builder, mapper, validator, userRepository);
  }

  @Override
  public String registerUser(UserRegisterDto userRegisterDto) {
    this.builder.setLength(0);

    User user = this.mapper.map(userRegisterDto, User.class);
    Set<ConstraintViolation<User>> violations = this.validator.validate(user);

    User isRegisteredUser = this.userRepository.getUserByEmail(user.getEmail()).orElse(null);

    if (isRegisteredUser != null) {
      return builder.append("User is already registered!").append(System.lineSeparator()).toString();
    }

    if (violations.size() > 0) {
      for (ConstraintViolation<User> violation : violations) {
        this.builder.append(violation).append(System.lineSeparator());
      }
    } else {

      if (this.userRepository.count() == 0) {
        user.setRole(Role.ADMIN);
      } else {
        user.setRole(Role.USER);
      }

      this.builder.append(String.format("%s was registered", user.getFullName())).append(System.lineSeparator());
      this.userRepository.saveAndFlush(user);
    }

    return builder.toString();
  }

  @Override
  public String loginUser(UserLoginDto userLoginDto) {
    this.builder.setLength(0);

    if ( !super.getLoggedUser().getEmail().isEmpty() && super.getLoggedUser() != null ) {
      return this.builder.append("User is already logged in.").append(System.lineSeparator()).toString();
    }

    User user = this.userRepository
            .getUserByEmail(userLoginDto.getEmail())
            .orElse(null);

    if (user == null) {
      return this.builder.append("Incorrect email!").append(System.lineSeparator()).toString();
    } else {
      if (!user.getPassword().equals(userLoginDto.getPassword())) {
        return this.builder.append("Incorrect password!").append(System.lineSeparator()).toString();
      }

      super.setLogInUser(user.getId(),user.getFullName(), user.getEmail(), user.getPassword(), user.getRole());

      this.builder.append(
              String.format("Successfully logged in %s",
                      user.getFullName()));
    }

    return this.builder.append(System.lineSeparator()).toString();
  }

  @Override
  public String logOut() {
    this.builder.setLength(0);

    if (super.getLoggedUser().getEmail().isEmpty()) {

      this.builder.append("Cannot log out. No user was logged in.");

    } else {

      this.userRepository
              .getUserByEmail(super.getLoggedUser().getEmail())
              .ifPresent(user ->
                      this.builder
                              .append(String.format("User %s successfully logged out", user.getFullName())));

      super.logOutLogInUser();
    }
    return this.builder.append(System.lineSeparator()).toString();
  }
}
