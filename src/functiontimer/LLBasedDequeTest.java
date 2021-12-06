/**
 * 
 */
package functiontimer;

import student.TestCase;

/**
 * @author Krish Ganotra
 * @version 11.29.2021
 */
public class LLBasedDequeTest extends TestCase {

    LLBasedDeque<String> deque;
    LLBasedDeque<String> empty;
    public void setUp() {
        deque = new LLBasedDeque<String>();
        empty = new LLBasedDeque<String>();
        
        deque.addEnd("hi");
        deque.addEnd("hello");
        deque.addEnd("hola");
    }
    
    public void testGetInd() {
        Exception e = null;
        try {
            deque.getInd(0);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testGetFirst() {
        assertEquals("hi", deque.getLast());
    }
    
    public void testGetLast() {
        assertEquals("hola", deque.getLast());
    }
    
    public void testContains() {
        Exception e = null;
        try {
            deque.contains("hi");
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testAddInd() {
        Exception e = null;
        try {
            deque.addInd("howdy", 2);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testAddEnd() {
        deque.addEnd("hey");
        
        assertEquals(4, deque.length());
        assertEquals("hey", deque.getLast());
    }
    
    public void testAddFront() {
        deque.addFront("hey");
        
        assertEquals(4, deque.length());
        assertEquals("hey", deque.getFirst());
    }
    
    public void testAddAnywhere() {
        deque.addAnywhere("hey");
        
        assertEquals(4, deque.length());
        assertEquals("hey", deque.getLast());
    }
    
    public void testRemoveObj() {
        assertEquals("hola", deque.remove("hola"));
        assertEquals(2, deque.length());
        assertEquals("hello", deque.getLast());
        
        Exception e = null;
        try {
            deque.remove("hola");
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testRemoveInd() {
        Exception e = null;
        try {
            deque.removeInd(0);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testRemoveFirstInd() {
        assertEquals("hi", deque.removeFirstInd());
        assertEquals(2, deque.length());
        assertEquals("hello", deque.getFirst());
    }
    
    public void testRemoveLastInd() {
        assertEquals("hola", deque.removeLastInd());
        assertEquals(2, deque.length());
        assertEquals("hello", deque.getLast());
    }
    
    public void testIsEmpty() {
        assertFalse(deque.isEmpty());
        assertTrue(empty.isEmpty());
    }
    
    public void testToArray() {
        String[] exp = {"hi", "hello", "hola"};
        Object[] out = deque.toArray();
        for (int i = 0; i < exp.length; i++) {
            assertEquals(exp[i], out[i]);
        }
    }
    
    public void testLength() {
        assertEquals(3, deque.length());
        assertEquals(0, empty.length());
    }
    
    public void testEquals() {
        assertFalse(deque.equals(null));
        assertFalse(deque.equals(3));
        assertTrue(deque.equals(deque));
        
        assertFalse(deque.equals(empty));
        
        empty.addEnd("hello");
        empty.addEnd("hi");
        empty.addEnd("hola");
        
        assertFalse(deque.equals(empty));
        
        LLBasedDeque<String> deque2 = new LLBasedDeque<String>();
        deque2.addEnd("hi");
        deque2.addEnd("hello");
        deque2.addEnd("hola");
        assertTrue(deque.equals(deque2));
    }
    
    

}
