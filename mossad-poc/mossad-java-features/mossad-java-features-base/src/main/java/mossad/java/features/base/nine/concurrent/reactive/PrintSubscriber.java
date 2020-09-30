package mossad.java.features.base.nine.concurrent.reactive;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class PrintSubscriber implements Subscriber<Integer> {

    private Subscription subscription;
    private int identifier;

    PrintSubscriber(int identifier) {
        this.identifier = identifier;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        System.out.println("PrintSubscriber " + identifier + "received item: " + item);
        subscription.request(1);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable error) {
        System.out.println("Error occurred: " + error.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("PrintSubscriber " + identifier + "is complete");
    }

}
