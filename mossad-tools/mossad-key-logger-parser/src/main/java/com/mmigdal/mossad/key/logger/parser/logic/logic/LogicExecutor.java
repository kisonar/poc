package com.mmigdal.mossad.key.logger.parser.logic.logic;


import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.file.FileProcessor;


import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.logging.Logger;

public final class LogicExecutor extends LogicAbstraction {

    private static Logger LOG = Logger.getLogger(LogicExecutor.class.getName());
    private ExecutorService executorService;

    private long startTime;
    private long endTime;

    public LogicExecutor(ModeExecution modeExecution) {
        super(modeExecution);
    }

    @Override
    public void execute() {
        determineExecutorService();
        startTime = getTime();
        getItems().stream().forEach(item -> {
            executorService.submit(() -> {
                FileProcessor fileProcessor = new FileProcessor();
                fileProcessor.processFile(item.input, item.output);
                LOG.info(String.format("Thread name %s", Thread.currentThread().getName()));
            });
        });
        executorService.shutdown();
        endTime = getTime();
        LOG.info(String.format("Execution took: %d ms", (endTime - startTime)));

    }

    private void determineExecutorService() {
        executorService = switch (modeExecution) {
            case PARALLEL -> Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
            case SINGLE -> Executors.newSingleThreadExecutor();
            default -> throw new IllegalArgumentException(String.format("Mode execution is not supported ", modeExecution));
        };
    }

    private long getTime() {
        return Calendar.getInstance().getTime().getTime();
    }
}