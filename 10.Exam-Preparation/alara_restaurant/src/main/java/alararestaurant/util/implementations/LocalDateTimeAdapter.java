package alararestaurant.util.implementations;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {


  @Override
  public LocalDateTime unmarshal(String s) throws Exception {
    return LocalDateTime.parse(s);
  }

  @Override
  public String marshal(LocalDateTime dateTime) throws Exception {
    return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(dateTime);
  }
}
