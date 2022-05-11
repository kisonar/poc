package com.mmigdal.mossad.key.logger.parser.csv;

import com.google.gson.Gson;
import com.mmigdal.mossad.key.logger.parser.csv.line.LineEntry;
import com.mmigdal.mossad.key.logger.parser.csv.line.LineEntryContract;
import com.mmigdal.mossad.key.logger.parser.csv.line.LineEntryEmpty;
import com.opencsv.bean.CsvBindByPosition;

import java.time.Instant;
import java.util.Date;

public class CSVEntry {

      Gson gson = new Gson();
      @CsvBindByPosition(position = 0)
      private Long timeStamp;
      @CsvBindByPosition(position = 1)
      private String textToParse;

      @Override
      public String toString() {
            return new StringBuilder().append("TimeStamp:").append(timeStamp).append("Content: ").append(textToParse).toString();
      }

      public Date getDate() {
            Instant date = Instant.ofEpochMilli(timeStamp);
            return Date.from(date);
      }

      public LineEntryContract getLineEntry() {

            try {
                  return gson.fromJson(textToParse, LineEntry.class);
            }
            catch (Exception ex) {
                  System.out.println("Parsing JSON line  failed: " + textToParse);
            }
            return new LineEntryEmpty();
      }

}
