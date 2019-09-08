package com.bakalov.gamestoredatabase.services;

import com.bakalov.gamestoredatabase.domains.dtos.UserLoginDto;
import com.bakalov.gamestoredatabase.domains.dtos.UserRegisterDto;


public interface UserService {
  String registerUser(UserRegisterDto userRegisterDto);

  String loginUser(UserLoginDto userLoginDto);

  String logOut();
}
