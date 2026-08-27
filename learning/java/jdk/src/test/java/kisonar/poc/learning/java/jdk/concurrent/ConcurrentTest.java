package kisonar.poc.learning.java.jdk.concurrent;

import org.junit.jupiter.api.Test;

public class ConcurrentTest {

    @Test
    public void testClassic()  {

        Runnable taskClassic = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello thread from " + threadName);
        };

        taskClassic.run();
        System.out.println("Task run from Main");

        Thread thread1 = new Thread(taskClassic);
        thread1.start();

        Thread thread2 = new Thread(taskClassic);
        thread2.start();

        Thread thread3 = new Thread(taskClassic);
        thread3.start();

        System.out.println("Main finished!");
    }

    @Test
    public void testVirtual1() throws InterruptedException {
        Thread.Builder builder = Thread.ofVirtual().name("MyThread");
        Runnable task = () -> System.out.println("Running thread");
        Thread t = builder.start(task);
        System.out.println("Thread t name: " + t.getName());
        t.join();
    }

    @Test
    public void testVirtual2() throws InterruptedException {
        Thread.Builder builder = Thread.ofVirtual().name("worker-", 0);
        Runnable task = () -> System.out.println("Thread ID: " + Thread.currentThread().threadId());

// name "worker-0"
        Thread t1 = builder.start(task);
        t1.join();
        System.out.println(t1.getName() + " terminated");

// name "worker-1"
        Thread t2 = builder.start(task);
        t2.join();
        System.out.println(t2.getName() + " terminated");
    }

}
