package models;


import java.util.*;


public class Inventory implements Container{
    private List<Item> backpack;
    private Iterator iter;

    public Inventory() {
        this.backpack = new ArrayList<>();
    }

    public List<Item> getItems() {
        return backpack;
    }

    public void addItem(Item item) {
        backpack.add(item);
    }

    public Iterator getIterator() {
        return new InventoryIterator();
    }

    private class InventoryIterator implements Iterator {
        int index = 0;

        public boolean hasNext() {
            if (backpack.size() < index) {
                return true;
            }
            return false;
        }

        public Object next() {
            return backpack.get(index++);
        }
    }
}
