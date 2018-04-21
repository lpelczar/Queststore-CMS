package queststore.utils;

import java.util.List;

public class Iterator<T> {

    private int index;
    private List<T> data;

    public Iterator(List<T> elements) {
        data = elements;
    }

    public boolean hasNext() { return index < data.size(); }

    public T next() {
        return data.get(index++);
    }
}
