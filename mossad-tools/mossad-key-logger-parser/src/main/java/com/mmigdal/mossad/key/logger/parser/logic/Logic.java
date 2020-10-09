package com.mmigdal.mossad.key.logger.parser.logic;

import com.mmigdal.mossad.key.logger.parser.logic.file.FileProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            final List<Item> items = new ArrayList<>();
            filesPaths.forEach(filePath -> {
                String sourceFileName = filePath.toFile().getName();
                Path destinationFilePath = Paths.get(outputPath.toString(), sourceFileName);
                items.add( new Item(filePath,destinationFilePath));
            });

            items.stream().forEach(item -> {
                executorService.submit(() -> {
                    FileProcessor fileProcessor = new FileProcessor();
                    fileProcessor.processFile(item.input, item.output);
                });
            });
        } catch (IOException e) {
            LOG.log(Level.ALL, String.format("Cannot start execution due to %s", e.getMessage()));
        }
    }

    public ExecutorService determineExecutorService() {

        ExecutorService executorService = null;
        switch (mode){
            case SINGLE :
                executorService = Executors.newSingleThreadExecutor();
                break;
            case PARALLEL:
                executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()-1 );
            default:
                break;
        }
        return executorService;

        /*
        ExecutorService executorService = switch (mode) {
            case PARALLEL ->  Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
            default -> Executors.newSingleThreadExecutor();
        };

        return executorService;
        */

    }
}