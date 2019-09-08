package com.bakalov.jsonexr.utils;

import java.io.IOException;

public interface FileIoUtil {

  String readFileContent(String path) throws IOException;

  void writeFileContent(String content, String path) throws IOException;
}
