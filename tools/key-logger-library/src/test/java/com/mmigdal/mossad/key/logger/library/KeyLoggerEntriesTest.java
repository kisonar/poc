package com.mmigdal.mossad.key.logger.library;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeyLoggerEntriesTest {

    @Test
    public void getKeyLoggerEntriesToFilterOut() {
        List<String> entriesToBeFiltered = KeyLoggerEntries.getStringsToFilterOut();

        assertEquals(22, entriesToBeFiltered.size());
    }
}