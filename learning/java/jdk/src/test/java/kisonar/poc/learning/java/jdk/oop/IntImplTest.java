package kisonar.poc.learning.java.jdk.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntImplTest {

      private final String message = "Go";

      @Test
      void testInt1() {
            Int1WithDefaultWriteAndJustWrite cut = new IntImpl(IntMode.ONE);

            var response = cut.write(message);

            assertEquals("One write Go", response);
      }

      @Test
      void testInt2() {
            Int2WithDefaultWrite cut = new IntImpl(IntMode.TWO);

            var response = cut.write(message);

            assertEquals("Two write  Go", response);
      }

      @Test
      void testInt3() {
            Int3WithNoDefaultOnlyGo cut = new IntImpl(IntMode.THREE);

            var response = cut.write(message);

            assertEquals("Three Impl write Go", response);
      }

      @Test
      void testDefault() {
            Int3WithNoDefaultOnlyGo cut = new IntImpl(IntMode.DEFAULT);

            var response = cut.write(message);

            assertEquals("Write default from IntImpl", response);
      }
}