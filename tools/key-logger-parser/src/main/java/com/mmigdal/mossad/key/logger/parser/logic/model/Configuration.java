package com.mmigdal.mossad.key.logger.parser.logic.model;

import java.util.List;

public final class Configuration {

    public final static String INPUT = "input";
    public final static String OUTPUT = "output";
    public final static List<String> YEARS = List.of("2018", "2019", "2020", "2021","2022");

    public final static String LOG_RECORDER = "com.mossad.keylogger.logging.LogRecorder";

    private Configuration() {
    }
}