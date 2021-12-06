/**
 * 
 */
package functiontimer;

/**
 * @author Krish Ganotra
 * @version 11.28.2021
 */
public class ArrayBasedStack<T> implements DataStructure<T> {
    private T[] arr;
    private int size;
    private int capacity;

    public ArrayBasedStack() {
        this(50);
    }

    @SuppressWarnings("unchecked")
    public ArrayBasedStack(int cap) {
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
        return arr[size - 1];
    }

    @Override
    public boolean contains(T obj) {
        throw new InvalidFunctionException();
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
        if (size == capacity) {
            expandCapacity();
        }
        arr[size] = obj;
        size++;
    }
    
    @Override
    public void addAnywhere(T obj) {
        addEnd(obj);
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
        if (obj.equals(getLast())) {
            return removeLastInd();
        }
        else {
            throw new InvalidFunctionException();
        }
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
        T obj = arr[size - 1];
        arr[size - 1] = null;
        size--;
        return obj;
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
        ArrayBasedStack<T> other = (ArrayBasedStack<T>) obj;
        if (other.length() != this.length()) {
            return false;
        }
        
        T[] otherArr = other.arr;
        for (int i = 0; i < size; i++) {
            if (!otherArr[i].equals(arr[i])) {
                return false;
            }
        }
        return true;
        
    }

}
