package com.mmigdal.mossad.key.logger.parser.cli;

import com.mmigdal.mossad.key.logger.parser.logic.logic.Logic;
import com.mmigdal.mossad.key.logger.parser.logic.logic.LogicFactory;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeRuntime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException("Insufficient number of input parameters!!!");
        }
        String inputDirectory = args[0];
        String outputDirectory = args[1];
        List<String> years = parseYears(args);

        Logic logic = LogicFactory.getLogic(ModeRuntime.EXECUTOR, ModeExecution.PARALLEL_FIXED);
        logic.prepare(inputDirectory, outputDirectory, years);
        logic.execute();
    }

    private static List<String> parseYears(String[] input) {
        List<String> years = new ArrayList<>();
        for (int index = 2; index < input.length; index++) {
            years.add(input[index]);
        }
        return Collections.unmodifiableList(years);
    }
}