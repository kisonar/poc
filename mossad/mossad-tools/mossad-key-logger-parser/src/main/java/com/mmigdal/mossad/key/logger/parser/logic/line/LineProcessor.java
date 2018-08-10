package com.mmigdal.mossad.key.logger.parser.logic.line;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

        String replacementValue = "";

        streamOfLines.forEachOrdered(line -> {

            Set<String> wordsToWipeOut = getStringsToFilterOut();
            wordsToWipeOut.forEach(wordToWipeOut -> {
                line.replace(wordToWipeOut, replacementValue);
            });

            processedLinesOfText.add(line);
        });
        return processedLinesOfText;
    }

    private boolean startsWith(String text, String pattern) {
        return text.startsWith(pattern);
    }


    Set<String> getStringsToFilterOut() {
        return Set.of("INFO:", "Backspace", "Down", "Enter", "Up", "Volume", "Right", "Left", "Back", "Space", "Shift");
    }

}
