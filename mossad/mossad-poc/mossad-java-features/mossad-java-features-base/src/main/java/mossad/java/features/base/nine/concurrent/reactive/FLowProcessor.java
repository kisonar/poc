package mossad.java.features.base.nine.concurrent.reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;

public class FLowProcessor implements Processor {

    int identifier;
    private Flow.Subscription subscription;
    private Subscriber subscriber;

    FLowProcessor(int identifier) {
        this.identifier = identifier;
    }

    @Override
    public void subscribe(Flow.Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Object item) {
        this.subscription.request(1);
        this.subscriber.onNext(item);
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
