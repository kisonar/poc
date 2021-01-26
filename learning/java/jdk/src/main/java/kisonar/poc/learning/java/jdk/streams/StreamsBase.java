package kisonar.poc.learning.java.jdk.streams;

import kisonar.platform.domain.User;

import java.util.List;
import java.util.stream.IntStream;

public class StreamsBase {

    List<User> users;

    public StreamsBase() {

    }

    public void usageIntStream() {
        IntStream.rangeClosed(1, 13).takeWhile(value -> value < 8 && value % 2 == 0).forEach(value -> System.out.println(value));
    }


}
