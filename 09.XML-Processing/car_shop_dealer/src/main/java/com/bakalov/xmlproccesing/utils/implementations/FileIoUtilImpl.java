package com.bakalov.xmlproccesing.utils.implementations;

import com.bakalov.xmlproccesing.utils.FileIoUtil;

import java.io.*;


public class FileIoUtilImpl implements FileIoUtil {


  @Override
  public BufferedReader readFileContent(String path) throws IOException {

    return new BufferedReader(new FileReader(path));
  }

  @Override
  public BufferedWriter writeFileContent(String path) throws IOException {

    return new BufferedWriter((new FileWriter(path)));
  }
}