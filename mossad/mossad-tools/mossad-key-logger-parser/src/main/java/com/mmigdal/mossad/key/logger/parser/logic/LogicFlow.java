package com.mmigdal.mossad.key.logger.parser.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class LogicFlow {

    private final String directoryLocation;
    private FileProcessor fileProcessor;
    private static Logger LOG = Logger.getLogger(LogicFlow.class.getName());

    private static String postfix = ".logging.0";

    public LogicFlow(String directoryLocation) {
        this.directoryLocation = directoryLocation;
        fileProcessor = new FileProcessor();
    }

    public void execute() {
        Path directoryPath = Paths.get(directoryLocation);

        try {
            Stream<Path> filesPaths = Files.list(directoryPath);
            filesPaths.map(path -> path.toString()).filter(path -> path.endsWith(postfix)).forEach(path -> {
                Path source = Paths.get(path);
                Path destination = Paths.get(path.replace(postfix, "").concat(".processed.log"));
                fileProcessor.processFile(source, destination);
            });

        } catch (IOException e) {
            LOG.log(Level.ALL,String.format("Cannot start execution due to %s",e.getMessage()));
        }

    }


}
