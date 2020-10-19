package com.mmigdal.mossad.key.logger.parser.logic.others;

import com.mmigdal.mossad.key.logger.parser.cli.Configuration;
import com.mmigdal.mossad.key.logger.parser.logic.logic.Logic;
import com.mmigdal.mossad.key.logger.parser.logic.logic.LogicFactory;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeRuntime;

public class MainTest {

    public static void main(String[] args) {
        Logic logic = LogicFactory.getLogic(ModeRuntime.EXECUTOR, ModeExecution.PARALLEL);
        logic.prepare(ConfigurationTest.LINUX_PATH_FQ_INPUT, ConfigurationTest.LINUX_PATH_FQ_OUTPUT, Configuration.YEARS);
        logic.execute();
    }
}