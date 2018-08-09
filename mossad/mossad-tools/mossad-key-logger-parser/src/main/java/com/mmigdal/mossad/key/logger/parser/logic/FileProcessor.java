package com.mmigdal.mossad.key.logger.parser.logic;

import com.mmigdal.mossad.key.logger.parser.logic.line.LineProcessor;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public final class FileProcessor {

    private LineProcessor lineProcessor;
    private String pathToFile;

    public FileProcessor() {
        lineProcessor = new LineProcessor();

    }

    public void processFile(String pathToFile) {
        this.pathToFile = pathToFile;
        try {
            Stream<String> lines = readLines(pathToFile);
            Stream<String> filteredLines = lineProcessor.executeFiltering(lines);
            List<String> processedLines = lineProcessor.executeReplacement(filteredLines);
            saveResult(processedLines);
        } catch (IOException e) {

        }

    }

    private Stream<String> readLines(String pathToFile) throws IOException {
        Path filePath = Paths.get(pathToFile);
        if (!Files.exists(filePath, LinkOption.NOFOLLOW_LINKS))
            return Stream.empty();
        return Files.lines(filePath);
    }

    private void saveResult(List<String> linesToWrite) throws IOException {
        try (FileWriter fileWriter = new FileWriter(pathToFile)) {
            linesToWrite.stream().forEachOrdered(line -> {
                try {
                    fileWriter.write(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
    }


}
