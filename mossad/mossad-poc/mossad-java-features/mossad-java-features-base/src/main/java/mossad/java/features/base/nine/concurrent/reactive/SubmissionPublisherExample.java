package mossad.java.features.base.nine.concurrent.reactive;

import java.util.concurrent.SubmissionPublisher;

public class SubmissionPublisherExample {

    public static void main(String... args) throws InterruptedException {

        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        for (int i = 0; i < 10; i++) {
            publisher.subscribe(new PrintSubscriber(i));
        }

        System.out.println("Submitting items...");
        for (int i = 0; i < 100; i++) {
            publisher.submit(i);
        }
        Thread.sleep(100000);
        publisher.close();

    }

}
