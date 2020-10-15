package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.model.Item;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LogicManual extends LogicAbstraction {

    private static Logger LOG = Logger.getLogger(LogicManual.class.getName());

    public LogicManual(ModeExecution modeExecution) {
        super(modeExecution);
    }

    @Override
    public void configure() {

    }

    @Override
    public void execute(String inputPathString, String outputPathString) {
        try {
            List<Item> items = getItems(Paths.get(inputPathString), Paths.get(outputPathString));
        } catch (IOException e) {
            LOG.log(Level.ALL, String.format("Cannot start execution due to %s", e.getMessage()));
        }
    }
}