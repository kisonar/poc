package com.mossad.keylogger.logging;


import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class LogRecorder {


    private final Logger LOG = Logger.getLogger("moossad");
    private FileHandler fileHandler;
    private SimpleFormatter simpleFormatter;

    public LogRecorder() {
        simpleFormatter = new SimpleFormatter();
        try {
            fileHandler = new FileHandler("Logging.txt");
        }
        catch (IOException ex){
            throw new RuntimeException("Cannot create logging.txt file. Exiting application");
        }
        fileHandler.setFormatter(simpleFormatter);
        LOG.addHandler(fileHandler);
    }

    public void writeContent(String content)  {
        LOG.info(content);
    }

}
