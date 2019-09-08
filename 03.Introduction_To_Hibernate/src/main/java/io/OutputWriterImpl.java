package io;

import interfaces.OutputWriter;

public class OutputWriterImpl implements OutputWriter {
    @Override
    public void write(String line) {
        System.out.print(line);
    }

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
