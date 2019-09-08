package com.bakalov.xmlprocessing.configurations;

import com.bakalov.xmlprocessing.utils.FileIoUtil;
import com.bakalov.xmlprocessing.utils.ValidatorUtil;
import com.bakalov.xmlprocessing.utils.XmlParser;
import com.bakalov.xmlprocessing.utils.implementations.FileIoUtilImpl;
import com.bakalov.xmlprocessing.utils.implementations.ValidatorUtilImpl;
import com.bakalov.xmlprocessing.utils.implementations.XmlParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;


@Configuration
public class ProductShopConfiguration {

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

  @Bean
  public XmlParser xmlParser() {
    return new XmlParserImpl();
  }
}