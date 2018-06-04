package mossad.java.features.library.item;


import mossad.java.features.library.entry.LibraryEntry;

import java.util.ArrayList;
import java.util.List;

public final class LibraryItem {

    private List<LibraryEntry> libraryEntries;

    public LibraryItem(){
        libraryEntries = new ArrayList<>();
    }

    public void addEntry(LibraryEntry libraryEntry) {
        libraryEntries.add(libraryEntry);
    }


}
