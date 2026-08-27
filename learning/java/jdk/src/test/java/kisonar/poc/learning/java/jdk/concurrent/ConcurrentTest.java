package kisonar.poc.learning.java.jdk.concurrent;

import org.junit.jupiter.api.Test;

public class ConcurrentTest {

    @Test
    public void test() {

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello thread from " + threadName);
        };

        task.run();
        System.out.println("Task run from Main");

        Thread thread = new Thread(task);
        thread.start();

        Thread thread2 = new Thread(task);
        thread2.start();

        Thread thread3 = new Thread(task);
        thread3.start();

        System.out.println("Main finished!");
    }

}
