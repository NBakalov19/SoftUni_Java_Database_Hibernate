package com.bakalov.jsonparsecardealer.configurations;

import com.bakalov.jsonparsecardealer.utils.FileIoUtil;
import com.bakalov.jsonparsecardealer.utils.ValidatorUtil;
import com.bakalov.jsonparsecardealer.utils.implementations.FileIoUtilImpl;
import com.bakalov.jsonparsecardealer.utils.implementations.ValidatorUtilImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;


@Configuration
public class CarDealerConfiguration {

  @Bean
  public Gson gson() {
    return new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
            .setPrettyPrinting()
            .create();
  }

  @Bean
  public FileIoUtil fileUtil() {
    return new FileIoUtilImpl();
  }

  @Bean
  public Validator validator() {
    return Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Bean
  public ValidatorUtil validatorUtil() {
    return new ValidatorUtilImpl(validator());
  }

  @Bean
  public ModelMapper mapper() {
    return new ModelMapper();
  }
}
