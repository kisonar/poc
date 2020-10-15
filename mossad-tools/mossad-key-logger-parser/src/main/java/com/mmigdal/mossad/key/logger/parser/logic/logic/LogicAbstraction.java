package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeRuntime;

public abstract class LogicAbstraction implements Logic {

    protected ModeExecution modeExecution;
    @Deprecated
    protected ModeRuntime modeRuntime;

    public LogicAbstraction(ModeExecution modeExecution) {
        this.modeExecution = modeExecution;
    }
}