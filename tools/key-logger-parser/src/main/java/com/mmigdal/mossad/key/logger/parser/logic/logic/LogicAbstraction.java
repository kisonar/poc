package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.model.Item;
import com.mmigdal.mossad.key.logger.parser.logic.model.mode.ModeExecution;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class LogicAbstraction implements Logic {

      private static final Logger LOG = Logger.getLogger(LogicAbstraction.class.getName());
      private final List<Item> items;
      protected ModeExecution modeExecution;

      public LogicAbstraction(ModeExecution modeExecution) {
            this.modeExecution = modeExecution;
            this.items = new ArrayList<>();
      }

       public void prepare(String inputDirectory, String outputDirectory, List<String> years) {
             years.forEach(year -> {
                   var inputYear = createYearLocation(inputDirectory, year);
                   var outputYear = createYearLocation(outputDirectory, year);
                   var input = Path.of(inputYear);
                   var output = Path.of(outputYear);
                   if (input.toFile().isDirectory() && output.toFile().isDirectory()) {
                         try {
                               items.addAll(readItems(input, output));
                         }
                         catch (IOException e) {
                               LOG.log(Level.WARNING, "Cannot read items for %s %s".formatted(inputYear, outputYear));
                         }
                   } else {
                         LOG.log(Level.WARNING, "One of locations is not a directory %s %s".formatted(input, output));
                   }
             });
       }

      public List<Item> getItems() {
            return Collections.unmodifiableList(items);
      }

      private String createYearLocation(String inputPath, String year) {
            return inputPath + File.separatorChar + year;
      }

}