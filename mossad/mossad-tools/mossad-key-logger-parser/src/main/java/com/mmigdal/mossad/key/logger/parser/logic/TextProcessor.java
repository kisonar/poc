package com.mmigdal.mossad.key.logger.parser.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TextProcessor {

    private List<String> processedLinesOfText;

    public TextProcessor() {
        processedLinesOfText = new ArrayList<>();
    }


    public List<String> executeFiltering(Stream<String> streamOfLines) {
        return streamOfLines.
                filter(line -> startsWith(line, "rokko")).
                filter(line -> startsWith(line, "mam")).
                collect(Collectors.toList());
    }

    public List<String> executeRepalcement(Stream<String> streamOfLines) {

        String replacementPattern = "", replacementValue = "";

        streamOfLines.forEachOrdered(line -> {
            String processedLine = line.replace(replacementPattern, replacementValue);
            processedLinesOfText.add(processedLine);
        });
        return processedLinesOfText;
    }

    private boolean startsWith(String text, String pattern) {
        return text.startsWith(pattern);
    }
}
