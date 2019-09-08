package com.bakalov.xmlproccesing.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public interface XmlParser {

  <T> T parseXml(Class<T> tClass, BufferedReader reader);

  <T> void objectToXmlFile(T object, BufferedWriter writer);
}
