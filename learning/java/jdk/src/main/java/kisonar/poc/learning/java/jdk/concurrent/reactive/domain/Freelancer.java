package kisonar.poc.learning.java.jdk.concurrent.reactive.domain;

public final class Freelancer extends Employee {

    public final int fid;

    public Freelancer(int id, int fid, String name) {
        super(id, name);
        this.fid = fid;
    }


    @Override
    public String toString() {
        return "[id=" + super.getId() + ",name=" + super.getName() + ",fid=" + fid + "]";
    }


}
