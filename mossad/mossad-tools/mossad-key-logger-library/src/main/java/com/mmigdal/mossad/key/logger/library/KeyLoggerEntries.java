package com.mmigdal.mossad.key.logger.library;

import java.util.List;

public final class KeyLoggerEntries {

    public static List<String> getStringsToFilterOut() {
        return List.of("INFO:", "Backspace", "Down", "Enter", "Up", "Volume", "Right", "Left", "Back", "Space", "Shift",
            "Escape", SHIFT_PRESSED, SHIFT_RELEASED);
    }


    public final static String SHIFT_PRESSED= "shift_pressed";
    public final static String SHIFT_RELEASED = "shift_released";



}
