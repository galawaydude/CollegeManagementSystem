import java.io.Serializable;

public class pavan_dynamic_array implements Serializable {
    private Object[] array;
    private int size;

    public pavan_dynamic_array(int initialCapacity) {
        array = new Object[initialCapacity];
        size = 0;
    }

    private void resizeArray() {
        int newCapacity = Math.max(size * 2, 1);
        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void add(Object item) {
        if (size == array.length) {
            resizeArray();
        }
        array[size++] = item;
    }

    public void remove(Object item) {
        int index = indexOf(item);
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
        }
    }
    public int indexOf(Object element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public Object get(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        }
        return null;
    }

    public Object[] getArray() {
        Object[] newArray = new Object[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public void addAll(pavan_dynamic_array otherArray) {
        if (size + otherArray.size() > array.length) {
            int newCapacity = Math.max(size + otherArray.size(), array.length * 2);
            Object[] newArray = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        for (int i = 0; i < otherArray.size(); i++) {
            add(otherArray.get(i));
        }
    }

    public boolean contains(Object item) {
        return indexOf(item) != -1;
    }
}
