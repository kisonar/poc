package com.mmigdal.mossad.key.logger.parser.cli;

import com.mmigdal.mossad.key.logger.parser.logic.LogicFlow;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException("Insufficient number of input parameters");
        }

        LogicFlow logicFlow = new LogicFlow(args[0], args[1]);
        logicFlow.execute();
    }
}
