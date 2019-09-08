package com.bakalov.gamestoredatabase.configurations;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class GameStoreConfiguration {

  @Bean
  public BufferedReader reader() {
    return new BufferedReader(streamReader());
  }

  @Bean
  public InputStreamReader streamReader() {
    return new InputStreamReader(System.in);
  }

  @Bean
  public StringBuilder builder() {
    return new StringBuilder();
  }

  @Bean
  public ModelMapper mapper() {
    return new ModelMapper();
  }

  @Bean
  public Validator validator() {
    return Validation.buildDefaultValidatorFactory().getValidator();
  }
}
