package kisonar.poc.learning.java.jdk.streams;

import kisonar.platform.domain.user.User;
import kisonar.platform.domain.user.factory.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StreamsTest {

    private Streams streams;

    @BeforeEach
    public void setUp() {
        streams = new Streams();
    }

    @Test
    public void usageCheck() {
        streams.usageStreamOf();
        streams.usageIntStream();
    }

    @Test
    public void addUsersAndFetchNames() {
        User user1 = UserFactory.getUser("1", "Marcin", "Migdal", "password1","kisonar@wp.pl");
        User user2 = UserFactory.getUser("2", "Adrian", "Puchalski", "password2","kisonar@wp.pl");
        streams.addUser(user1);
        streams.addUser(user2);

        List<String> userNames = streams.getUsersNames();

        Assertions.assertEquals(2, userNames.size());
    }
}