/**
 * 
 */
package functiontimer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author Krish Ganotra
 * @version 11.29.2021
 */
public class ProjectRunner {

    /**
     * @param args
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static void main(String[] args) throws NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        RunTimer<String> timer = new RunTimer<String>();
        
        // Add Functions
        timer.addFunc("containsLast"); // Worst case scenario for array
        timer.addFunc("containsFirst"); // Best case scenario for array
        timer.addFunc("getFirst"); // constant time for Array
        timer.addFunc("getLast"); // constant time for Array

        double perc = 0.70; // when selecting a 'random' element from array, the
                            // 70th percentile element will be chosen

        // Add data structures
        for (int n = 100; n <= 1000000; n *= 10) {
            DataStructure<String> arr = new DLL<String>();
            for (int i = 0; i < n; i++) {
                arr.addAnywhere("element " + i);
            }
            timer.addStruct(
                    new TimedStructure<String>(arr, perc, "element " + n));
        }
        
        for (int n = 100; n <= 1000000; n *= 10) {
            DataStructure<String> arr = new SLL<String>();
            for (int i = 0; i < n; i++) {
                arr.addAnywhere("element " + i);
            }
            timer.addStruct(
                    new TimedStructure<String>(arr, perc, "element " + n));
        }
        
        for (int n = 100; n <= 1000000; n *= 10) {
            DataStructure<String> arr = new ArrayBasedList<String>();
            for (int i = 0; i < n; i++) {
                arr.addAnywhere("element " + i);
            }
            timer.addStruct(
                    new TimedStructure<String>(arr, perc, "element " + n));
        }

        double[][] times = timer.run();
        ArrayList<String> funcNames = timer.getFuncNames();
        ArrayList<TimedStructure<String>> structArr = timer.getStructArr();
        
        //Printing
        int width = 0;
        for (int i = 0; i < funcNames.size(); i++) {
            System.out.print(funcNames.get(i));
            System.out.print("  ");
            width += 2 + funcNames.get(i).length();
        }
        System.out.println();
        for (int i = 0; i < width; i++) {
            System.out.print("_");
        }
        System.out.println();
        
        for (int i = 0; i < times.length; i++) {
            System.out.print(structArr.get(i));
            System.out.print("|  ");
            for (int j = 0; j < times[i].length; j++) {
                System.out.print(times[i][j]);
                System.out.print("  ");
            }
            System.out.println();
        }

        /**
         * Interpreting output:
         *  e.g.
         *      28800.0  1100.0  900.0  600.0  
                367600.0  1300.0  1000.0  600.0  
                7335500.0  1200.0  1400.0  900.0  
                1.10577E7  16700.0  900.0  700.0  
                2.96282E7  1000.0  1000.0  800.0
                
           Each row consists of times from the same data structure.
           Each column consists of times of running the same function.
           
           In this example, our different data structures only varied by their size,
            so each column shows how the time increases for running a specific method
            as the size of the structure increases. The first column is containsLast
            which has an efficiency of O(n), so we should see a linear pattern down the column. 
            The last column is getLast which has an efficiency of O(1) so we should 
            see constant times even as the size of the data structure increases. 
         */
        
    }

}
