package com.mmigdal.mossad.key.logger.parser.logic.line;

import com.mmigdal.mossad.key.logger.library.KeyLoggerEntries;
import com.mmigdal.mossad.key.logger.parser.logic.model.Configuration;

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
                filter(line -> !line.contains(Configuration.LOG_RECORDER)).
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
        for (String word : processedLineWithEmptySings.split(SPACE)) {
            if (word.equals(SPACE) || word.equals(EMPTY)) {
                continue;
            }
            stringBuilder.append(word);
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