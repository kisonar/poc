package com.mmigdal.mossad.key.logger.parser.csv;

import java.util.ArrayList;
import java.util.List;

public class DataExtractor {

       private final String daoName;
       private final String methodName;
       private final List<DatabasePerformanceEntry> entries = new ArrayList<>();

       private double average = 0;
       private int max = 0;
       private int min = 0;

       DataExtractor(String daoName, String methodName) {
             this.daoName = daoName;
             this.methodName = methodName;
       }


       public void extractData(List<DatabasePerformanceEntry> inputEntries) {
             var filtered = inputEntries.stream()
                   .filter(item -> item.dao.equals(daoName))
                   .filter(item -> item.method.equals(methodName))
                   .toList();
             entries.addAll(filtered);
       }

       public void generateStatistics() {
             average = entries.stream().mapToInt(item -> Integer.parseInt(item.time)).average().orElse(0);
             max = entries.stream().mapToInt(item -> Integer.parseInt(item.time)).max().orElse(0);
             min = entries.stream().mapToInt(item -> Integer.parseInt(item.time)).min().orElse(0);
             System.out.println("%s: %s : items: %d: min: %d average: %.2f max: +%d".formatted(daoName, methodName, entries.size(), min, average, max));
       }

}
