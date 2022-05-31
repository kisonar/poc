package com.mmigdal.mossad.key.logger.parser.csv;

import java.util.Date;

public class DatabasePerformanceEntry {

      public final Date date;
      public final String time;
      public final String dao;
      public final String method;

      public DatabasePerformanceEntry(Date date, String time, String dao, String method) {
            this.date = date;
            this.time = time;
            this.dao = dao;
            this.method = method;
      }

      @Override
      public String toString() {
            return "DatabasePerformanceEntry{" +
                    "date='" + date + '\'' +
                    "time='" + time + '\'' +
                    ", dao='" + dao + '\'' +
                    ", method='" + method + '\'' +
                    '}';
      }
}