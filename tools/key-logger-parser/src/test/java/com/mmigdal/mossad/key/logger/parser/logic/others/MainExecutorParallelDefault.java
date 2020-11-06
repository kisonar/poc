package com.mmigdal.mossad.key.logger.parser.logic.others;

import com.mmigdal.mossad.key.logger.parser.cli.Configuration;
import com.mmigdal.mossad.key.logger.parser.logic.logic.Logic;
import com.mmigdal.mossad.key.logger.parser.logic.logic.LogicFactory;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeRuntime;

public class MainExecutorParallelDefault {

    public static void main(String[] args) {
        Logic logic = LogicFactory.getLogic(ModeRuntime.EXECUTOR, ModeExecution.PARALLEL_DEFAULT);
        logic.prepare(TestingConfiguration.LINUX_PATH_FQ_INPUT, TestingConfiguration.LINUX_PATH_FQ_OUTPUT, Configuration.YEARS);
        logic.execute();
    }
}