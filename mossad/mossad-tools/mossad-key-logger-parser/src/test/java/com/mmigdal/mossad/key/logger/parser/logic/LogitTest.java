package com.mmigdal.mossad.key.logger.parser.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LogitTest {


    private FileProcessor fileProcessor;

    @BeforeAll
    public void setUp() {
        fileProcessor = new FileProcessor();
    }

    @Test
    public void processFile() {
        fileProcessor.processFile("");


    }

}
