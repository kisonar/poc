package kisonar.poc.learning.java.jdk.oop;

public interface Int2WithDefaultWrite {

      default String write(String input) {
            return "Two write  " + input;
      }
}