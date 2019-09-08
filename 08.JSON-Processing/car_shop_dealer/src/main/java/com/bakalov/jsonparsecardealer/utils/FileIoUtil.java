package com.bakalov.jsonparsecardealer.utils;

import java.io.IOException;

public interface FileIoUtil {

  String readFileContent(String path) throws IOException;

  void writeFileContent(String content, String path) throws IOException;
}
