package com.bakalov.gamestoredatabase.services.implementations;

import com.bakalov.gamestoredatabase.domains.entities.LoggedUser;
import com.bakalov.gamestoredatabase.domains.entities.Role;
import com.bakalov.gamestoredatabase.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

public class BaseService {
  protected static volatile LoggedUser loggedUser;

  protected final StringBuilder builder;
  protected final ModelMapper mapper;
  protected final Validator validator;
  protected final UserRepository userRepository;

  @Autowired
  protected BaseService(StringBuilder builder,
                        ModelMapper mapper,
                        Validator validator,
                        UserRepository userRepository) {
    this.builder = builder;
    this.mapper = mapper;
    this.validator = validator;
    this.userRepository = userRepository;
    loggedUser = new LoggedUser(0, "", "", "", Role.USER);
  }

  protected LoggedUser getLoggedUser() {
    return loggedUser;
  }

  protected void setLogInUser(Integer id, String fullName, String email, String password, Role role) {
    this.getLoggedUser().setId(id);
    this.getLoggedUser().setFullName(fullName);
    this.getLoggedUser().setEmail(email);
    this.getLoggedUser().setPassword(password);
    this.getLoggedUser().setRole(role);
  }

  protected void logOutLogInUser() {
    this.getLoggedUser().setId(0);
    this.getLoggedUser().setFullName("");
    this.getLoggedUser().setEmail("");
    this.getLoggedUser().setPassword("");
    this.getLoggedUser().setRole(Role.USER);
  }
}
