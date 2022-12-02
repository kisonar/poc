package kisonar.poc.learning.java.jdk.var;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public final class VarTest {

      @Test
      public void checkTill10() {
            Function<String, String> helloFunction = s -> "Hello " + s;

            var strings = new ArrayList<>();
            strings.add(helloFunction.apply("World"));

            // Inference of generics (Diamond Operator) with anonymous inner classes (Java 9 -> JEP 213)
            Consumer printer = (Consumer<String>) string -> System.out.println(string);
            strings.forEach(printer::accept);
      }

      @Test
      public void checkSince10() {

            // Inference of generic types (List<String>)
            var strings = Arrays.asList("World", "Java 10"); //not mutable list

            // Inference in Loops
            for (var string : strings) {
                  System.out.println("Hello " + string);
            }

            // In combination with the Diamond Operator this leads to the inference of List<Object>
            var mutableList = new ArrayList<>();
            mutableList.add("Hello World");
            for (var string31 : mutableList) {
                  System.out.println(string31.equals("Worldava 10"));
            }

            var anotherMutableList = new ArrayList<String>();
            anotherMutableList.add("Hello World");
            for (var string : anotherMutableList) {
                  System.out.println(string.replace("World", "Java 10"));
            }
      }
}