package kisonar.poc.learning.java.jdk.vars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public final class VarExample {

  public void checkTill10() {
    // Type inference bei Parametern von Lambda Ausdrücken (Java 8)
    Function<String, String> helloFunction = s -> "Hello " + s;

    // Inference of generics (Diamond Operator, since Java 7)
    List strings = new ArrayList<>();
    strings.add(helloFunction.apply("World"));

    // Inference of generics (Diamond Operator) with anonymous inner classes (Java 9 -> JEP 213)
    Consumer printer = (Consumer<String>) string -> System.out.println(string);
    strings.forEach(printer::accept);

  }

  public void checkSince10() {

    // Inference of generic types (List<String>)
    var strings = Arrays.asList("World", "Java 10");

    // Inference in Loops
    for (var string : strings) {
      System.out.println("Hello " + string);
    }

    // In combination with the Diamond Operator this leads to the inference of List<Object>
    var strings3 = new ArrayList<>();
    strings.add("Hello World");
    for (var string31 : strings3) {
      System.out.println(string31.equals("Worldava 10"));
    }

    var strings2 = new ArrayList<String>();
    strings2.add("Hello World");
    for (var string : strings2) {
      System.out.println(string.replace("World", "Java 10"));
    }
  }
}