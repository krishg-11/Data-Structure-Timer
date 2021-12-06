/**
 * 
 */
package functiontimer;

import student.TestCase;

/**
 * @author Krish Ganotra
 * @version 11.30.2021
 */
public class DLLTest extends TestCase {

    DLL<String> list1;
    DLL<String> empty;
    public void setUp() {
        list1 = new DLL<String>();
        empty = new DLL<String>();
        
        list1.addEnd("hi");
        list1.addEnd("hello");
        list1.addEnd("hola");
    }
    
    public void testGet() {
        assertEquals("hi", list1.getFirst());
        assertEquals("hola", list1.getLast());
        assertEquals("hello", list1.getInd(1));
        
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
        list1.addInd("howdy", 1);
        
        assertEquals("hi", list1.getInd(0));
        assertEquals("howdy", list1.getInd(1));
        assertEquals("hello", list1.getInd(2));
        assertEquals("hola", list1.getInd(3));
        
        assertEquals(4, list1.length());
    }
    
    public void testAddFront() {
        list1.addFront("howdy");
        
        assertEquals("howdy", list1.getInd(0));
        assertEquals("hi", list1.getInd(1));
        assertEquals("hello", list1.getInd(2));
        assertEquals("hola", list1.getInd(3));
        
        assertEquals(4, list1.length());
    }
    
    public void testAddEnd() {
        list1.addEnd("howdy");
        
        assertEquals("hi", list1.getInd(0));
        assertEquals("hello", list1.getInd(1));
        assertEquals("hola", list1.getInd(2));
        assertEquals("howdy", list1.getInd(3));
        
        assertEquals(4, list1.length());
    }
    
    public void testAddAnywhere() {
        list1.addAnywhere("howdy");
        
        assertEquals("howdy", list1.getInd(0));
        assertEquals("hi", list1.getInd(1));
        assertEquals("hello", list1.getInd(2));
        assertEquals("hola", list1.getInd(3));
        
        assertEquals(4, list1.length());
    }
    
    public void testRemoveObj() {
        assertEquals(list1.remove("hi"), "hi");
        
        assertEquals(list1.length(), 2);
        assertEquals("hello", list1.getInd(0));
        assertEquals("hola", list1.getInd(1));
        
        assertNull(list1.remove("hi"));
    }
    
    public void testRemoveInd() {
        assertEquals(list1.removeInd(1), "hello");
        
        assertEquals(list1.length(), 2);
        assertEquals("hi", list1.getInd(0));
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
        assertEquals(list1.removeFirstInd(), "hi");
        
        assertEquals(list1.length(), 2);
        assertEquals("hello", list1.getInd(0));
        assertEquals("hola", list1.getInd(1));
    }
    
    public void testRemoveLastInd() {
        assertEquals(list1.removeLastInd(), "hola");
        
        assertEquals(list1.length(), 2);
        assertEquals("hi", list1.getInd(0));
        assertEquals("hello", list1.getInd(1));
    }
    
    public void testIsEmpty() {
        assertFalse(list1.isEmpty());
        assertTrue(empty.isEmpty());
    }
    
    public void testToArray() {
        String[] exp = {"hi", "hello", "hola"};
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
        
        empty.addEnd("hello");
        empty.addEnd("hi");
        empty.addEnd("hola");
        
        assertFalse(list1.equals(empty));
        
        DLL<String> list2 = new DLL<String>();
        list2.addEnd("hi");
        list2.addEnd("hello");
        list2.addEnd("hola");
        assertTrue(list1.equals(list2));
    }

}

