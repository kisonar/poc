package mossad.java.features.base.concurrent;

public final class MossadBasic {

    public static void main(String... args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        task.run();

        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Done!");

    }

}
