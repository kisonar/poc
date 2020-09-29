package com.mmigdal.mossad.key.logger.parser.logic;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class LogicFlowTest {

    private final static List<String> years = List.of("2018", "2019", "2020");
    private LogicFlow logicFlow;
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
    public void processFile_whenLinux_2020() {
        String generalLinuxPathInput = generalLinuxPath + File.separatorChar + INPUT;
        String generalLinuxPathOutput = generalLinuxPath + File.separatorChar + OUTPUT;
        executeGeneritTest(generalLinuxPathInput, generalLinuxPathOutput);
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void processFile_whenWindows_2020() {
        String generalWinPathInput = generalWinPath + File.pathSeparator + INPUT;
        String generalWinPathOutput = generalWinPath + File.pathSeparator + OUTPUT;
        executeGeneritTest(generalWinPathInput, generalWinPathOutput);
    }

    private void executeGeneritTest(String generalLinuxPathInput, String generalLinuxPathOutput) {
        for (String year : years) {
            String yearPathInput = generalLinuxPathInput + File.separatorChar + year;
            String yearPathOutput = generalLinuxPathOutput + File.separatorChar + year;
            logicFlow = new LogicFlow(
                yearPathInput,
                yearPathOutput);
            logicFlow.execute();
        }
    }

    private long getTime() {
        return Calendar.getInstance().getTime().getTime();
        //return LocalDateTime.now().getSecond();
    }
}