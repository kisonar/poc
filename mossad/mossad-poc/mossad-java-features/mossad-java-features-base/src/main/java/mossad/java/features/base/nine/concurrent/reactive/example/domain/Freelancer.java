package mossad.java.features.base.nine.concurrent.reactive.example.domain;

public class Freelancer extends Employee {

    private int fid;

    public Freelancer(int id, int fid, String name) {
        super(id, name);
        this.fid = fid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    @Override
    public String toString() {
        return "[id=" + super.getId() + ",name=" + super.getName() + ",fid=" + fid + "]";
    }


}
