package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.file.FileProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public final class LogicExecutor extends LogicAbstraction {

    private static Logger LOG = Logger.getLogger(LogicExecutor.class.getName());
    private ExecutorService executorService;

    public LogicExecutor(ModeExecution modeExecution) {
        super(modeExecution);
    }

    @Override
    public void execute() {
        determineExecutorService();
        getItems().stream().forEach(item -> {
            executorService.submit(() -> {
                FileProcessor fileProcessor = new FileProcessor();
                fileProcessor.processFile(item.input, item.output);
                LOG.finest(String.format("Thread name %s", Thread.currentThread().getName()));
            });
        });
        executorService.shutdown();
    }

    private void determineExecutorService() {
        executorService = switch (modeExecution) {
            case PARALLEL_FIXED -> Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
            case PARALLEL_DEFAULT -> Executors.newWorkStealingPool();
            case SINGLE -> Executors.newSingleThreadExecutor();
            default -> throw new IllegalArgumentException(String.format("Provided mode execution is not supported %s", modeExecution));
        };
    }
}