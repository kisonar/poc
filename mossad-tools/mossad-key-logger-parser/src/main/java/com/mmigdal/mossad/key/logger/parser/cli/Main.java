package com.mmigdal.mossad.key.logger.parser.cli;

import com.mmigdal.mossad.key.logger.parser.logic.logic.Logic;
import com.mmigdal.mossad.key.logger.parser.logic.logic.LogicFactory;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeRuntime;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException("Insufficient number of input parameters");
        }
        Logic logic = LogicFactory.getLogic(ModeRuntime.EXECUTOR, ModeExecution.PARALLEL);
        logic.configure();
        logic.execute(args[0], args[1]);
    }
}
