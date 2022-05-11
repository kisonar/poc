package com.mmigdal.mossad.key.logger.parser.csv;

import java.util.Date;
import java.util.Optional;

interface LineEntryContract {

      Optional<DatabasePerformanceEntry> getDatabasePerformanceEntry();

      void parseLineForDatabasePerformance(Date date);
}