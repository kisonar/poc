package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeRuntime;

public final class LogicFactory {

    private LogicFactory() {
    }

    public static Logic getLogic(ModeRuntime modeRuntime, ModeExecution modeExecution) {
        Logic logic = switch (modeRuntime) {
            case EXECUTOR -> new LogicExecutor(modeExecution);
            case ClASIC -> new LogicManual(modeExecution);
            default -> throw new IllegalArgumentException(String.format("Mode runtime Not supported ", modeRuntime));
        };
        return logic;
    }
}