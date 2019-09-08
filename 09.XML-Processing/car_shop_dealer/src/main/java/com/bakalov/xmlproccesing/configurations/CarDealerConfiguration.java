package com.bakalov.xmlproccesing.configurations;

import com.bakalov.xmlproccesing.utils.FileIoUtil;
import com.bakalov.xmlproccesing.utils.ValidatorUtil;
import com.bakalov.xmlproccesing.utils.XmlParser;
import com.bakalov.xmlproccesing.utils.implementations.FileIoUtilImpl;
import com.bakalov.xmlproccesing.utils.implementations.ValidatorUtilImpl;
import com.bakalov.xmlproccesing.utils.implementations.XmlParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;


@Configuration
public class CarDealerConfiguration {

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
