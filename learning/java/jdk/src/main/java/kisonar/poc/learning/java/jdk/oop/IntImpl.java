package kisonar.poc.learning.java.jdk.oop;

public class IntImpl implements Int1WithDefaultWriteAndJustWrite, Int2WithDefaultWrite,
        Int3WithNoDefaultOnlyGo {

      private final IntMode mode;

      public IntImpl(IntMode mode) {
            this.mode = mode;
      }

      @Override
      public String write(String input) {
            return switch (mode) {
                  case ONE -> Int1WithDefaultWriteAndJustWrite.super.write(input);
                  case TWO -> Int2WithDefaultWrite.super.write(input);
                  case THREE -> "Three Impl write " + input;
                  default -> "Write default from IntImpl";
            };
      }
}
