package librarymanagement;

import java.util.ArrayList;
import java.util.List;

// Manages collection of LibraryItem objects
public class LibraryManager {
    private List<LibraryItem> items = new ArrayList<>();

    public void addItem(LibraryItem item) { items.add(item); }
    public void removeById(int id) { items.removeIf(i -> i.getId() == id); }
    public List<LibraryItem> getItems() { return items; }
}
