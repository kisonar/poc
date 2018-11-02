package com.mmigdal.mossad.key.logger.parser.logic;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;


public class LogicFlowTest {

    private final static String INPUT = "input";
    private final static String OUTPUT = "output";
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
    public void processFile_whenLinux() {
        String generalLinuxPath = "/run/media/marcin/MigiBigDisk/repozytoria/repo_test/korpo/toBeDestroyed/tools/wlam/";
        inputPath = generalLinuxPath + INPUT;
        outputPath = generalLinuxPath + OUTPUT;
        logicFlow = new LogicFlow(
            inputPath,
            outputPath);
        logicFlow.execute();
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void processFile_whenWindows() {
        String generalWindowsPath = "G:\\repozytoria\\repo_test\\korpo\\toBeDestroyed\\tools\\wlam\\";
        inputPath = generalWindowsPath + INPUT;
        outputPath = generalWindowsPath + OUTPUT;
        logicFlow = new LogicFlow(
            inputPath,
            outputPath);
        logicFlow.execute();
    }

    private long getTime() {
        return LocalDateTime.now().getNano();
    }
}
