package mossad.java.features.library.position;

import java.util.ArrayList;
import java.util.List;
import mossad.java.features.library.position.item.PositionItem;

public final class Position {


    private List<PositionItem> positionItems;

    public Position() {
        positionItems = new ArrayList<>();
    }

    public List<PositionItem> getPositionItems() {
        return positionItems;
    }

    public void addPositionItem(PositionItem positionItem) {
        positionItems.add(positionItem);
    }
}
