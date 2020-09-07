package com.mmigdal.mossad.key.logger.parser.logic;

import java.util.Calendar;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import java.util.List;

public class LogicFlowTest {

    private final static List<String> years = List.of("2018","2019","2020");
    private LogicFlow logicFlow;
    private long startTime;
    private long endTime;
    private String yearPath;

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
        for ( String year: years) {
            yearPath = generalLinuxPath + year;
            logicFlow = new LogicFlow(
                yearPath,
                yearPath);
            logicFlow.execute();
        }
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void processFile_whenWindows_2020() {
        String generalWindowsPath = "G:\\repozytoria\\repo_test\\korpo\\toBeDestroyed\\tools\\wlam\\output\\";
        for ( String year: years) {
            yearPath = generalWindowsPath + year;
            logicFlow = new LogicFlow(
                yearPath,
                yearPath);
            logicFlow.execute();
        }
    }

    private long getTime() {
        return Calendar.getInstance().getTime().getTime();
        //return LocalDateTime.now().getSecond();
    }
}