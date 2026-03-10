package kisonar.poc.learning.java.jdk.oop.extension;

interface BaseInterface {

      default String writeDefault(String input) {
            return "Default for input " + input;
      }

      String process(String input);

      String processExtended(String input);

}
