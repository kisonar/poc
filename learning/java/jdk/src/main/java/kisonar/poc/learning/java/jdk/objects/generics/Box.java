package kisonar.poc.learning.java.jdk.objects.generics;

public class Box<T> {

    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u){
        IO.println("T: " + t.getClass().getName());
        IO.println("U: " + u.getClass().getName());
    }
}
