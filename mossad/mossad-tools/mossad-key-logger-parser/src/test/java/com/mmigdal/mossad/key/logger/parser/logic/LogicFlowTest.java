package com.mmigdal.mossad.key.logger.parser.logic;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;


public class LogicFlowTest {

    private LogicFlow logicFlow;
    private long startTime;
    private long endTime;

    @BeforeAll
    public static void setUpBeforeAll() {
    }

    @BeforeEach
    public void setUpBeforeEach(){
        startTime = getTime();
    }

    @AfterEach
    public void tearDownAfterEach(){
        endTime = getTime();
        System.out.println(String.format("Execution took: %d ms", (endTime-startTime)));
    }

    @EnabledOnOs(OS.LINUX)
    @Test
    public void processFile_whenLinux() {
        logicFlow = new LogicFlow(
            "/run/media/marcin/MigiBigDisk/repozytoria/repo_test/korpo/toBeDestroyed/tools/wlam/input");
        logicFlow.execute();
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void processFile_whenWindows() {
        logicFlow = new LogicFlow("G:\\repozytoria\\repo_test\\korpo\\toBeDestroyed\\tools\\wlam\\input");
        logicFlow.execute();
    }

    private long getTime (){
        return LocalDateTime.now().getNano();

    }
}
