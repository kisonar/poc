package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;

public abstract class LogicAbstraction implements Logic {

    protected ModeExecution modeExecution;

    public LogicAbstraction(ModeExecution modeExecution) {
        this.modeExecution = modeExecution;
    }
}