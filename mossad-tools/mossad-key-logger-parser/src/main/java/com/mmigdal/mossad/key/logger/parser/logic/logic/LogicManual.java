package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;

import java.util.logging.Logger;

public final class LogicManual extends LogicAbstraction {

    private static Logger LOG = Logger.getLogger(LogicManual.class.getName());

    public LogicManual(ModeExecution modeExecution) {
        super(modeExecution);
    }

    @Override
    public void execute() {

    }

}