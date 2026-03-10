package kisonar.poc.learning.java.jdk.oop;

public interface Int1WithDefaultWriteAndJustWrite {

      default String write(String input) {
            return "One write " + input;
      }

}