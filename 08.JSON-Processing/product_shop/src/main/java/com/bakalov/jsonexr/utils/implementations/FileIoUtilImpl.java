package com.bakalov.jsonexr.utils.implementations;

import com.bakalov.jsonexr.utils.FileIoUtil;

import java.io.*;

public class FileIoUtilImpl implements FileIoUtil {


  @Override
  public String readFileContent(String path) throws IOException {

    File file = new File(path);

    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

    StringBuilder builder = new StringBuilder();

    String line;

    while ((line = reader.readLine()) != null) {

      builder.append(line).append(System.lineSeparator());
    }

    return builder.toString().trim();
  }

  @Override
  public void writeFileContent(String content, String path) throws IOException {
    File file = new File(path);

    FileWriter writer = new FileWriter(file);

    writer.write(content);

    writer.close();
  }
}
