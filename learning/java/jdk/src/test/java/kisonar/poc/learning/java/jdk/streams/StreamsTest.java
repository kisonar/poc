package kisonar.poc.learning.java.jdk.streams;

import kisonar.platform.domain.user.UserRecord;
import kisonar.poc.learning.java.jdk.oop.factory.UserFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamsTest {

      private final List<UserRecord> usersList = new ArrayList<>();

      @Test
      public void addUsersAndFetchNames() {
            UserRecord userRecord1 = UserFactory.getUser("1", "Marcin", "Migdal", "password1", "kisonar@wp.pl");
            UserRecord userRecord2 = UserFactory.getUser("2", "Adrian", "Puchalski", "password2", "kisonar@wp.pl");
            usersList.add(userRecord1);
            usersList.add(userRecord2);
            List<String> userNames = usersList.stream().map(UserRecord::name).toList();
            assertEquals(2, userNames.size());
      }

      @Test
      public void intStream() {
            IntStream.rangeClosed(1, 13).takeWhile(value -> value < 8 && value % 2 == 0).forEach(System.out::println);
      }

      @Test
      public void streamOfInts() {
            Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).takeWhile(i -> i < 5)
                    .forEach(System.out::println);
      }

      @Test
      public void reduceAsSumOfInts() {
            var result = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11).reduce(0, Integer::sum);
            assertEquals(66, result);
      }

      @Test
      public void reduceAsSilnia() {
            var result = Stream.of(1, 2, 3, 4).reduce(1,
                    (val1, val2) ->
                            val1 * val2
            );
            assertEquals(24, result);

            var resultForTwo = Stream.of(1, 2, 3, 4).reduce(2,
                    (val1, val2) ->
                            val1 * val2
            );
            assertEquals(48, resultForTwo);

      }

      @Test
      public void reduceAsTest() {
            var result = Stream.of(1, 2, 3, 4).reduce(1,
                    (accResult, val2) ->
                            accResult < val2 ?
                                    accResult + val2 :
                                    accResult * val2);
            assertEquals(36, result);
      }
}