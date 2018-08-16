package com.mmigdal.mossad.key.logger.parser.logic.line;

import com.mmigdal.mossad.key.logger.library.KeyLoggerEntries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class LineProcessor {

    private List<String> processedLinesOfText;

    public LineProcessor() {
        processedLinesOfText = new ArrayList<>();
    }

    public Stream<String> executeFilteringForLogger(Stream<String> streamOfLines) {
        return streamOfLines.
            filter(line -> !line.contains("com.mossad.keylogger.logging.LogRecorder")).
            collect(Collectors.toList()).stream();
    }

    public List<String> executeReplacement(Stream<String> streamOfLines) {
        streamOfLines.forEachOrdered(line -> {
            String processedLine = filterLine(line);
            processedLinesOfText.add(processedLine);

        });
        return processedLinesOfText;
    }

    private String filterLine(String input) {
        List<String> patterns = KeyLoggerEntries.getStringsToFilterOut();
        for (String pattern : patterns) {
            input = filterLine(input, pattern);
        }
        return input;
    }


    private String filterLine(String input, String wordToRemove) {
        return input.replaceAll(wordToRemove, "");
    }


}
