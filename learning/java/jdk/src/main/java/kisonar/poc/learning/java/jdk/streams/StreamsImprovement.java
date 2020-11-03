package kisonar.poc.learning.java.jdk.streams;

import java.util.stream.Stream;

public final class StreamsImprovement {

    private StreamsImprovement() {
    }

    public static void usageCheck() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).takeWhile(i -> i < 5)
                .forEach(System.out::println);
    }
}