package com.mmigdal.mossad.key.logger.parser.cli;

import com.mmigdal.mossad.key.logger.parser.logic.Logic;
import com.mmigdal.mossad.key.logger.parser.logic.Mode;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException("Insufficient number of input parameters");
        }

        Logic logic = new Logic(Mode.PARALLEL);
        logic.execute(args[0], args[1]);
    }
}
