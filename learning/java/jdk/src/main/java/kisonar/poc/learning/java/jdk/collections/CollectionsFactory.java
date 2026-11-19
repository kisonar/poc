package kisonar.poc.learning.java.jdk.collections;

import kisonar.poc.learning.java.jdk.collections.record.RecordEntity;

public final class CollectionsFactory {

    private CollectionsFactory() {
    }

    public static RecordEntity getRecord(int id, String name, String value) {
        return new RecordEntity(id, name, value);
    }
}