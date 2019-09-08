package alararestaurant.util.implementations;

import alararestaurant.util.FileUtil;

import java.io.*;

public class FileUtilImpl implements FileUtil {
  @Override
  public String readFile(String filePath) throws IOException {
    File file = new File(filePath);

    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

    StringBuilder builder = new StringBuilder();

    String line;

    while ((line = reader.readLine()) != null) {

      builder.append(line).append(System.lineSeparator());
    }

    return builder.toString().trim();
  }
}
