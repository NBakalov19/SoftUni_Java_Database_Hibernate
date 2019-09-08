package app.ccb.config;

import app.ccb.utils.FileUtil;
import app.ccb.utils.ValidationUtil;
import app.ccb.utils.XmlParser;
import app.ccb.utils.impls.FileUtilImpl;
import app.ccb.utils.impls.ValidationUtilImpl;
import app.ccb.utils.impls.XmlParserImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
