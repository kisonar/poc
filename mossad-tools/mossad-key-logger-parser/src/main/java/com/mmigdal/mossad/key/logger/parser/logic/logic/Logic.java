package com.mmigdal.mossad.key.logger.parser.logic.logic;

import com.mmigdal.mossad.key.logger.parser.logic.model.Item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public interface Logic {

    void configure();

    void execute(String inputPathString, String outputPathString);

    default List<Item> getItems(Path inputPath, Path outputPath) throws IOException {
        Stream<Path> filesPaths = Files.list(inputPath).sorted();
        List<Item> items = new ArrayList<>();
        filesPaths.forEach(filePath -> {
            String sourceFileName = filePath.toFile().getName();
            Path destinationFilePath = Paths.get(outputPath.toString(), sourceFileName);
            items.add(new Item(filePath, destinationFilePath));
        });
        return items;
    }
}