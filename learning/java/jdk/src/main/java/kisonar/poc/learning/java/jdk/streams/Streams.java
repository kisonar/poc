package kisonar.poc.learning.java.jdk.streams;

import kisonar.platform.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

    private List<User> usersList;

    public Streams() {
        usersList = new ArrayList<>();
    }

    public void addUser(User user) {
        usersList.add(user);
    }

    public List<String> getUsersNames() {
        return usersList.stream().map(user -> user.name()).collect(Collectors.toList());
    }

    public void usageIntStream() {
        IntStream.rangeClosed(1, 13).takeWhile(value -> value < 8 && value % 2 == 0).forEach(System.out::println);
    }

    public void usageStreamOf() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).takeWhile(i -> i < 5)
                .forEach(System.out::println);
    }

}