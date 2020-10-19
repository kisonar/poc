package com.mmigdal.mossad.key.logger.parser.logic;

import com.mmigdal.mossad.key.logger.parser.logic.logic.Logic;
import com.mmigdal.mossad.key.logger.parser.logic.logic.LogicFactory;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeRuntime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static com.mmigdal.mossad.key.logger.parser.cli.Configuration.YEARS;

import static com.mmigdal.mossad.key.logger.parser.logic.others.ConfigurationTest.LINUX_PATH_FQ_INPUT;
import static com.mmigdal.mossad.key.logger.parser.logic.others.ConfigurationTest.LINUX_PATH_FQ_OUTPUT;
import static com.mmigdal.mossad.key.logger.parser.logic.others.ConfigurationTest.WIN_PATH_FQ_INPUT;
import static com.mmigdal.mossad.key.logger.parser.logic.others.ConfigurationTest.WIN_PATH_FQ_OUTPUT;

public class LogicExecutorTest {

    private Logic logic;

    @EnabledOnOs(OS.LINUX)
    @Test
    public void processFile_whenLinux_executor_parallel() {
        executeGenericTest(LINUX_PATH_FQ_INPUT, LINUX_PATH_FQ_OUTPUT, ModeRuntime.EXECUTOR, ModeExecution.PARALLEL);
    }

    @EnabledOnOs(OS.LINUX)
    @Test
    public void processFile_whenLinux_executor_single() {
        executeGenericTest(LINUX_PATH_FQ_INPUT, LINUX_PATH_FQ_OUTPUT, ModeRuntime.EXECUTOR, ModeExecution.SINGLE);
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void processFile_whenWindows_executor_single() {
        executeGenericTest(WIN_PATH_FQ_INPUT, WIN_PATH_FQ_OUTPUT, ModeRuntime.EXECUTOR, ModeExecution.SINGLE);
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void processFile_whenWindows_executor_parallel() {
        executeGenericTest(WIN_PATH_FQ_INPUT, WIN_PATH_FQ_OUTPUT, ModeRuntime.EXECUTOR, ModeExecution.PARALLEL);
    }

    private void executeGenericTest(String inputPath, String outputPath, ModeRuntime modeRuntime, ModeExecution modeExecution) {
        logic = LogicFactory.getLogic(modeRuntime, modeExecution);
        logic.prepare(inputPath, outputPath, YEARS);
        logic.execute();
    }


}