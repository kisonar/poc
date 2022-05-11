package com.mmigdal.mossad.key.logger.parser.csv;

import com.mmigdal.mossad.key.logger.parser.csv.line.LineEntryContract;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mmigdal.mossad.key.logger.parser.csv.DaosDefenitions.CHARGE_POINT_STATUS_DAO;
import static com.mmigdal.mossad.key.logger.parser.csv.DaosDefenitions.CIRCUIT_BREAKER_DAO;
import static com.mmigdal.mossad.key.logger.parser.csv.DaosDefenitions.CONNECTOR_STATUS_DAO;
import static com.mmigdal.mossad.key.logger.parser.csv.DaosDefenitions.STATUS_LOOKUP_DAO;
import static com.mmigdal.mossad.key.logger.parser.csv.DaosDefenitions.STATUS_VIEW_DAO;

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
            extractors = arm(extractors, CHARGE_POINT_STATUS_DAO, DaosDefenitions.ChargePointStatusDaoMapping);
            extractors = arm(extractors, STATUS_VIEW_DAO, DaosDefenitions.StatusViewDaoMapping);
            extractors = arm(extractors, STATUS_LOOKUP_DAO, DaosDefenitions.StatusLookupDaoMapping);
            extractors = arm(extractors, CONNECTOR_STATUS_DAO, DaosDefenitions.ConnectorStatusDaoMapping);
            extractors = arm(extractors, CIRCUIT_BREAKER_DAO, DaosDefenitions.CircuirBrakerDaoMapping);

            extractors.forEach(
                    item -> {
                          item.extractData(List.copyOf(databasePerformanceEntryList));
                          item.generateStatistics();
                    }
            );
      }

      public static List<DataExtractor> arm(List<DataExtractor> extractors, String daoname, List<String> mapping) {
            mapping.forEach(
                    item -> {
                          extractors.add(new DataExtractor(daoname, item));
                    }
            );
            return extractors;
      }
}