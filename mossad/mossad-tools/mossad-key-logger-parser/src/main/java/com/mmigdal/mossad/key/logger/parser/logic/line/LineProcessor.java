package com.mmigdal.mossad.key.logger.parser.logic.line;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class LineProcessor {

    private List<String> processedLinesOfText;

    public LineProcessor() {
        processedLinesOfText = new ArrayList<>();
    }


    public Stream<String> executeFiltering(Stream<String> streamOfLines) {
        return streamOfLines.
                filter(line -> startsWith(line, "rokko")).
                filter(line -> startsWith(line, "mam")).
            collect(Collectors.toList()).stream();
    }

    public List<String> executeReplacement(Stream<String> streamOfLines) {

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
