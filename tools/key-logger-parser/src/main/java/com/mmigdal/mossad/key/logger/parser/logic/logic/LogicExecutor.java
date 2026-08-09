package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.file.FileProcessor;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class LogicExecutor extends LogicAbstraction {

      private ExecutorService executorService;

      public LogicExecutor(ModeExecution modeExecution) {
            super(modeExecution);
      }

      @Override
      public void execute() {
            determineExecutorService();
            getItems().forEach(
                    item -> executorService.submit(() ->
                            {
                                  FileProcessor fileProcessor = new FileProcessor();
                                  fileProcessor.processFile(Thread.currentThread().getName(), item.input(), item.output());
                            }
                    ));
            executorService.shutdown();
      }

      private void determineExecutorService() {
            executorService = switch (modeExecution) {
                  case CACHED -> Executors.newCachedThreadPool();
                  case PARALLEL_DEFAULT -> Executors.newWorkStealingPool();
                  case PARALLEL_FIXED -> Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
                  case SINGLE -> Executors.newSingleThreadExecutor();
                  case VIRTUAL -> Executors.newVirtualThreadPerTaskExecutor();
            };
      }
}