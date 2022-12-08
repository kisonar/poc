package kisonar.poc.learning.java.jdk.oop;

import org.junit.jupiter.api.Test;

public class OopTest {

      @Test
      void testInt1() {
            Int1WithDefaultWriteAndJustWrite object = new IntImpl();
            object.justWrite("JustWrite");
            object.write("Write");
      }

      @Test
      void testInt2() {
            Int2WithDefaultWrite object = new IntImpl();
            object.write("Write");
      }

      @Test
      void testInt3() {
            Int3WithNoDefaultOnlyGo object = new IntImpl();
            object.go();
      }
}