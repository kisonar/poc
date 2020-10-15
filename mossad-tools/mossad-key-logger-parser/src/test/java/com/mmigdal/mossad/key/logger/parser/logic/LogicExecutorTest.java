package com.mmigdal.mossad.key.logger.parser.logic;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import com.mmigdal.mossad.key.logger.parser.logic.logic.Logic;
import com.mmigdal.mossad.key.logger.parser.logic.logic.LogicFactory;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeRuntime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class LogicExecutorTest {

    private final static List<String> years = List.of("2018", "2019", "2020");
    private Logic logic;
    private long startTime;
    private long endTime;
    private final String generalLinuxPath = "/run/media/marcin/MigiBigDisk/repozytoria/repo-arbeit-client/trunk/korpo/toBeDestroyed/tools/wlam";
    private final String generalWinPath = "G:\\repozytoria\\repo_test\\korpo\\toBeDestroyed\\tools\\wlam\\output\\";
    private final static String INPUT = "input";
    private final static String OUTPUT = "output";

    @BeforeEach
    public void setUpBeforeEach() {
        startTime = getTime();
    }

    @AfterEach
    public void tearDownAfterEach() {
        endTime = getTime();
        System.out.println(String.format("Execution took: %d ms", (endTime - startTime)));
    }

    @EnabledOnOs(OS.LINUX)
    @Test
    public void processFile_whenLinux_executor_parallel() {
        String generalLinuxPathInput = generalLinuxPath + File.separatorChar + INPUT;
        String generalLinuxPathOutput = generalLinuxPath + File.separatorChar + OUTPUT;
        executeGenericTest(generalLinuxPathInput, generalLinuxPathOutput, ModeRuntime.EXECUTOR, ModeExecution.PARALLEL);
    }

    @EnabledOnOs(OS.LINUX)
    @Test
    public void processFile_whenLinux_executor_single() {
        String generalLinuxPathInput = generalLinuxPath + File.separatorChar + INPUT;
        String generalLinuxPathOutput = generalLinuxPath + File.separatorChar + OUTPUT;
        executeGenericTest(generalLinuxPathInput, generalLinuxPathOutput, ModeRuntime.EXECUTOR, ModeExecution.SINGLE);
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void processFile_whenWindows_executor_single() {
        String generalWinPathInput = generalWinPath + File.pathSeparator + INPUT;
        String generalWinPathOutput = generalWinPath + File.pathSeparator + OUTPUT;
        executeGenericTest(generalWinPathInput, generalWinPathOutput, ModeRuntime.EXECUTOR, ModeExecution.SINGLE);
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void processFile_whenWindows_executor_parallel() {
        String generalWinPathInput = generalWinPath + File.pathSeparator + INPUT;
        String generalWinPathOutput = generalWinPath + File.pathSeparator + OUTPUT;
        executeGenericTest(generalWinPathInput, generalWinPathOutput, ModeRuntime.EXECUTOR, ModeExecution.PARALLEL);
    }

    private void executeGenericTest(String generalLinuxPathInput, String generalLinuxPathOutput, ModeRuntime modeRuntime, ModeExecution modeExecution) {
        logic = LogicFactory.getLogic(modeRuntime, modeExecution);
        logic.configure();
        for (String year : years) {
            String yearPathInput = generalLinuxPathInput + File.separatorChar + year;
            String yearPathOutput = generalLinuxPathOutput + File.separatorChar + year;
            logic.execute(yearPathInput, yearPathOutput);
        }
    }

    private long getTime() {
        return Calendar.getInstance().getTime().getTime();
    }
}