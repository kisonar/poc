package mossad.java.features.base.concurrent.reactive.example.domain;

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
