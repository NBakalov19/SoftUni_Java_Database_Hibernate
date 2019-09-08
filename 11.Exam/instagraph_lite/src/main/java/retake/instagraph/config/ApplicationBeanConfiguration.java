package retake.instagraph.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retake.instagraph.util.*;

@Configuration
public class ApplicationBeanConfiguration {

  @Bean
  public FileUtil fileUtil() {
    return new FileUtilImpl();
  }

  @Bean
  public Gson gson() {
    return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
  }

  @Bean
  public ValidationUtil validationUtil() {
    return new ValidationUtilImpl();
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public XmlParser xmlParser() {
    return new XmlParserImpl(fileUtil());
  }

}
