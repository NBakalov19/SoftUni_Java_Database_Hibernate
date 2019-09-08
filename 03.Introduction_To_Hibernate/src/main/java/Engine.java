import core.Executor;
import interfaces.*;
import io.InputReaderImpl;
import io.OutputWriterImpl;

import java.io.IOException;

public class Engine implements Runnable {

    private OutputWriter writer;
    private InputReader reader;
    private Executor executor;

    public Engine() {
        this.writer = new OutputWriterImpl();
        this.reader = new InputReaderImpl();
        this.executor = new Executor();
    }

    @Override
    public void run() {
        this.writer.write("Enter task number fro 1-14 to execute or 0 to stop: ");
        try {
            int taskNumber = Integer.parseInt(this.reader.readLine());
            this.executor.execute(taskNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}