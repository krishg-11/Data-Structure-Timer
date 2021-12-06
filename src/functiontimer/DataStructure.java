/**
 * 
 */
package functiontimer;

/**
 * @author Krish Ganotra
 * @version 11.28.2021
 * @param <T> data type held in DataStructure
 */
public interface DataStructure<T> {
    public T getInd(int ind);
    
    public T getFirst();
    
    public T getLast();

    public boolean contains(T obj);

    /**
     * Adds object to some position in data structure. Each type of data
     * structure may choose a different position. The chosen position should be
     * the structure's best-case scenario in terms of efficiency.
     * 
     * @param obj - object to be added to data structure
     */
    public void addAnywhere(T obj);
    
    public void addEnd(T obj);
    
    public void addFront(T obj);

    public void addInd(T obj, int ind);

    public T remove(T obj);
    
    public T removeFirstInd();
    
    public T removeLastInd();

    public T removeInd(int ind);
    
    public int length();

    public boolean isEmpty();

    public String toString();

    public Object[] toArray();

    public boolean equals(Object obj);
}
