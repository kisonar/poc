package com.mmigdal.mossad.key.logger.parser.cli;

import com.mmigdal.mossad.key.logger.parser.logic.logic.Logic;
import com.mmigdal.mossad.key.logger.parser.logic.logic.LogicFactory;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeRuntime;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException("Insufficient number of input parameters");
        }
        String inputPath = args[0];
        String outputPath = args[1];

        Logic logic = LogicFactory.getLogic(ModeRuntime.EXECUTOR, ModeExecution.PARALLEL_FIXED);
        logic.prepare(inputPath, outputPath, List.of());
        logic.execute();
    }
}
