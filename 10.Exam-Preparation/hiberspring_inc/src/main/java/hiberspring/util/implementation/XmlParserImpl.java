package hiberspring.util.implementation;

import hiberspring.util.FileUtil;
import hiberspring.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class XmlParserImpl implements XmlParser {
  @Autowired
  private final FileUtil fileUtil;

  @Autowired
  public XmlParserImpl(FileUtil fileUtil) {
    this.fileUtil = fileUtil;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <O> O parseXml(Class<O> objectClass, String filePath) throws FileNotFoundException {
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      return (O) unmarshaller.unmarshal(reader);
    } catch (JAXBException e) {
      e.printStackTrace();
    }
    return null;
  }
}
