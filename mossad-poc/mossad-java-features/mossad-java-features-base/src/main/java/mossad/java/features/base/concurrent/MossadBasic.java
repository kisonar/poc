package mossad.java.features.base.concurrent;

public final class MossadBasic {

    public static void main(String... args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello thread from " + threadName);
        };

        task.run();
        System.out.println("Task run from Main");

        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Main finished!");
    }

}
