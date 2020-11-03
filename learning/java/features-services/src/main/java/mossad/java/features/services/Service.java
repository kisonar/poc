package mossad.java.features.services;

import java.util.ArrayList;
import java.util.List;
import mossad.java.features.library.position.Position;

public class Service {

    private List<Position> positions = new ArrayList<>();

    public Service() {

    }

    public void addPositionItem(Position position) {
        positions.add(position);
    }


}
