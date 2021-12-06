/**
 * 
 */
package functiontimer;

/**
 * @author Krish Ganotra
 * @version 11.28.2021
 */
public class ArrayBasedList<T> implements DataStructure<T> {

    private T[] arr;
    private int size;
    private int capacity;

    public ArrayBasedList() {
        this(50);
    }

    @SuppressWarnings("unchecked")
    public ArrayBasedList(int cap) {
        size = 0;
        capacity = cap;
        arr = (T[]) new Object[capacity];
    }

    @Override
    public T getFirst() {
        return arr[0];
    }
    
    @Override
    public T getLast() {
        return arr[size - 1];
    }
    
    @Override
    public T getInd(int ind) {
        if (ind >= size || ind < 0) {
            throw new IndexOutOfBoundsException();
        }
        return arr[ind];
    }

    @Override
    public boolean contains(T obj) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addInd(T obj, int ind) {
        if (ind > size || ind < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size == capacity) {
            expandCapacity();
        }

        // move old data over
        for (int i = size-1; i >= ind; i--) {
            arr[i + 1] = arr[i];
        }
        arr[ind] = obj;
        size++;
    }
    
    @Override
    public void addEnd(T obj) {
        addInd(obj, size);
    }
    
    public void addFront(T obj) {
        addInd(obj, 0);
    }

    /**
     * Appends obj to the end of the array
     */
    @Override
    public void addAnywhere(T obj) {
        addEnd(obj);
    }

    /**
     * Expands the capacity of the stack by doubling its current capacity.
     */
    private void expandCapacity() {

        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[this.capacity * 2];

        for (int i = 0; i < this.capacity; i++) {
            newArray[i] = this.arr[i];
        }

        this.arr = newArray;
        this.capacity *= 2;
    }

    /**
     * removes first occurence of obj in arr. returns null if obj not in arr
     * 
     * @return object removed. null if no object removed
     */
    @Override
    public T remove(T obj) {
        for (int i = 0; i < size; i++) {
            // object is found and will be removed
            if (arr[i].equals(obj)) {
                for (int j = i; j < size-1; j++) {
                    arr[j] = arr[j+1];
                }
                arr[size-1] = null;
                size--;
                return obj;
            }
        }
        return null;
    }

    @Override
    public T removeInd(int ind) {
        if (ind >= size || ind < 0) {
            throw new IndexOutOfBoundsException();
        }
        T obj = arr[ind];
        for (int i = ind; i < size; i++) {
            arr[i] = arr[i+1];
        }
        size--;
        return obj;
    }
    
    @Override
    public T removeFirstInd() {
        return removeInd(0);
    }
    
    @Override
    public T removeLastInd() {
        return removeInd(size - 1);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        return arr;
    }
    
    public int length() {
        return size;
    }
    
    @Override 
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }
        @SuppressWarnings("unchecked")
        ArrayBasedList<T> other = (ArrayBasedList<T>) obj;
        if (other.length() != this.length()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!other.getInd(i).equals(this.getInd(i))) {
                return false;
            }
        }
        return true;
        
    }

}
