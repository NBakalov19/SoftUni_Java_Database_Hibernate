package com.bakalov.xmlproccesing.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public interface FileIoUtil {

  BufferedReader readFileContent(String path) throws IOException;

  BufferedWriter writeFileContent(String path) throws IOException;
}
