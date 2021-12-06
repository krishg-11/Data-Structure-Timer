/**
 * 
 */
package functiontimer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author Krish Ganotra
 * @version 11.29.2021
 */
public class RunTimer<T> {

    private ArrayList<TimedStructure<T>> structArr;
    private ArrayList<String> funcNames;
    /**
     * 
     */
    public RunTimer() {
        structArr = new ArrayList<TimedStructure<T>>();
        funcNames = new ArrayList<String>();
    }
    
    public void addFunc(String funcName) {
        funcNames.add(funcName);
    }
    
    public void addStruct(TimedStructure<T> struct) {
        structArr.add(struct);
    }
    
    public String removeFunc(int ind) {
        String val = funcNames.get(ind);
        funcNames.remove(ind);
        return val;
    }
    
    public TimedStructure<T> removeStruct(int ind) {
        TimedStructure<T> val = structArr.get(ind);
        structArr.remove(ind);
        return val;
    }
    
    public TimedStructure<T> getStruct(int ind) {
        return structArr.get(ind);
    }
    
    public TimedStructure<T> replaceStruct(TimedStructure<T> struct, int ind) {
        TimedStructure<T> val = structArr.get(ind);
        structArr.set(ind, struct);
        return val;
    }
    
    public ArrayList<String> getFuncNames() {
        return funcNames;
    }
    
    public ArrayList<TimedStructure<T>> getStructArr() {
        return structArr;
    }
    
    public void updatePerc(double perc) {
        for (TimedStructure<T> struct : structArr) {
            struct.setPerc(perc);
        }
    }
    
    public double[][] run() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        double[][] times = new double[funcNames.size()][structArr.size()];
        for (int i = 0; i < funcNames.size(); i++) {
            for (int j = 0; j < structArr.size(); j++) {
                Method method = structArr.get(j).getClass().getMethod(funcNames.get(i));
                double timer = (double) method.invoke(structArr.get(j));
                times[i][j] = timer;
            }
        }
        return times;
    }
}
