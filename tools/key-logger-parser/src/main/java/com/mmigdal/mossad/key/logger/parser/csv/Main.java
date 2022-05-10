package com.mmigdal.mossad.key.logger.parser.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

      private static final String ChargePointStatusDao = "ChargePointStatusDao";
      private static final String upsertOnline = "upsertOnline";
      private static final String StatusViewDao = "StatusViewDao";
      private static final String findByChargePointId = "findByChargePointId";
      private static final String findByConnectorId = "findByConnectorId";
      private static final String PublishingService = "PublishingService";
      private static final String publishEvent = "publishEvent";
      private static final String CircuitBreakerDao = "CircuitBreakerDao";
      private static final String findByChargePointAndConnectorId = "findByChargePointAndConnectorId";
      private static final String ConnectorStatusDao = "ConnectorStatusDao";
      private static final String insertStatusNotification = "insertStatusNotification";
      private static final String ConnectorErrorDao = "ConnectorErrorDao";
      private static final String deleteByConnectorId = "deleteByConnectorId";

      public static void main(String[] args) throws IOException {

            String fileName = "/Users/marcin/development/logging/log-events-viewer-result-1c03adcf643f4f6ebe232a118e6eff26-rocco.csv";

            List<CSVEntry> beans = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(CSVEntry.class).withSkipLines(1)
                    .build()
                    .parse();

            List<DatabasePerformanceEntry> databasePerformanceEntryList = new ArrayList<>();

            beans.forEach(csvEntry -> {
                  LineEntry lineEntry = csvEntry.getLineEntry();
                  lineEntry.parseLineForDatabasePerformance(csvEntry.getDate());
                  Optional<DatabasePerformanceEntry> databasePerformanceEntry = lineEntry.getDatabasePerformanceEntry();
                  databasePerformanceEntry.ifPresent(item -> databasePerformanceEntryList.add(item));
            });

            DataExtractor dataExtractorChargePointStatusDaoUpsertOnline = new DataExtractor(ChargePointStatusDao, upsertOnline);
            dataExtractorChargePointStatusDaoUpsertOnline.extractData(List.copyOf(databasePerformanceEntryList));
            dataExtractorChargePointStatusDaoUpsertOnline.generateStatistics();

            DataExtractor dataExtractorStatusViewDaoFindByChargePointId = new DataExtractor(StatusViewDao, findByChargePointId);
            dataExtractorStatusViewDaoFindByChargePointId.extractData(List.copyOf(databasePerformanceEntryList));
            dataExtractorStatusViewDaoFindByChargePointId.generateStatistics();

            DataExtractor dataExtractorStatusViewDaoFindByConnectorId = new DataExtractor(StatusViewDao, findByConnectorId);
            dataExtractorStatusViewDaoFindByConnectorId.extractData(List.copyOf(databasePerformanceEntryList));
            dataExtractorStatusViewDaoFindByConnectorId.generateStatistics();
      }

}