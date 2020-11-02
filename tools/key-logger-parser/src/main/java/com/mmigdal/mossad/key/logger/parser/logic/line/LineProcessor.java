package com.mmigdal.mossad.key.logger.parser.logic.line;

import com.mmigdal.mossad.key.logger.library.KeyLoggerEntries;
import static kisonar.platform.domain.BaseDefinitions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class LineProcessor {

    private List<String> processedLinesOfText;
    private BiFunction<String, String, String> filterLineFunction = (s1, s2) -> s1
            .replaceAll(s2, EMPTY);

    public LineProcessor() {
        processedLinesOfText = new ArrayList<>();
    }

    public void reset() {
        processedLinesOfText.clear();
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
        for (String splittedWord : processedLineWithEmptySings.split(SPACE)) {
            if (splittedWord.equals(SPACE) || splittedWord.equals(EMPTY)) {
                continue;
            }
            stringBuilder.append(splittedWord);
            stringBuilder.append(SPACE);
        }
        return stringBuilder.toString();
    }

    private String filterLine(String input) {
        for (String pattern : KeyLoggerEntries.getStringsToFilterOut()) {
            input = filterLineFunction.apply(input, pattern);
        }
        return input;
    }
}