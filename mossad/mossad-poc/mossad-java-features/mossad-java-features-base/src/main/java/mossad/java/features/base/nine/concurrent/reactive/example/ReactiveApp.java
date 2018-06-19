package mossad.java.features.base.nine.concurrent.reactive.example;

import mossad.java.features.base.nine.concurrent.reactive.example.domain.EmpHelper;
import mossad.java.features.base.nine.concurrent.reactive.example.domain.Employee;
import mossad.java.features.base.nine.concurrent.reactive.example.subscriber.EmployeeSubscriber;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class ReactiveApp {

    public static void main(String args[]) throws InterruptedException {

        // Create Publisher
        SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();

        // Register Subscriber
        EmployeeSubscriber subs = new EmployeeSubscriber();
        publisher.subscribe(subs);

        List<Employee> emps = EmpHelper.getEmps();

        // Publish items
        System.out.println("Publishing Items to Subscriber");
        emps.forEach(i -> publisher.submit(i));

        // logic to wait till processing of all messages are over
        while (emps.size() != subs.getCounter()) {
            Thread.sleep(10);
        }
        // close the Publisher
        publisher.close();

        System.out.println("Exiting the app");

    }

}
