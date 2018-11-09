package mossad.java.features.base.nine.concurrent.reactive.example;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import mossad.java.features.base.nine.concurrent.reactive.example.domain.Employee;
import mossad.java.features.base.nine.concurrent.reactive.example.domain.EmployeeFactory;
import mossad.java.features.base.nine.concurrent.reactive.example.domain.Freelancer;
import mossad.java.features.base.nine.concurrent.reactive.example.subscriber.FreelancerSubscriber;

public class ReactiveAppWithProcessor {

    public static void main(String[] args) throws InterruptedException {
        // Create End Publisher
        SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();

        // Create Processor
        MyProcessor transformProcessor = new MyProcessor(s -> {
            return new Freelancer(s.getId(), s.getId() + 100, s.getName());
        });

        //Create End Subscriber
        FreelancerSubscriber freelanceSubscriber = new FreelancerSubscriber();

        //Create chain of publisher, processor and subscriber
        publisher.subscribe(transformProcessor); // publisher to processor
        transformProcessor.subscribe(freelanceSubscriber); // processor to subscriber

        List<Employee> emps = EmployeeFactory.getEmployees();

        // Publish items
        System.out.println("Publishing Items to Subscriber");
        emps.forEach(i -> publisher.submit(i));

        // Logic to wait for messages processing to finish
        while (emps.size() != freelanceSubscriber.getCounter()) {
            Thread.sleep(10);
        }

        // Closing publishers
        publisher.close();
        transformProcessor.close();

        System.out.println("Exiting the app");
    }
}
