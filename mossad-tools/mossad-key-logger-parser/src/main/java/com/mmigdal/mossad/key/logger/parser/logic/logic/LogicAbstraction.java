package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.model.Item;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class LogicAbstraction implements Logic {

    private static Logger LOG = Logger.getLogger(LogicAbstraction.class.getName());
    protected ModeExecution modeExecution;
    private List<Item> items;

    public LogicAbstraction(ModeExecution modeExecution) {
        this.modeExecution = modeExecution;
        this.items = new ArrayList<>();
    }

    public void prepare(String inputPath, String outputPath, List<String> years) {
        years.forEach(year -> {
            String input = inputPath + File.separatorChar + year;
            String output = outputPath + File.separatorChar + year;
            try {
                items.addAll(readItems(Path.of(input), Path.of(output)));
            } catch (IOException e) {
                LOG.log(Level.WARNING, String.format("Cannot read items for %s %s", input, output));
            }
        });
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }
}