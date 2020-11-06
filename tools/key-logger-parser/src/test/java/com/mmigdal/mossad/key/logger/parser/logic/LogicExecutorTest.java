package com.mmigdal.mossad.key.logger.parser.logic;

import com.mmigdal.mossad.key.logger.parser.logic.logic.Logic;
import com.mmigdal.mossad.key.logger.parser.logic.logic.LogicFactory;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeRuntime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.Calendar;
import java.util.logging.Logger;

import static com.mmigdal.mossad.key.logger.parser.cli.Configuration.YEARS;
import static com.mmigdal.mossad.key.logger.parser.logic.others.TestingConfiguration.LINUX_PATH_FQ_INPUT;
import static com.mmigdal.mossad.key.logger.parser.logic.others.TestingConfiguration.LINUX_PATH_FQ_OUTPUT;
import static com.mmigdal.mossad.key.logger.parser.logic.others.TestingConfiguration.WIN_PATH_FQ_INPUT;
import static com.mmigdal.mossad.key.logger.parser.logic.others.TestingConfiguration.WIN_PATH_FQ_OUTPUT;

public class LogicExecutorTest {

    private static Logger LOG = Logger.getLogger(LogicExecutorTest.class.getName());

    private Logic logic;
    private long startTime;
    private long endTime;

    @BeforeEach
    public void recordStartTime() {
        startTime = getTime();
    }

    @AfterEach
    public void recordEndTime() {
        endTime = getTime();
        LOG.info(String.format("Execution took: %d ms", (endTime - startTime)));
    }

    @EnabledOnOs(OS.LINUX)
    @Test
    public void processFile_whenLinux_executor_parallel() {
        executeGenericTest(LINUX_PATH_FQ_INPUT, LINUX_PATH_FQ_OUTPUT, ModeRuntime.EXECUTOR, ModeExecution.PARALLEL_FIXED);
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
        executeGenericTest(WIN_PATH_FQ_INPUT, WIN_PATH_FQ_OUTPUT, ModeRuntime.EXECUTOR, ModeExecution.PARALLEL_FIXED);
    }

    private void executeGenericTest(String inputPath, String outputPath, ModeRuntime modeRuntime, ModeExecution modeExecution) {
        logic = LogicFactory.getLogic(modeRuntime, modeExecution);
        logic.prepare(inputPath, outputPath, YEARS);
        logic.execute();
    }

    private long getTime() {
        return Calendar.getInstance().getTime().getTime();
    }
}