package com.mmigdal.mossad.key.logger.parser.logic;

import com.mmigdal.mossad.key.logger.parser.logic.file.FileProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public final class Logic {

    private static Logger LOG = Logger.getLogger(Logic.class.getName());
    private Mode mode;

    public Logic(Mode mode) {
        this.mode = mode;
    }

    public void execute(String inputPathString, String outputPathString) {
        Path inputPath = Paths.get(inputPathString);
        Path outputPath = Paths.get(outputPathString);
        try {
            Stream<Path> filesPaths = Files.list(inputPath).sorted();
            final ExecutorService executorService = determineExecutorService();
            filesPaths.forEach(sourceFilePath -> {
                executorService.submit(() -> {
                    FileProcessor fileProcessor = new FileProcessor();
                    String sourceFileName = sourceFilePath.toFile().getName();
                    Path destinationFilePath = Paths.get(outputPath.toString(), sourceFileName);
                    fileProcessor.processFile(sourceFilePath, destinationFilePath);
                });
            });
            executorService.awaitTermination(1, TimeUnit.SECONDS);
            executorService.shutdown();

        } catch (IOException |InterruptedException e) {
            LOG.log(Level.ALL, String.format("Cannot start execution due to %s", e.getMessage()));
        }
    }

    public ExecutorService determineExecutorService() {
        ExecutorService executorService = switch (mode) {
            case PARALLEL -> Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
            default -> Executors.newSingleThreadExecutor();
        };
        return executorService;
    }
}