package com.mmigdal.mossad.key.logger.parser.csv;

import com.mmigdal.mossad.key.logger.parser.csv.line.LineEntryContract;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mmigdal.mossad.key.logger.parser.csv.DaosAndDefinitions.getExtractors;

public class Main {

      public static void main(String[] args) throws IOException {

            String fileName = "/Users/marcin/development/logging/log-events-viewer-result-1c03adcf643f4f6ebe232a118e6eff26-rocco.csv";

            List<CSVEntry> beans = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(CSVEntry.class).withSkipLines(1)
                    .build()
                    .parse();

            List<DatabasePerformanceEntry> databasePerformanceEntryList = new ArrayList<>();

            beans.forEach(csvEntry -> {
                  LineEntryContract lineEntry = csvEntry.getLineEntry();
                  lineEntry.parseLineForDatabasePerformance(csvEntry.getDate());
                  Optional<DatabasePerformanceEntry> databasePerformanceEntry = lineEntry.getDatabasePerformanceEntry();
                  databasePerformanceEntry.ifPresent(item -> databasePerformanceEntryList.add(item));
            });

            List<DataExtractor> extractors = getExtractors();

            extractors.forEach(
                    item -> {
                          item.extractData(List.copyOf(databasePerformanceEntryList));
                          item.generateStatistics();
                    }
            );
      }
}