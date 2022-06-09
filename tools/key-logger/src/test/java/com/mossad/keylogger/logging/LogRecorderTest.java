package com.mossad.keylogger.logging;

import org.junit.jupiter.api.Test;

public class LogRecorderTest {

      @Test
      public void writeContent() {
            LogRecorder logRecorder = new LogRecorder();
            logRecorder.writeContent("Something");
      }

}
