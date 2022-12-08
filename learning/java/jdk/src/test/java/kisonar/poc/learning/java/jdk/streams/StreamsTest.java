package kisonar.poc.learning.java.jdk.streams;

import kisonar.platform.domain.user.User;
import kisonar.platform.domain.user.factory.UserFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamsTest {

      private final List<User> usersList = new ArrayList<>();

      @Test
      public void addUsersAndFetchNames() {
            User user1 = UserFactory.getUser("1", "Marcin", "Migdal", "password1", "kisonar@wp.pl");
            User user2 = UserFactory.getUser("2", "Adrian", "Puchalski", "password2", "kisonar@wp.pl");
            usersList.add(user1);
            usersList.add(user2);
            List<String> userNames = usersList.stream().map(User::name).collect(Collectors.toList());
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
            var result = Stream.of(1, 2, 3, 4).reduce(1, (val1, val2) -> val1 * val2);
            assertEquals(24, result);
      }

      @Test
      public void reduceAsTest() {
            var result = Stream.of(1, 2, 3, 4).reduce(1, (val1, val2) -> val1 < val2 ? val1 + val2 : val1 * val2);
            assertEquals(36, result);
      }
}