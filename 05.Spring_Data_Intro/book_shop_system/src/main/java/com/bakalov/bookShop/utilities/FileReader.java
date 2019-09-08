package com.bakalov.bookShop.utilities;

import java.io.IOException;

public interface FileReader {

    String[] getFileContent(String path) throws IOException;
}
