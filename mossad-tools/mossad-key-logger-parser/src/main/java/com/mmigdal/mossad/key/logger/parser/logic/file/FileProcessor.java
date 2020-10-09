package com.mmigdal.mossad.key.logger.parser.logic.file;

import com.mmigdal.mossad.key.logger.parser.logic.line.LineProcessor;

import java.io.File;
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

    private Logger LOG = Logger.getLogger(FileProcessor.class.getName());
    private LineProcessor lineProcessor;

    public FileProcessor() {
        lineProcessor = new LineProcessor();
    }

    public void processFile(Path pathInputFile, Path pathOutputFile) {
        try {
            //LOG.info(String.format("Starting processing files %s %s", pathInputFile.toAbsolutePath().toFile().getName(), pathOutputFile.toAbsolutePath().toFile().getName()));
            Stream<String> lines = readLines(pathInputFile);
            Stream<String> filteredLines = lineProcessor.executeFilteringForLogger(lines);
            List<String> processedLines = lineProcessor.executeReplacement(filteredLines);
            saveResult(processedLines, pathOutputFile);
            lineProcessor.reset();
            //LOG.info(String.format("Finished processing files %s %s", pathInputFile.toAbsolutePath().toFile().getName(),
            //    pathOutputFile.toAbsolutePath().toFile().getName()));
        } catch (IOException e) {
            LOG.log(Level.WARNING, String
                    .format("Problems with processing file %s %s", pathInputFile.getFileName().toString(), e.getMessage()));
        }
    }

    private Stream<String> readLines(Path filePath) throws IOException {
        if (!Files.exists(filePath, LinkOption.NOFOLLOW_LINKS)) {
            return Stream.empty();
        }
        return Files.readAllLines(filePath, StandardCharsets.UTF_8).stream();
    }

    private void saveResult(List<String> linesToWrite, Path outputFilePath) throws IOException {
        File outputFile = outputFilePath.toFile();
        if (outputFile.exists()) {
            outputFile.delete();
        }
        outputFile.createNewFile();
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            linesToWrite.stream().forEachOrdered(line -> {
                try {
                    fileWriter.write(line);
                    fileWriter.write("\n");
                } catch (IOException e) {
                    LOG.log(Level.WARNING, String.format("Problems during saving result: %s ", e.getMessage()));
                }
            });

        }
    }

}
