package mossad.java.features.base.nine.concurrent.reactive.example.domain;

import java.util.Objects;

public class Employee {

    private int id;
    private String name;

    Employee(int i, String s) {
        this.id = i;
        this.name = s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
