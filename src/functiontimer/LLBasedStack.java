/**
 * 
 */
package functiontimer;

/**
 * @author Krish Ganotra
 * @version 11.30.2021
 */
public class LLBasedStack<T> implements DataStructure<T> {

    private Node<T> head;
    private int size;

    public LLBasedStack() {
        head = null;
        size = 0;
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
        return head.data();
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
        head = new Node<T>(obj, head);
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
        T val = head.data();
        head = head.next();
        size--;
        return val;
    }
   
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];

        Node<T> curr = head;
        for (int i = size - 1; i >= 0; i--) {
            arr[i] = curr.data();
            curr = curr.next();
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
        if (obj == this) {
            return true;
        }
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }
        @SuppressWarnings("unchecked")
        LLBasedStack<T> other = (LLBasedStack<T>) obj;
        if (other.length() != this.length()) {
            return false;
        }
        
        Node<T> oCurr = other.head;
        Node<T> curr = head;
        for (int i = 0; i < size; i++) {
            if(!oCurr.data().equals(curr.data())) {
                return false;
            }
            oCurr = oCurr.next();
            curr = curr.next();
        }
        return true;
    }

    private class Node<A> {
        private A data;
        private Node<A> next;

        public Node(A data, Node<A> next) {
            this.data = data;
            this.next = next;
        }

        public A data() {
            return data;
        }

        public Node<A> next() {
            return next;
        }

        public void setData(A data) {
            this.data = data;
        }

        public void setNext(Node<A> next) {
            this.next = next;
        }
    }

}
