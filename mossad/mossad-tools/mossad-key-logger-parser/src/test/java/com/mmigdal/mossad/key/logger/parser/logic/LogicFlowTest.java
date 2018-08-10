package com.mmigdal.mossad.key.logger.parser.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LogicFlowTest {


    private LogicFlow logicFlow;

    @BeforeAll
    public static void setUp() {

    }

    @Test
    public void processFile() {
        logicFlow = new LogicFlow("/home/marcin/private");
        logicFlow.execute();

    }

}
