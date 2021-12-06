/**
 * 
 */
package functiontimer;

/**
 * @author Krish Ganotra
 * @version 11.28.2021
 */
public class ArrayBasedBag<T> implements DataStructure<T> {
    private T[] arr;
    private int size;
    private int capacity;

    public ArrayBasedBag() {
        this(50);
    }

    @SuppressWarnings("unchecked")
    public ArrayBasedBag(int cap) {
        size = 0;
        capacity = cap;
        arr = (T[]) new Object[capacity];
    }

    @Override
    public T getInd(int ind) {
        throw new InvalidFunctionException();
    }

    @Override
    public T getFirst() {
        throw new InvalidFunctionException();
    }

    @Override
    public T getLast() {
        throw new InvalidFunctionException();
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
        throw new InvalidFunctionException();

    }

    /**
     * Appends obj to the end of the array
     */
    @Override
    public void addEnd(T obj) {
        throw new InvalidFunctionException();
    }

    @Override
    public void addAnywhere(T obj) {
        if (size == capacity) {
            expandCapacity();
        }
        arr[size] = obj;
        size++;
    }

    @Override
    public void addFront(T obj) {
        throw new InvalidFunctionException();
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

    @Override
    public T remove(T obj) {
        for (int i = 0; i < size; i++) {
            // object is found and will be removed
            if (arr[i].equals(obj)) {
                for (int j = i; j < size - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[size - 1] = null;
                size--;
                return obj;
            }
        }
        return null;
    }

    @Override
    public T removeInd(int ind) {
        throw new InvalidFunctionException();
    }

    @Override
    public T removeFirstInd() {
        throw new InvalidFunctionException();
    }

    @Override
    public T removeLastInd() {
        throw new InvalidFunctionException();
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
        ArrayBasedBag<T> other = (ArrayBasedBag<T>) obj;
        if (other.length() != this.length()) {
            return false;
        }

        T[] otherArr = other.arr;
        for (int i = 0; i < size; i++) {
            boolean found = false;
            for (int j = 0; j < size; j++) {
                if (arr[i].equals(otherArr[j])) {
                    otherArr[j] = null;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;

    }

}
