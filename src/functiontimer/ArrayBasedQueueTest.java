/**
 * 
 */
package functiontimer;

import student.TestCase;

/**
 * @author Krish Ganotra
 * @version 11.29.2021
 */
public class ArrayBasedQueueTest extends TestCase {

    ArrayBasedQueue<String> queue;
    ArrayBasedQueue<String> empty;
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
        empty = new ArrayBasedQueue<String>();
        
        queue.addEnd("hi");
        queue.addEnd("hello");
        queue.addEnd("hola");
    }
    
    public void testGetInd() {
        Exception e = null;
        try {
            queue.getInd(0);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testGetFirst() {
        assertEquals("hi", queue.getFirst()); 
    }
    
    public void testGetLast() {
        Exception e = null;
        try {
            queue.getLast();
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testContains() {
        Exception e = null;
        try {
            queue.contains("hi");
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
            queue.addInd("howdy", 2);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testAddEnd() {
        queue.addEnd("hey");
        
        assertEquals(4, queue.length());
        assertEquals("hi", queue.getFirst());
    }
    
    public void testAddFront() {
        Exception e = null;
        try {
            queue.addFront("howdy");
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testAddAnywhere() {
        queue.addAnywhere("hey");
        
        assertEquals(4, queue.length());
        assertEquals("hi", queue.getFirst());
    }
    
    public void testRemoveObj() {
        assertEquals("hi", queue.remove("hi"));
        assertEquals(2, queue.length());
        assertEquals("hello", queue.getFirst());
        
        Exception e = null;
        try {
            queue.remove("hola");
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
            queue.removeInd(0);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testRemoveFirstInd() {
        assertEquals("hi", queue.removeFirstInd());
        assertEquals(2, queue.length());
        assertEquals("hello", queue.getFirst());
    }
    
    public void testRemoveLastInd() {
        Exception e = null;
        try {
            queue.removeLastInd();
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testIsEmpty() {
        assertFalse(queue.isEmpty());
        assertTrue(empty.isEmpty());
    }
    
    public void testToArray() {
        String[] exp = {"hi", "hello", "hola"};
        Object[] out = queue.toArray();
        for (int i = 0; i < exp.length; i++) {
            assertEquals(exp[i], out[i]);
        }
    }
    
    public void testLength() {
        assertEquals(3, queue.length());
        assertEquals(0, empty.length());
    }
    
    public void testEquals() {
        assertFalse(queue.equals(null));
        assertFalse(queue.equals(3));
        assertTrue(queue.equals(queue));
        
        assertFalse(queue.equals(empty));
        
        empty.addEnd("hello");
        empty.addEnd("hi");
        empty.addEnd("hola");
        
        assertFalse(queue.equals(empty));
        
        ArrayBasedQueue<String> queue2 = new ArrayBasedQueue<String>();
        queue2.addEnd("hi");
        queue2.addEnd("hello");
        queue2.addEnd("hola");
        
        assertTrue(queue.equals(queue2));
    }
    
    

}
