package mossad.java.features.services;

import mossad.java.features.library.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private List<Position> positions = new ArrayList<>();

    public Service(){

    }

    public void addPositionItem(Position position){
        positions.add(position);
    }


}
