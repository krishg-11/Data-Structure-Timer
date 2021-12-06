/**
 * 
 */
package functiontimer;

/**
 * @author Krish Ganotra
 * @version 11.28.2021
 */
public class BinarySearchTree<T extends Comparable<? super T>>
        implements DataStructure<T> {

    BinaryNode<T> head;
    int size;

    @Override
    public T getInd(int ind) {
        throw new InvalidFunctionException();
    }

    @Override
    public T getFirst() {
        return elementAt(findMin(head));
    }

    @Override
    public T getLast() {
        return elementAt(findMax(head));
    }

    @Override
    public boolean contains(T obj) {
        return find(obj, head) != null;
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x    is item to search for.
     * @param node the node that roots the tree.
     * @return node containing the matched item.
     */
    private BinaryNode<T> find(T x, BinaryNode<T> node) {
        if (node == null) {
            return null; // Not found
        }
        else if (x.compareTo(node.data()) < 0) {
            // Search in the left subtree
            return find(x, node.left());
        }
        else if (x.compareTo(node.data()) > 0) {
            // Search in the right subtree
            return find(x, node.right());
        }
        else {
            return node; // Match
        }
    }

    @Override
    public void addAnywhere(T obj) {
        head = insert(obj, head);
        size++;
    }

    /**
     * Internal method to get element value stored in a tree node, with safe
     * handling of null nodes.
     *
     * @param node the node.
     * @return the element field or null if node is null.
     */
    private T elementAt(BinaryNode<T> node) {
        return (node == null) ? null : node.data();
    }

    /**
     * Internal method to insert a value into a subtree.
     *
     * @param x    the item to insert.
     * @param node the node that roots the subtree.
     * @return the new root of the subtree.
     * @throws DuplicateItemException if x is already present.
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> node) {
        if (node == null) {
            return new BinaryNode<T>(x, null, null);
        }
        else if (x.compareTo(node.data()) < 0) {
            node.setLeft(insert(x, node.left()));
        }
        else if (x.compareTo(node.data()) > 0) {
            node.setRight(insert(x, node.right()));
        }
        else {
            throw new DuplicateItemException(x.toString());
        }
        return node;
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
    public void addInd(T obj, int ind) {
        throw new InvalidFunctionException();
    }

    @Override
    public T remove(T obj) {
        head = remove(obj, head);
        size--;
        return obj;
    }

    /**
     * Internal method to remove a specified item from a subtree.
     *
     * @param x    the item to remove.
     * @param node the node that roots the subtree.
     * @return the new root of the subtree.
     * @throws ItemNotFoundException if x is not found.
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> node) {
        // This local variable will contain the new root of the subtree,
        // if the root needs to change.
        BinaryNode<T> result = node;

        // If there's no more subtree to examine
        if (node == null) {
            return null;
        }

        // if value should be to the left of the root
        if (x.compareTo(node.data()) < 0) {
            node.setLeft(remove(x, node.left()));
        }
        // if value should be to the right of the root
        else if (x.compareTo(node.data()) > 0) {
            node.setRight(remove(x, node.right()));
        }
        // If value is on the current node
        else {
            // If there are two children
            if (node.left() != null && node.right() != null) {
                result = node;
                BinaryNode<T> replacement = findMax(node.left());
                result.setData(elementAt(replacement));
                result.setLeft(remove(elementAt(replacement), result.left()));
            }
            // If there is only one child on the left
            else if (node.left() != null) {
                result = node.left();
            }
            // If there is only one child on the right
            else {
                result = node.right();
            }
        }
        return result;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param node the node that roots the tree.
     * @return node containing the smallest item.
     */
    private BinaryNode<T> findMin(BinaryNode<T> node) {
        if (node == null) {
            return node;
        }
        else if (node.left() == null) {
            return node;
        }
        else {
            return findMin(node.left());
        }
    }

    // ----------------------------------------------------------
    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param node the node that roots the tree.
     * @return node containing the largest item.
     */
    private BinaryNode<T> findMax(BinaryNode<T> node) {
        if (node == null) {
            return node;
        }
        else if (node.right() == null) {
            return node;
        }
        else {
            return findMax(node.right());
        }
    }

    @Override
    public T removeFirstInd() {
        BinaryNode<T> curr = head;
        if (curr == null) {
            return null;
        }
        else if (curr.left() == null) {
            T obj = curr.data();
            head = curr.right();
            size--;
            return obj;
        }
        else {
            while (curr.left().left() != null) {
                curr = curr.left();
            }
            T obj = curr.left().data();
            curr.setLeft(curr.left().right());
            size--;
            return obj;
        }
    }

    @Override
    public T removeLastInd() {
        BinaryNode<T> curr = head;
        if (curr == null) {
            return null;
        }
        else if (curr.right() == null) {
            T obj = curr.data();
            head = curr.left();
            size--;
            return obj;
        }
        else {
            while (curr.right().right() != null) {
                curr = curr.right();
            }
            T obj = curr.right().data();
            curr.setRight(curr.right().left());
            size--;
            return obj;
        }
    }

    @Override
    public T removeInd(int ind) {
        throw new InvalidFunctionException();
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * in order
     */
    @Override
    public Object[] toArray() {
        return getInorder(head);

    }

    private BinaryNode<T>[] getInorder(BinaryNode<T> node) {
        if (node == null) {
            return null;
        }
        BinaryNode<T>[] left = getInorder(node.left());
        BinaryNode<T>[] right = getInorder(node.right());
        int leftSize = left == null ? 0 : left.length;
        int rightSize = right == null ? 0 : right.length;

        @SuppressWarnings("unchecked")
        BinaryNode<T>[] arr = (BinaryNode<T>[]) new Object[leftSize + 1
                + rightSize];

        for (int i = 0; i < leftSize; i++) {
            arr[i] = left[i];
        }
        arr[leftSize] = node;

        for (int i = 0; i < rightSize; i++) {
            arr[leftSize + i + 1] = right[i];
        }
        
        return arr;
    }

    public String toString() {
        if (head == null) {
            return "()";
        }
        else {
            return "(" + head.toString() + ")";
        }
    }
    
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
        
        BinarySearchTree<T> other = (BinarySearchTree<T>) obj;
        return this.toString().equals(other.toString());
    }
    
    private class BinaryNode<A> {
        private BinaryNode<A> left;
        private BinaryNode<A> right;
        private A data;

        public BinaryNode() {
            data = null;
            left = null;
            right = null;
        }

        public BinaryNode(A data, BinaryNode<A> left, BinaryNode<A> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public A data() {
            return data;
        }

        public BinaryNode<A> left() {
            return left;
        }

        public BinaryNode<A> right() {
            return right;
        }

        public void setData(A data) {
            this.data = data;
        }

        public void setLeft(BinaryNode<A> left) {
            this.left = left;
        }

        public void setRight(BinaryNode<A> right) {
            this.right = right;
        }
    

        /**
         * Provides an in-order representation of the node
         * @return a string representation of the node
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (left != null)
            {
                builder.append(left.toString() + ", ");
            }
            builder.append(data.toString());
            if (right != null)
            {
                builder.append(", " + right.toString());
            }
            return builder.toString();
        }
    }
}
