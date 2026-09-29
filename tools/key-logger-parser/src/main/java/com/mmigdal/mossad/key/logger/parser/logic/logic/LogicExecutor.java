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
            getItems().forEach(item -> executorService.submit(() -> {
                  FileProcessor fileProcessor = new FileProcessor();
                  fileProcessor.processFile(Thread.currentThread().getName(), item.input(), item.output());
            }));
            executorService.shutdown();
      }

       private void determineExecutorService() {
             executorService = switch (modeExecution) {
                   case PARALLEL_FIXED -> Executors.newThreadPerTaskExecutor(Thread.ofVirtual().name("logic-executor-", 0).factory());
                   case PARALLEL_DEFAULT -> Executors.newVirtualThreadPerTaskExecutor();
                   case SINGLE -> Executors.newSingleThreadExecutor(Thread.ofVirtual().name("logic-single-", 0).factory());
                   case CACHED -> Executors.newVirtualThreadPerTaskExecutor();
             };
       }
}