package kisonar.poc.learning.java.jdk.collections;

public final class EntityEqualsNokHashNok extends Entity {

    public EntityEqualsNokHashNok(int id, String name, String value) {
        super(id, name, value);
    }

    @Override
    public int hashCode() {

        return 4;
        //return Objects.hash(id, name);
    } //missing value

    @SuppressWarnings("should check the class of its parameter")
    @Override
    public boolean equals(Object obj) {

        /*
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Entity e)) return false;

        return this.id == e.id && Objects.equals(e.name, this.name);  //missing value

         */
        return false;
    }
}
