/**
 * 
 */
package functiontimer;

/**
 * @author Krish Ganotra
 * @version 11.28.2021
 */
public class SortedArray<T extends Comparable<? super T>>
        implements DataStructure<T> {

    private T[] arr;
    private int size;
    private int capacity;

    public SortedArray() {
        this(50);
    }

    @SuppressWarnings("unchecked")
    public SortedArray(int cap) {
        size = 0;
        capacity = cap;
        arr = (T[]) new Comparable[capacity];
    }

    @Override
    public T getInd(int ind) {
        if (ind >= size || ind < 0) {
            throw new IndexOutOfBoundsException();
        }
        return arr[ind];
    }

    @Override
    public T getFirst() {
        return getInd(0);
    }

    @Override
    public T getLast() {
        return getInd(size - 1);
    }

    @Override
    public boolean contains(T obj) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(obj)) {
                return true;
            }
            else if (arr[i].compareTo(obj) > 0) {
                return false;
            }
        }
        return false;
    }

    @Override
    public void addInd(T obj, int ind) {
        throw new InvalidFunctionException();
    }

    @Override
    public void addEnd(T obj) {
        throw new InvalidFunctionException();
    }

    @Override
    public void addFront(T obj) {
        throw new InvalidFunctionException();
    }

    /**
     * Appends obj to the end of the array
     */
    @Override
    public void addAnywhere(T obj) {
        if (size == capacity) {
            expandCapacity();
        }
        // find position for obj
        int ind = 0;
        while (ind < size && arr[ind].compareTo(obj) < 0) {
            ind++;
        }
        // move old data over
        for (int i = size - 1; i >= ind; i--) {
            arr[i + 1] = arr[i];
        }
        arr[ind] = obj;
        size++;
    }

    /**
     * Expands the capacity of the stack by doubling its current capacity.
     */
    private void expandCapacity() {

        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Comparable[this.capacity * 2];

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
                for (int j = i; j < size; j++) {
                    arr[j] = arr[j + 1];
                }
                size--;
                return obj;
            }
            else if (arr[i].compareTo(obj) > 0) {
                return null;
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
            arr[i] = arr[i + 1];
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
        SortedArray<T> other = (SortedArray<T>) obj;
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
