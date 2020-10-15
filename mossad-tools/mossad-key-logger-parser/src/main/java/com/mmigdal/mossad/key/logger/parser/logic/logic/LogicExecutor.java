package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.model.Item;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.file.FileProcessor;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LogicExecutor extends LogicAbstraction {

    private static Logger LOG = Logger.getLogger(LogicExecutor.class.getName());
    private ExecutorService executorService;

    public LogicExecutor(ModeExecution modeExecution) {
        super(modeExecution);
    }

    @Override
    public void configure() {
        determineExecutorService();
    }

    @Override
    public void execute(String inputPathString, String outputPathString) {
        try {
            List<Item> items = getItems(Paths.get(inputPathString), Paths.get(outputPathString));
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

    private void determineExecutorService() {
        executorService = switch (modeExecution) {
            case PARALLEL -> Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
            case SINGLE -> Executors.newSingleThreadExecutor();
            default -> throw new IllegalArgumentException(String.format("Mode execution is not supported ", modeExecution));
        };
    }
}