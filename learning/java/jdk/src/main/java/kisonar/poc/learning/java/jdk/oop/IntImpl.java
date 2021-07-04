package kisonar.poc.learning.java.jdk.oop;

public class IntImpl implements Int1WithDefaultWriteAndJustWrite, Int2WithDefaultWrite,
    Int3WithNoDefaultOnlyGo {

  @Override
  public String go() {
    return "Go from IntImpl";
  }

  @Override
  public String write(String input) {
    return "Write from IntImpl";
  }


}
