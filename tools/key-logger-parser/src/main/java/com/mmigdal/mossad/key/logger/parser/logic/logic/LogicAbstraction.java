package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.model.Item;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class LogicAbstraction implements Logic {

    private static Logger LOG = Logger.getLogger(LogicAbstraction.class.getName());
    protected ModeExecution modeExecution;
    private List<Item> items;

    public LogicAbstraction(ModeExecution modeExecution) {
        this.modeExecution = modeExecution;
        this.items = new ArrayList<>();
    }

    public void prepare(String inputDirectory, String outputDirectory, List<String> years) {
        years.forEach(year -> {
            String inputYear = createYearLocation(inputDirectory, year);
            String outputYear = createYearLocation(outputDirectory, year);
            Path input = Path.of(inputYear);
            Path output = Path.of(outputYear);
            if (input.toFile().isDirectory() && output.toFile().isDirectory()) {
                try {
                    items.addAll(readItems(input, output));
                } catch (IOException e) {
                    LOG.log(Level.WARNING, String.format("Cannot read items for %s %s", inputYear, outputYear));
                }
            } else {
                LOG.log(Level.WARNING, String.format("One of locations is not a directory %s %s", input, output));
            }
        });
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    private String createYearLocation(String inputPath, String year) {
        return inputPath + File.separatorChar + year;
    }

}