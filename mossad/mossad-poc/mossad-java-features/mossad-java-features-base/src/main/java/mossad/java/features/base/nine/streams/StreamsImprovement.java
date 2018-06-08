package mossad.java.features.base.nine.streams;

import java.util.stream.Stream;

public final class StreamsImprovement {

    private StreamsImprovement(){

    }

    public static void check(){

        Stream.of(1,2,3,4,5,6,7,8,9,10).takeWhile(i -> i < 5 )
                .forEach(System.out::println);
    }

}
