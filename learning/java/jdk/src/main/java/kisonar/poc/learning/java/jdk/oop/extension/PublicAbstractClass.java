package kisonar.poc.learning.java.jdk.oop.extension;

public abstract class PublicAbstractClass implements BaseInterface {

      @Override
      public String process(String input) {
            return "Process " + input;
      }
}