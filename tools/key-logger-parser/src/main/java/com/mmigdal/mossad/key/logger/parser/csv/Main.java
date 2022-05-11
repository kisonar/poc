package com.mmigdal.mossad.key.logger.parser.csv;

import com.mmigdal.mossad.key.logger.parser.csv.line.LineEntryContract;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mmigdal.mossad.key.logger.parser.csv.DaosAndDefinitions.*;

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

            List<DataExtractor> extractors = new ArrayList<>();
            extractors = armExtractors(extractors, CHARGE_POINT_STATUS_DAO, ChargePointStatusDaoMapping);
            extractors = armExtractors(extractors, STATUS_VIEW_DAO, StatusViewDaoMapping);
            extractors = armExtractors(extractors, STATUS_LOOKUP_DAO, StatusLookupDaoMapping);
            extractors = armExtractors(extractors, CONNECTOR_STATUS_DAO, ConnectorStatusDaoMapping);
            extractors = armExtractors(extractors, CIRCUIT_BREAKER_DAO, CircuitBreakerDaoMapping);
            extractors = armExtractors(extractors, CONNECTOR_ERROR_DAO, connectorErrorDaoMapping);
            extractors = armExtractors(extractors, CHARGE_POINT_ERROR_DAO, chargePointErrorDaoMapping);
            extractors = armExtractors(extractors, PUBLISHING_SERVICE, publishingServiceMapping);

            extractors.forEach(
                    item -> {
                          item.extractData(List.copyOf(databasePerformanceEntryList));
                          item.generateStatistics();
                    }
            );
      }

}