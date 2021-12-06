/**
 * 
 */
package functiontimer;

import student.TestCase;

/**
 * @author Krish Ganotra
 * @version 11.30.2021
 */
public class SortedArrayTest extends TestCase {

    SortedArray<String> arr1;
    SortedArray<String> empty;
    public void setUp() {
        arr1 = new SortedArray<String>();
        empty = new SortedArray<String>();
        
        arr1.addAnywhere("hi");
        arr1.addAnywhere("hello");
        arr1.addAnywhere("hola");
    }
    
    public void testGet() {
        assertEquals("hello", arr1.getFirst());
        assertEquals("hi", arr1.getInd(1));
        assertEquals("hola", arr1.getLast());
        
        Exception e = null;
        try {
            arr1.getInd(5);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    
    public void testContains() {
        assertTrue(arr1.contains("hi"));
        assertFalse(arr1.contains("bye"));
        assertFalse(empty.contains("hi"));
    }
    
    public void testAddInd() {
        Exception e = null;
        try {
            arr1.addInd("howdy", 1);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testAddFront() {
        Exception e = null;
        try {
            arr1.addFront("howdy");
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testAddEnd() {
        Exception e = null;
        try {
            arr1.addEnd("howdy");
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testAddAnywhere() {
        arr1.addAnywhere("howdy");
        arr1.addAnywhere("bye");

        assertEquals("bye", arr1.getInd(0));
        assertEquals("hello", arr1.getInd(1));
        assertEquals("hi", arr1.getInd(2));
        assertEquals("hola", arr1.getInd(3));
        assertEquals("howdy", arr1.getInd(4));
        
        assertEquals(5, arr1.length());
    }
    
    public void testRemoveObj() {
        assertEquals(arr1.remove("hi"), "hi");
        
        assertEquals(arr1.length(), 2);
        assertEquals("hello", arr1.getInd(0));
        assertEquals("hola", arr1.getInd(1));
        
        assertNull(arr1.remove("hi"));
    }
    
    public void testRemoveInd() {
        assertEquals(arr1.removeInd(1), "hi");
        
        assertEquals(arr1.length(), 2);
        assertEquals("hello", arr1.getInd(0));
        assertEquals("hola", arr1.getInd(1));
        
        Exception e = null;
        try {
            arr1.removeInd(2);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    
    public void testRemoveFirstInd() {
        assertEquals(arr1.removeFirstInd(), "hello");
        
        assertEquals(arr1.length(), 2);
        assertEquals("hi", arr1.getInd(0));
        assertEquals("hola", arr1.getInd(1));
    }
    
    public void testRemoveLastInd() {
        assertEquals(arr1.removeLastInd(), "hola");
        
        assertEquals(arr1.length(), 2);
        assertEquals("hello", arr1.getInd(0));
        assertEquals("hi", arr1.getInd(1));
    }
    
    public void testIsEmpty() {
        assertFalse(arr1.isEmpty());
        assertTrue(empty.isEmpty());
    }
    
    public void testToArray() {
        String[] exp = {"hello", "hi", "hola"};
        Object[] out = arr1.toArray();
        for (int i = 0; i < exp.length; i++) {
            assertEquals(exp[i], out[i]);
        }
    }
    
    public void testLength() {
        assertEquals(3, arr1.length());
        assertEquals(0, empty.length());
    }
    
    public void testEquals() {
        assertFalse(arr1.equals(null));
        assertFalse(arr1.equals(3));
        assertTrue(arr1.equals(arr1));
        
        assertFalse(arr1.equals(empty));
        
        empty.addAnywhere("bye");
        empty.addAnywhere("farewell");
        empty.addAnywhere("adios");
        
        assertFalse(arr1.equals(empty));
        
        SortedArray<String> arr2 = new SortedArray<String>();
        arr2.addAnywhere("hola");
        arr2.addAnywhere("hello");
        arr2.addAnywhere("hi");
        
        assertTrue(arr1.equals(arr2));
    }
    
    
}
