package com.mmigdal.mossad.key.logger.parser.logic;

import java.util.Calendar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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

    //@Disabled
    @Test
    public void processFile_whenLinux() {
        logicFlow = new LogicFlow(
            "/run/media/marcin/MigiBigDisk/repozytoria/repo_test/korpo/toBeDestroyed/tools/wlam/input");
        logicFlow.execute();
    }

    @Disabled
    @Test
    public void processFile_whenWindows() {
        logicFlow = new LogicFlow("G:\\repozytoria\\repo_test\\korpo\\toBeDestroyed\\tools\\wlam\\input");
        logicFlow.execute();
    }

    private long getTime (){
        return Calendar.getInstance().getTimeInMillis();
    }
}
