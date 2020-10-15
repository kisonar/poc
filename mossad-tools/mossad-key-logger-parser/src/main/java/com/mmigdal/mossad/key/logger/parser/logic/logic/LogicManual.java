package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.model.Item;
import com.mmigdal.mossad.key.logger.parser.logic.model.Mode;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

public final class LogicManual implements Logic {

    private static Logger LOG = Logger.getLogger(LogicManual.class.getName());
    private Mode mode;

    public LogicManual(Mode mode) {
        this.mode = mode;
    }

    @Override
    public void configure() {

    }

    @Override
    public void execute(String inputPathString, String outputPathString) {
        try {
            List<Item> items = getItems(Paths.get(inputPathString), Paths.get(outputPathString));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}