/**
 * 
 */
package functiontimer;

/**
 * @author Krish Ganotra
 * @version 11.28.2021
 */
public class LLBasedDeque<T> implements DataStructure<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;
    /**
     * 
     */
    public LLBasedDeque() {
        head = null;
        tail = null;
        size = 0;
    }

    
    @Override
    public T getInd(int ind) {
        throw new InvalidFunctionException();
    }

    @Override
    public T getFirst() {
        if (head == null) {
            return null;
        }
        return head.data();
    }
    
    @Override
    public T getLast() {
        if (tail == null) {
            return null;
        }
        return tail.data();
    }
    
    @Override
    public boolean contains(T obj) {
        throw new InvalidFunctionException();
    }

    @Override
    public void addInd(T obj, int ind) {
        throw new InvalidFunctionException();
    }

    @Override
    public void addFront(T obj) {
        if (size == 0) {
            head = new Node<T>(obj, null, null);
            tail = head;
            size++;
        }
        else {
            head.setPrev(new Node<T>(obj, head, null));
            head = head.prev();
            size++;   
        }
    }
    
    @Override
    public void addEnd(T obj) {
        if (size == 0) {
            head = new Node<T>(obj, null, null);
            tail = head;
            size++;
        }
        else {
            tail.setNext(new Node<T>(obj, null, tail));
            tail = tail.next();
            size++;   
        }
    }
    
    @Override 
    public void addAnywhere(T obj) {
        addEnd(obj);
    }

    @Override
    public T remove(T obj) {
        if (head == null) {
            return null;
        }
        else if (head.data().equals(obj)) {
            return removeFirstInd();
        }
        else if (tail.data().equals(obj)) {
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
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        else if (size == 1) {
            T val = head.data();
            head = null;
            tail = null;
            size--;
            return val;
        }
        T val = head.data();
        head = head.next();
        head.setPrev(null);
        size--;
        return val;
    }
    
    @Override
    public T removeLastInd() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        else if (size == 1) {
            T val = head.data();
            head = null;
            tail = null;
            size--;
            return val;
        }
        T val = tail.data();
        tail = tail.prev;
        tail.setNext(null);
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
        for (int i = 0; i < size; i++) {
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
        LLBasedDeque<T> other = (LLBasedDeque<T>) obj;
        if (other.length() != this.length()) {
            return false;
        }
        
        Node<T> curr = head;
        Node<T> oCurr = other.head;
        
        for (int i = 0; i < size; i++) {
            if(!curr.data().equals(oCurr.data())) {
                return false;
            }
            curr = curr.next();
            oCurr = oCurr.next();
        }
        return true;
        
    }
    
    private class Node<A> {
        private A data;
        private Node<A> next;
        private Node<A> prev;
        
        Node(A data, Node<A> next, Node<A> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        /**
         * @return the data
         */
        public A data() {
            return data;
        }

        /**
         * @param data the data to set
         */
        public void setData(A data) {
            this.data = data;
        }

        /**
         * @return the next
         */
        public Node<A> next() {
            return next;
        }

        /**
         * @param next the next to set
         */
        public void setNext(Node<A> next) {
            this.next = next;
        }

        /**
         * @return the prev
         */
        public Node<A> prev() {
            return prev;
        }

        /**
         * @param prev the prev to set
         */
        public void setPrev(Node<A> prev) {
            this.prev = prev;
        }
    }
}
