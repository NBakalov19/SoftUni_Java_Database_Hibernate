package com.bakalov.bookShop.utilities.imlementations;

import com.bakalov.bookShop.utilities.FileReader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileReaderImpl implements FileReader {
    @Override
    public String[] getFileContent(String path) throws IOException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new java.io.FileReader(file));//try with String path

        List<String> authors = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null){
            authors.add(line);
        }

        return authors.stream()
                .filter(a -> !a.equals(""))
                .toArray(String[]::new);

    }
}
