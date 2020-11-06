package com.mmigdal.mossad.key.logger.parser.logic.others;

import java.io.File;

import static com.mmigdal.mossad.key.logger.parser.logic.model.Configuration.INPUT;
import static com.mmigdal.mossad.key.logger.parser.logic.model.Configuration.OUTPUT;

public final class TestingConfiguration {

    public final static String GENERAL_PATH_LINUX = "/run/media/marcin/MigiBigDisk/repozytoria/repo-arbeit-client/trunk/korpo/toBeDestroyed/tools/wlam";
    public final static String GENERAL_PATH_WIN = "G:\\repozytoria\\repo_test\\korpo\\toBeDestroyed\\tools\\wlam\\output\\";
    public final static String LINUX_PATH_FQ_INPUT = GENERAL_PATH_LINUX + File.separatorChar + INPUT;
    public final static String LINUX_PATH_FQ_OUTPUT = GENERAL_PATH_LINUX + File.separatorChar + OUTPUT;
    public final static String WIN_PATH_FQ_INPUT = GENERAL_PATH_WIN + File.pathSeparator + INPUT;
    public final static String WIN_PATH_FQ_OUTPUT = GENERAL_PATH_WIN + File.pathSeparator + OUTPUT;

    private TestingConfiguration() {
    }
}