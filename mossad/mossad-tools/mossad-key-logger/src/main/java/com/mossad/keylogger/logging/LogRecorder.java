package com.mossad.keylogger.logging;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogRecorder {

    private final Logger LOG = Logger.getLogger(LogRecorder.class.getCanonicalName());
    private FileHandler fileHandler;
    private SimpleFormatter simpleFormatter;

    public LogRecorder() {
        simpleFormatter = new SimpleFormatter();
        try {
            fileHandler = new FileHandler("Logging", 1024000, 100, true);
        } catch (IOException ex) {
            throw new RuntimeException("Cannot create logging file. Exiting application");
        }
        fileHandler.setFormatter(simpleFormatter);
        LOG.addHandler(fileHandler);
    }

    public void writeContent(String content) {

        if (!Objects.isNull(content) && !content.isEmpty()) {
            LOG.info(content);
        }
    }

}
