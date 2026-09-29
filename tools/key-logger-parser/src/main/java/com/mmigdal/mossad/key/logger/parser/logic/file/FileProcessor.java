package com.mmigdal.mossad.key.logger.parser.logic.file;

import com.mmigdal.mossad.key.logger.parser.logic.line.LineProcessor;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public final class FileProcessor {

      private final Logger LOG = Logger.getLogger(FileProcessor.class.getName());
      private final LineProcessor lineProcessor;

      public FileProcessor() {
            lineProcessor = new LineProcessor();
      }

       public void processFile(String threadName, Path pathInputFile, Path pathOutputFile) {
             try {
                   var inputFileName = pathInputFile.toAbsolutePath().toFile().getName();
                   var outputFileName = pathOutputFile.toAbsolutePath().toFile().getName();
                   LOG.info("Thread %s is starting processing files %s %s".formatted(threadName, inputFileName, outputFileName));
                   var lines = readLines(pathInputFile);
                   var filteredLines = lineProcessor.executeFilteringForLogger(lines);
                   var processedLines = lineProcessor.executeReplacement(filteredLines);
                   saveResult(processedLines, pathOutputFile);
                   lineProcessor.reset();
                   LOG.info("Thread %s finished processing files %s %s".formatted(threadName, inputFileName, outputFileName));
             }
             catch (IOException e) {
                   LOG.log(Level.WARNING, "Thread %s had problem with processing file %s %s".formatted(threadName, pathInputFile, e.getMessage()));
             }
       }

      private Stream<String> readLines(Path filePath) throws IOException {
            if (!Files.exists(filePath, LinkOption.NOFOLLOW_LINKS)) {
                  return Stream.empty();
            }
            return Files.readAllLines(filePath, StandardCharsets.ISO_8859_1).stream();
      }

       private void saveResult(List<String> linesToWrite, Path outputFilePath) throws IOException {
             var outputFile = outputFilePath.toFile();
             if (outputFile.exists()) {
                   outputFile.delete();
             }
             outputFile.createNewFile();
             try (var fileWriter = new FileWriter(outputFile)) {
                   linesToWrite.stream().forEachOrdered(line -> {
                         try {
                               fileWriter.write(line);
                               fileWriter.write("\n");
                         }
                         catch (IOException e) {
                               LOG.log(Level.WARNING, "Problems during saving result: %s ".formatted(e.getMessage()));
                         }
                   });
             }
       }

}