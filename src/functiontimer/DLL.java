/**
 * 
 */
package functiontimer;

/**
 * @author Krish Ganotra
 * @version 11.28.2021
 */
public class DLL<T> implements DataStructure<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;
    /**
     * 
     */
    public DLL() {
        head = null;
        tail = null;
        size = 0;
    }

    
    @Override
    public T getInd(int ind) {
        if (ind >= size || ind < 0) {
            throw new IndexOutOfBoundsException();
        }
        else if (ind > size/2) {
            Node<T> curr = tail;
            for (int i = 0; i < (size - ind - 1); i++) {
                curr = curr.prev();
            }
            return curr.data();
        }
        else {
            Node<T> curr = head;
            for (int i = 0; i < ind; i++) {
                curr = curr.next();
            }
            return curr.data();
        }
        
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
        Node<T> curr = head;
        while (curr != null) {
            if (curr.data().equals(obj)) {
                return true;
            }
            curr = curr.next();
        }
        return false;
    }

    @Override
    public void addInd(T obj, int ind) {
        if (ind > size || ind < 0) {
            throw new IndexOutOfBoundsException();
        }
        else if (ind == 0 || size == 0) {
            addFront(obj);
        }
        else if (ind == size) {
            tail.setNext(new Node<T>(obj, null, tail));
            tail = tail.next();
            size++;
        }
        else if (ind > size/2) {
            Node<T> curr = tail;
            for (int i = 0; i < (size - ind); i++) {
                curr = curr.prev();
            }
            Node<T> newNode = new Node<T>(obj, curr.next(), curr);
            curr.next().setPrev(newNode);
            curr.setNext(newNode);
            size++;
        }
        else {
            Node<T> curr = head;
            for (int i = 0; i < ind - 1; i++) {
                curr = curr.next();
            }
            Node<T> newNode = new Node<T>(obj, curr.next(), curr);
            curr.next().setPrev(newNode);
            curr.setNext(newNode);
            size++;
        }
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
        addFront(obj);
    }

    @Override
    public T remove(T obj) {
        if (head == null) {
            return null;
        }
        if (head.data().equals(obj)) {
            head = head.next();
            size--;
            return obj;
        }

        Node<T> curr = head;
        while (curr.next() != null) {
            if (curr.next().data().equals(obj)) {
                curr.setNext(curr.next().next());
                size--;
                return obj;
            }
            curr = curr.next();
        }
        return null;
    }

    @Override
    public T removeInd(int ind) {
        if (ind >= size || ind < 0) {
            throw new IndexOutOfBoundsException();
        }
        else if (ind == 0) {
            return removeFirstInd();
        }
        else if (ind == size-1) {
            return removeLastInd();
        }
        else if (ind > size/2) {
            Node<T> curr = tail;
            for (int i = 0; i < (size - ind - 1); i++) {
                curr = curr.prev();
            }
            T val = curr.data();
            curr.next().setPrev(curr.prev());
            curr.prev().setNext(curr.next());
            size--;
            return val;
        }
        else {
            Node<T> curr = head;
            for (int i = 0; i < ind; i++) {
                curr = curr.next();
            }
            T val = curr.data();
            curr.next().setPrev(curr.prev());
            curr.prev().setNext(curr.next());
            size--;
            return val;
        }
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
        DLL<T> other = (DLL<T>) obj;
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
