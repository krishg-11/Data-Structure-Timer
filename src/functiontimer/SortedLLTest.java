/**
 * 
 */
package functiontimer;

import student.TestCase;

/**
 * @author Krish Ganotra
 * @version 11.30.2021
 */
public class SortedLLTest extends TestCase {
    
    SortedLL<String> list1;
    SortedLL<String> empty;
    public void setUp() {
        list1 = new SortedLL<String>();
        empty = new SortedLL<String>();
        
        list1.addAnywhere("hi");
        list1.addAnywhere("hello");
        list1.addAnywhere("hola");
    }
    
    public void testGet() {
        assertEquals("hello", list1.getFirst());
        assertEquals("hi", list1.getInd(1));
        assertEquals("hola", list1.getLast());
        
        Exception e = null;
        try {
            list1.getInd(5);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    
    public void testContains() {
        assertTrue(list1.contains("hi"));
        assertFalse(list1.contains("bye"));
        assertFalse(empty.contains("hi"));
    }
    
    public void testAddInd() {
        Exception e = null;
        try {
            list1.addInd("howdy", 1);
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
            list1.addFront("howdy");
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
            list1.addEnd("howdy");
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testAddAnywhere() {
        list1.addAnywhere("howdy");
        list1.addAnywhere("bye");

        assertEquals("bye", list1.getInd(0));
        assertEquals("hello", list1.getInd(1));
        assertEquals("hi", list1.getInd(2));
        assertEquals("hola", list1.getInd(3));
        assertEquals("howdy", list1.getInd(4));
        
        assertEquals(5, list1.length());
    }
    
    public void testRemoveObj() {
        assertEquals(list1.remove("hi"), "hi");
        
        assertEquals(list1.length(), 2);
        assertEquals("hello", list1.getInd(0));
        assertEquals("hola", list1.getInd(1));
        
        assertNull(list1.remove("hi"));
    }
    
    public void testRemoveInd() {
        assertEquals(list1.removeInd(1), "hi");
        
        assertEquals(list1.length(), 2);
        assertEquals("hello", list1.getInd(0));
        assertEquals("hola", list1.getInd(1));
        
        Exception e = null;
        try {
            list1.removeInd(2);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    
    public void testRemoveFirstInd() {
        assertEquals(list1.removeFirstInd(), "hello");
        
        assertEquals(list1.length(), 2);
        assertEquals("hi", list1.getInd(0));
        assertEquals("hola", list1.getInd(1));
    }
    
    public void testRemoveLastInd() {
        assertEquals(list1.removeLastInd(), "hola");
        
        assertEquals(list1.length(), 2);
        assertEquals("hello", list1.getInd(0));
        assertEquals("hi", list1.getInd(1));
    }
    
    public void testIsEmpty() {
        assertFalse(list1.isEmpty());
        assertTrue(empty.isEmpty());
    }
    
    public void testToArray() {
        String[] exp = {"hello", "hi", "hola"};
        Object[] out = list1.toArray();
        for (int i = 0; i < exp.length; i++) {
            assertEquals(exp[i], out[i]);
        }
    }
    
    public void testLength() {
        assertEquals(3, list1.length());
        assertEquals(0, empty.length());
    }
    
    public void testEquals() {
        assertFalse(list1.equals(null));
        assertFalse(list1.equals(3));
        assertTrue(list1.equals(list1));
        
        assertFalse(list1.equals(empty));
        
        empty.addAnywhere("bye");
        empty.addAnywhere("farewell");
        empty.addAnywhere("adios");
        
        assertFalse(list1.equals(empty));
        
        SortedLL<String> list2 = new SortedLL<String>();
        list2.addAnywhere("hola");
        list2.addAnywhere("hello");
        list2.addAnywhere("hi");
        
        assertTrue(list1.equals(list2));
    }
    
  
}
