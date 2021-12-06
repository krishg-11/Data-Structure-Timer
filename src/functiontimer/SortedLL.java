/**
 * 
 */
package functiontimer;

/**
 * @author Krish Ganotra
 * @version 11.30.2021
 */
public class SortedLL<T extends Comparable<? super T>>
        implements DataStructure<T> {

    private Node<T> head;
    private int size;

    public SortedLL() {
        head = null;
        size = 0;
    }

    @Override
    public T getInd(int ind) {
        if (ind >= size || ind < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> curr = head;
        for (int i = 0; i < ind; i++) {
            curr = curr.next();
        }
        return curr.data();
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
            else if (curr.data().compareTo(obj) > 0) {
                return false;
            }
            curr = curr.next();
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

    @Override
    public void addAnywhere(T obj) {
        if (size == 0 || head.data().compareTo(obj) > 0) {
            head = new Node<T>(obj, head);
            size++;
        }
        else {
            Node<T> curr = head;
            while (curr.next() != null
                    && curr.next().data().compareTo(obj) < 0) {
                curr = curr.next();
            }
            curr.setNext(new Node<T>(obj, curr.next()));
            size++;
        }
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
            if (curr.next.data().compareTo(obj) > 0) {
                return null;
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
            T val = head.data();
            head = head.next();
            size--;
            return val;
        }
        else {
            Node<T> curr = head;
            for (int i = 0; i < ind - 1; i++) {
                curr = curr.next();
            }
            T val = curr.next().data();
            curr.setNext(curr.next().next());
            size--;
            return val;
        }
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
        SortedLL<T> other = (SortedLL<T>) obj;
        if (other.length() != this.length()) {
            return false;
        }
        
        Node<T> oCurr = other.head;
        Node<T> curr = head;
        for (int i = 0; i < size; i++) {
            if (!curr.data().equals(oCurr.data())) {
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
