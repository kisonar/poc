package kisonar.poc.learning.java.jdk.oop;

public interface Int1WithDefaultWriteAndJustWrite {

  default String write(String input) {
    return "Int1WithDefaultWriteAndJustWrite " + input;
  }

  default String justWrite(String input) {
    return "Int1WithDefaultWriteAndJustWrite " + input;
  }

}
