package kisonar.poc.learning.java.jdk.concurrent.classic;

public final class ConcurrentClassic {

    public static void main(String... args) {
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
