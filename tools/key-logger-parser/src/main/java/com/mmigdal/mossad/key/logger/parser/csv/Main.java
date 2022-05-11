package com.mmigdal.mossad.key.logger.parser.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mmigdal.mossad.key.logger.parser.csv.DaosDefenitions.DAO_CHARGE_POINT_STATUS;
import static com.mmigdal.mossad.key.logger.parser.csv.DaosDefenitions.DAO_CHARGE_POINT_STATUS_upsertOnline;
import static com.mmigdal.mossad.key.logger.parser.csv.DaosDefenitions.STATUS_VIEW_DAO;
import static com.mmigdal.mossad.key.logger.parser.csv.DaosDefenitions.STATUS_VIEW_DAO_findByChargePointId;
import static com.mmigdal.mossad.key.logger.parser.csv.DaosDefenitions.STATUS_VIEW_DAO_findByConnectorId;

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

            DataExtractor dataExtractorChargePointStatusDaoUpsertOnline = new DataExtractor(DAO_CHARGE_POINT_STATUS, DAO_CHARGE_POINT_STATUS_upsertOnline);
            dataExtractorChargePointStatusDaoUpsertOnline.extractData(List.copyOf(databasePerformanceEntryList));
            dataExtractorChargePointStatusDaoUpsertOnline.generateStatistics();

            DataExtractor dataExtractorStatusViewDaoFindByChargePointId = new DataExtractor(STATUS_VIEW_DAO, STATUS_VIEW_DAO_findByChargePointId);
            dataExtractorStatusViewDaoFindByChargePointId.extractData(List.copyOf(databasePerformanceEntryList));
            dataExtractorStatusViewDaoFindByChargePointId.generateStatistics();

            DataExtractor dataExtractorStatusViewDaoFindByConnectorId = new DataExtractor(STATUS_VIEW_DAO, STATUS_VIEW_DAO_findByConnectorId);
            dataExtractorStatusViewDaoFindByConnectorId.extractData(List.copyOf(databasePerformanceEntryList));
            dataExtractorStatusViewDaoFindByConnectorId.generateStatistics();
      }

}