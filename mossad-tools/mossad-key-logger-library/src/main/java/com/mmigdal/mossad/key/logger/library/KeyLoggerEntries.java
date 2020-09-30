package com.mmigdal.mossad.key.logger.library;

import java.util.List;

public final class KeyLoggerEntries {

    public final static String SHIFT_PRESSED = "shift_pressed";
    public final static String SHIFT_RELEASED = "shift_released";
    public final static String SIGN_SPACE = " ";
    public final static String SIGN_EMPTY = "";

    public static List<String> getStringsToFilterOut() {
        return List.of("INFO:", "Backspace", "Down", "Enter", "Up", "Volume", "Right", "Left", "Back", "Space", "Shift",
            "Escape", "Ctrl", "Alt", "Semiolon", "Home", "Delete", "Semicolon", "Comma", "Tab", "Caps", "Lock");
    }

}
