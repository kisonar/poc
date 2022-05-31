package com.mmigdal.mossad.key.logger.parser.csv.line;

import com.mmigdal.mossad.key.logger.parser.csv.DatabasePerformanceEntry;

import java.util.Date;
import java.util.Optional;

public interface LineEntryContract {

      Optional<DatabasePerformanceEntry> getDatabasePerformanceEntry();

      void parseLineForDatabasePerformance(Date date);
}