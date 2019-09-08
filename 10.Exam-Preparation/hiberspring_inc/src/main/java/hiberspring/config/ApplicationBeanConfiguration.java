package hiberspring.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import hiberspring.util.implementation.FileUtilImpl;
import hiberspring.util.implementation.ValidationUtilImpl;
import hiberspring.util.implementation.XmlParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

  @Bean
  public FileUtil fileUtil() {
    return new FileUtilImpl();
  }

  @Bean
  public Gson gson() {
    return new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();
  }

  @Bean
  public XmlParser xmlParser() {
    return new XmlParserImpl(fileUtil());
  }

  @Bean
  public ValidationUtil validationUtil() {
    return new ValidationUtilImpl();
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
