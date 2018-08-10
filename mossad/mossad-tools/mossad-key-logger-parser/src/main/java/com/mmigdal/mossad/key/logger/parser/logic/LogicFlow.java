package com.mmigdal.mossad.key.logger.parser.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LogicFlow {

    private final String directoryLocation;
    private FileProcessor fileProcessor;

    public LogicFlow(String directoryLocation) {
        this.directoryLocation = directoryLocation;
        fileProcessor = new FileProcessor();
    }

    public void execute() {
        Path directoryPath = Paths.get(directoryLocation);

        try {
            Stream<Path> filesPaths = Files.list(directoryPath);
            filesPaths.map(path -> path.toString()).filter(path -> path.endsWith(".log")).forEach(path -> {
                Path source = Paths.get(path);
                Path destination = Paths.get(path.replace(".log", "").concat(".processed.log"));
                fileProcessor.processFile(source, destination);
            });


        } catch (IOException e) {
        }


    }


}
