package com.mmigdal.mossad.key.logger.parser.csv;

import java.util.Date;
import java.util.Optional;

public class LineEntryEmpty implements LineEntryContract {

      public LineEntryEmpty() {
      }

      @Override
      public Optional<DatabasePerformanceEntry> getDatabasePerformanceEntry() {
            return Optional.empty();
      }

      @Override
      public void parseLineForDatabasePerformance(Date date) {
      }
}