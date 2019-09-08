package com.bakalov.xmlproccesing.utils.implementations;

import com.bakalov.xmlproccesing.utils.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;

public class XmlParserImpl implements XmlParser {

  public XmlParserImpl() {
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T parseXml(Class<T> tClass, BufferedReader reader) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      return (T) unmarshaller.unmarshal(reader);
    } catch (JAXBException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public <T> void objectToXmlFile(T object, BufferedWriter writer) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
      Marshaller marshaller = jaxbContext.createMarshaller();

      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.displayName());

      marshaller.marshal(object, writer);

    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }
}
