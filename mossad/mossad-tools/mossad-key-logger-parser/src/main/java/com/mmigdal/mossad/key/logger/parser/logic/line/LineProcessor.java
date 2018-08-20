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
            String processedLineWithEmptySings = filterLine(line);
            String processedLine = removeBlankSigns(processedLineWithEmptySings);
            processedLinesOfText.add(processedLine);

        });
        return processedLinesOfText;
    }

    private String removeBlankSigns(String processedLineWithEmptySings) {

        StringBuilder stringBuilder = new StringBuilder();
        String [] splittedWords = processedLineWithEmptySings.split(KeyLoggerEntries.SIGN_SPACE);
        for (String splittedWord : splittedWords) {
            if(splittedWord.equals(KeyLoggerEntries.SIGN_SPACE) || splittedWord.equals(KeyLoggerEntries.SIGN_EMPTY)) {
                continue;
            }
            stringBuilder.append(splittedWord);
            stringBuilder.append(KeyLoggerEntries.SIGN_SPACE);
        }
        return stringBuilder.toString();
    }

    private String filterLine(String input) {
        List<String> patterns = KeyLoggerEntries.getStringsToFilterOut();
        for (String pattern : patterns) {
            input = filterLine(input, pattern);
        }
        return input;
    }


    private String filterLine(String input, String wordToRemove) {
        return input.replaceAll(wordToRemove, KeyLoggerEntries.SIGN_EMPTY);
    }


}
