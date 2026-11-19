package kisonar.poc.learning.java.jdk.concurrent.reactive;


import kisonar.platform.domain.user.Employee;
import kisonar.platform.domain.user.factory.EmployeeFactory;
import kisonar.poc.learning.java.jdk.concurrent.reactive.subscriber.EmployeeSubscriber;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class ReactiveTest {

    @Test
    public void logic() throws InterruptedException {
        // Create Publisher
        SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();

        // Register Subscriber
        EmployeeSubscriber subscriber = new EmployeeSubscriber();
        publisher.subscribe(subscriber);

        List<Employee> employees = EmployeeFactory.getEmployees();

        // Publish items
        System.out.println("Publishing Items to Subscriber");
        employees.forEach(publisher::submit);

        // logic to wait till processing of all messages are over
        while (employees.size() != subscriber.getCounter()) {
            Thread.sleep(10);
        }
        // close the Publisher
        publisher.close();
    }
}
