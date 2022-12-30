package com.mmigdal.mossad.key.logger.parser.logic.others;

import com.mmigdal.mossad.key.logger.parser.logic.model.Configuration;
import com.mmigdal.mossad.key.logger.parser.logic.logic.Logic;
import com.mmigdal.mossad.key.logger.parser.logic.logic.LogicFactory;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeRuntime;
import kisonar.platform.domain.util.Measurement;

public class MainExecutorSingle {

    public static void main(String[] args) {
        Logic logic = LogicFactory.getLogic(ModeRuntime.EXECUTOR, ModeExecution.SINGLE);
        logic.prepare(TestingConfiguration.WIN_PATH_FQ_INPUT, TestingConfiguration.WIN_PATH_FQ_OUTPUT, Configuration.YEARS);
        Measurement measurement = new Measurement();
        measurement.prepare();
        measurement.start();
        logic.execute();
        measurement.finish();
        measurement.printResult();
    }
}