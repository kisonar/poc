package com.mmigdal.mossad.key.logger.parser.logic;

import com.mmigdal.mossad.key.logger.parser.logic.file.FileProcessor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class LogicFlow {

    private static Logger LOG = Logger.getLogger(LogicFlow.class.getName());
    private Path inputPath;
    private Path outputPath;
    private FileProcessor fileProcessor;

    public LogicFlow(String inputPathString, String outputPathString) {
        inputPath = Paths.get(inputPathString);
        outputPath = Paths.get(outputPathString);
        fileProcessor = new FileProcessor();
    }

    public void execute() {
        try {
            Stream<Path> filesPaths = Files.list(inputPath).sorted();
            filesPaths.forEach(sourceFilePath -> {
                String sourceFileName = sourceFilePath.toFile().getName();
                Path destinationFilePath = Paths.get(outputPath.toString(), sourceFileName);
                fileProcessor.processFile(sourceFilePath, destinationFilePath);
            });
        } catch (IOException e) {
            LOG.log(Level.ALL, String.format("Cannot start execution due to %s", e.getMessage()));
        }
    }
}