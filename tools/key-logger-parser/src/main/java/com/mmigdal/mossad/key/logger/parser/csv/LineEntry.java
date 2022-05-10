package com.mmigdal.mossad.key.logger.parser.csv;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineEntry {

      public String timestamp;
      public String message;
      private DatabasePerformanceEntry DatabasePerformanceEntry;

      @Override
      public String toString() {
            return "LineEntry{" +
                    "timestamp='" + timestamp + '\'' +
                    "message='" + message + '\'' +
                    '}';
      }

      public void parseLineForDatabasePerformance(Date date) {
            String regexp = ".+?(?=with)";
            Pattern pattern = Pattern.compile(regexp);
            if (message != null) {
                  Matcher matcher = pattern.matcher(message);
                  if (matcher.find()) {
                        String[] entries = matcher.group(0).split(" ");
                        DatabasePerformanceEntry = new DatabasePerformanceEntry(date, entries[2], entries[6], entries[9]);
                  } else {
                        System.out.println("No entry related to DB perfomrance");
                  }
            }

      }

      public Optional<DatabasePerformanceEntry> getDatabasePerformanceEntry() {
            return Optional.ofNullable(DatabasePerformanceEntry);
      }

      public boolean notEmpty() {
            return timestamp != null && message != null;
      }

}