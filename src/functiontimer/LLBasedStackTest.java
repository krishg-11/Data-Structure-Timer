/**
 * 
 */
package functiontimer;

import student.TestCase;

/**
 * @author Krish Ganotra
 * @version 11.29.2021
 */
public class LLBasedStackTest extends TestCase {

    LLBasedStack<String> stack;
    LLBasedStack<String> empty;
    public void setUp() {
        stack = new LLBasedStack<String>();
        empty = new LLBasedStack<String>();
        
        stack.addEnd("hi");
        stack.addEnd("hello");
        stack.addEnd("hola");
    }
    
    public void testGetInd() {
        Exception e = null;
        try {
            stack.getInd(0);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testGetFirst() {
        Exception e = null;
        try {
            stack.getFirst();
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testGetLast() {
        assertEquals("hola", stack.getLast());
        
    }
    
    public void testContains() {
        Exception e = null;
        try {
            stack.contains("hi");
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
            stack.addInd("howdy", 2);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testAddEnd() {
        stack.addEnd("hey");
        
        assertEquals(4, stack.length());
        assertEquals("hey", stack.getLast());
    }
    
    public void testAddFront() {
        Exception e = null;
        try {
            stack.addFront("howdy");
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testAddAnywhere() {
        stack.addAnywhere("hey");
        
        assertEquals(4, stack.length());
        assertEquals("hey", stack.getLast());
    }
    
    public void testRemoveObj() {
        assertEquals("hola", stack.remove("hola"));
        assertEquals(2, stack.length());
        assertEquals("hello", stack.getLast());
        
        Exception e = null;
        try {
            stack.remove("hola");
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
            stack.removeInd(0);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testRemoveFirstInd() {
        Exception e = null;
        try {
            stack.removeFirstInd();
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }
    
    public void testRemoveLastInd() {
        assertEquals("hola", stack.removeLastInd());
        assertEquals(2, stack.length());
        assertEquals("hello", stack.getLast());
    }
    
    public void testIsEmpty() {
        assertFalse(stack.isEmpty());
        assertTrue(empty.isEmpty());
    }
    
    public void testToArray() {
        String[] exp = {"hi", "hello", "hola"};
        Object[] out = stack.toArray();
        for (int i = 0; i < exp.length; i++) {
            assertEquals(exp[i], out[i]);
        }
    }
    
    public void testLength() {
        assertEquals(3, stack.length());
        assertEquals(0, empty.length());
    }
    
    public void testEquals() {
        assertFalse(stack.equals(null));
        assertFalse(stack.equals(3));
        assertTrue(stack.equals(stack));
        
        assertFalse(stack.equals(empty));
        
        empty.addEnd("hello");
        empty.addEnd("hi");
        empty.addEnd("hola");
        
        assertFalse(stack.equals(empty));
        
        LLBasedStack<String> stack2 = new LLBasedStack<String>();
        stack2.addEnd("hi");
        stack2.addEnd("hello");
        stack2.addEnd("hola");
        assertTrue(stack.equals(stack2));
    }
    
    

}
