package com.mmigdal.mossad.key.logger.parser.cli;

import com.mmigdal.mossad.key.logger.parser.logic.logic.Logic;
import com.mmigdal.mossad.key.logger.parser.logic.logic.LogicExecutors;
import com.mmigdal.mossad.key.logger.parser.logic.model.Mode;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException("Insufficient number of input parameters");
        }

        Logic logic = new LogicExecutors(Mode.EXECUTOR_PARALLEL);
        logic.configure();
        logic.execute(args[0], args[1]);
    }
}
