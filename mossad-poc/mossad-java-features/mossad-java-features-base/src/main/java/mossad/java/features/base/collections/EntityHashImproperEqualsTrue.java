package mossad.java.features.base.collections;

public final class EntityHashImproperEqualsTrue {

    public final int id;
    public final String name;
    public final String value;

    public EntityHashImproperEqualsTrue(int id, String name, String value) {
        this.name = name;
        this.id = id;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return 456754;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
