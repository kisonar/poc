package mossad.java.features.base.concurrent.reactive.example.domain;

import java.util.Objects;

public class Employee {

    private final int id;
    private final String name;

    Employee(int i, String s) {
        this.id = i;
        this.name = s;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id &&
            Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "[id=" + id + ",name=" + name + "]";
    }
}
