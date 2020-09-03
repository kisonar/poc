package com.mmigdal.mossad.key.logger.parser.logic;

import java.util.Calendar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;


public class LogicFlowTest {

    private final static String INPUT_2020 = "2020";
    private final static String OUTPUT_2000 = "2020";
    private LogicFlow logicFlow;
    private long startTime;
    private long endTime;
    private String inputPath;
    private String outputPath;

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
        String generalLinuxPath = "/run/media/marcin/MigiBigDisk/repozytoria/repo-arbeit-client/trunk/korpo/toBeDestroyed/tools/wlam/input/";
        inputPath = generalLinuxPath + INPUT_2020;
        outputPath = generalLinuxPath + OUTPUT_2000;
        logicFlow = new LogicFlow(
            inputPath,
            outputPath);
        logicFlow.execute();
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void processFile_whenWindows_2020() {
        String generalWindowsPath = "G:\\repozytoria\\repo_test\\korpo\\toBeDestroyed\\tools\\wlam\\output\\";
        inputPath = generalWindowsPath + INPUT_2020;
        outputPath = generalWindowsPath + OUTPUT_2000;
        logicFlow = new LogicFlow(
            inputPath,
            outputPath);
        logicFlow.execute();
    }

    private long getTime() {

        return Calendar.getInstance().getTime().getTime();
        //return LocalDateTime.now().getSecond();
    }
}
