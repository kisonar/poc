package kisonar.poc.learning.java.jdk.concurrent.reactive;


import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import kisonar.poc.learning.java.jdk.concurrent.reactive.domain.Employee;
import kisonar.poc.learning.java.jdk.concurrent.reactive.domain.EmployeeFactory;
import kisonar.poc.learning.java.jdk.concurrent.reactive.subscriber.EmployeeSubscriber;
import org.junit.jupiter.api.Test;

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
        employees.forEach(employee -> publisher.submit(employee));

        // logic to wait till processing of all messages are over
        while (employees.size() != subscriber.getCounter()) {
            Thread.sleep(10);
        }
        // close the Publisher
        publisher.close();
    }
}
