/**
 * 
 */
package functiontimer;

/**
 * @author Krish Ganotra
 * @version 11.30.2021
 */
public class ArrayBasedQueue<T> implements DataStructure<T> {

    private T[] queue;
    private int size;
    private int enqueueIndex;
    private int dequeueIndex;

    public ArrayBasedQueue() {
        this(50);
    }

    @SuppressWarnings("unchecked")
    public ArrayBasedQueue(int cap) {
        size = 0;
        queue = (T[]) new Object[cap + 1];
        dequeueIndex = 0;
        enqueueIndex = queue.length - 1;
    }

    @Override
    public T getInd(int ind) {
        throw new InvalidFunctionException();
    }
    
    @Override
    public T getFirst() {
        if (size == 0) {
            return null;
        }
        return queue[dequeueIndex];
    }
    
    public T getLast() {
        throw new InvalidFunctionException();
    }

    @Override
    public boolean contains(T obj) {
        throw new InvalidFunctionException();
    }

    @Override
    public void addAnywhere(T obj) {
        addEnd(obj);
    }
    
    @Override
    public void addEnd(T obj) {
        if (isFull()) {
            expandCapacity();
        }
        enqueueIndex = incrementIndex(enqueueIndex);
        queue[enqueueIndex] = obj;
        size++;
    }
    
    @Override
    public void addFront(T obj) {
        throw new InvalidFunctionException();
    }
    
    @Override
    public void addInd(T obj, int ind) {
        throw new InvalidFunctionException();
    }

    private int incrementIndex(int index) {
        return ((index + 1) % queue.length);
    }
    
    private boolean isFull() {
        return (enqueueIndex + 2) % queue.length == dequeueIndex;
    }
    
    /**
     * Expands the capacity of the stack by doubling its current capacity.
     */
    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        if (isFull()) {
            T[] oldQueue = queue;
            int oldCap = queue.length - 1;
            int newCap = oldCap * 2;
            queue = (T[]) new Object[newCap + 1];
            for (int i = 0; i < size; i++) {
                int queueInd = (i + dequeueIndex) % oldQueue.length;
                queue[i] = oldQueue[queueInd];
            }
            dequeueIndex = 0;
            enqueueIndex = size - 1;
        }
    }

    /**
     * removes first occurence of obj in arr. returns null if obj not in arr
     * 
     * @return object removed. null if no object removed
     */
    @Override
    public T remove(T obj) {
        if (obj.equals(getFirst())) {
            return removeFirstInd();
        }
        else {
            throw new InvalidFunctionException();
        }
    }

    public T removeFirstInd() {
        T val = getFirst();
        if (val == null) {
            return val;
        }
        queue[dequeueIndex] = null;
        dequeueIndex = incrementIndex(dequeueIndex);
        size--;
        return val;
    }
    
    public T removeLastInd() {
        throw new InvalidFunctionException();
    }
    
    @Override
    public T removeInd(int ind) {
        throw new InvalidFunctionException();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        if (isEmpty()) {
            return new Object[0];
        }
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            int queueInd = (i + dequeueIndex) % queue.length;
            arr[i] = queue[queueInd];
        }
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
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ArrayBasedQueue<T> other = (ArrayBasedQueue<T>) obj;
        if (this.length() != other.length()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            T myElement = queue[(dequeueIndex + i) % queue.length];
            T otherElement = other.queue[
                         (other.dequeueIndex + i) % other.queue.length];
            if (! myElement.equals(otherElement)) {
                return false;
            }
        }
        return true;
    }

}
