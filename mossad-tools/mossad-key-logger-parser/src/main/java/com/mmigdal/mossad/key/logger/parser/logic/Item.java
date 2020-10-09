package com.mmigdal.mossad.key.logger.parser.logic;

import java.nio.file.Path;

public final class Item {

    public final Path input;
    public final Path output;

    public Item(Path input, Path output) {
        this.input = input;
        this.output = output;
    }
}
