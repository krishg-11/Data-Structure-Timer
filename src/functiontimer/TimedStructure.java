/**
 * 
 */
package functiontimer;

/**
 * @author Krish Ganotra
 * @version 11.28.2021
 * @param <T> public double data type held in data structure
 */
public class TimedStructure<T> {

    private DataStructure<T> data;
    private double perc;
    private T extraObj;

    /**
     * 
     */
    public TimedStructure(DataStructure<T> data, double perc, T extra) {
        this.data = data;
        this.perc = perc;
        this.extraObj = extra;
    }

    public double getFirst() {
        try {
            long startTime = System.nanoTime();
            data.getFirst();
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }

    }

    public double getLast() {
        try {
            long startTime = System.nanoTime();
            data.getLast();
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }
    }

    public double getRandIndex() {
        try {
            int ind = (int) (data.length() * perc);

            long startTime = System.nanoTime();
            data.getInd(ind);
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }
    }

    public double containsFirst() {
        try {
            T obj = data.getFirst();

            long startTime = System.nanoTime();
            data.contains(obj);
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }

    }

    public double containsLast() {
        try {
            T obj = data.getLast();

            long startTime = System.nanoTime();
            data.contains(obj);
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }
    }

    public double containsRandObj() {
        try {
            T obj = data.getInd((int) (data.length() * perc));

            long startTime = System.nanoTime();
            data.contains(obj);
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }
    }

    public double addFront() {
        try {
            long startTime = System.nanoTime();
            data.addFront(extraObj);
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }

    }

    public double addEnd() {
        try {
            long startTime = System.nanoTime();
            data.addEnd(extraObj);
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }
    }

    public double addAnywhere() {
        try {
            long startTime = System.nanoTime();
            data.addAnywhere(extraObj);
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }
    }
    
    public double addRandInd() {
        try {
            int ind = (int) (data.length() * perc);
            
            long startTime = System.nanoTime();
            data.addInd(extraObj, ind);
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }
    }

    public double removeFirstInd() {
        try {
            long startTime = System.nanoTime();
            data.removeFirstInd();
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }
    }

    public double removeLastInd() {
        try {
            long startTime = System.nanoTime();
            data.removeLastInd();
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }
    }

    public double removeRandInd() {
        try {
            int ind = (int) (data.length() * perc);

            long startTime = System.nanoTime();
            data.removeInd(ind);
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }
    }

    public double removeFirstObj() {
        try {
            T obj = data.getFirst();

            long startTime = System.nanoTime();
            data.remove(obj);
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }

    }

    public double removeLastObj() {
        try {
            T obj = data.getLast();

            long startTime = System.nanoTime();
            data.remove(obj);
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }
    }

    public double removeRandObj() {
        try {
            T obj = data.getInd((int) (data.length() * perc));

            long startTime = System.nanoTime();
            data.remove(obj);
            long endTime = System.nanoTime();

            return endTime - startTime;
        }
        catch (InvalidFunctionException e) {
            return -1;
        }
    }

    public void setPerc(double perc) {
        this.perc = perc;
    }
    
    public DataStructure<T> getData() {
        return data;
    }
    
    public String toString() {
        return data.getClass().getSimpleName() + " of size: " + data.length();
    }
}
