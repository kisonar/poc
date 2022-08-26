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
                    "asdasdasd",
                    "sadasd",
                    "dsdas", 465);
            mailSender.prepareEmailWithAttqchment(Collections.emptyList());
            mailSender.send();
      }

}
