/**
 * 
 */
package functiontimer;

import student.TestCase;

/**
 * @author Krish Ganotra
 * @version 11.29.2021
 */
public class ArrayBasedBagTest extends TestCase {

    ArrayBasedBag<String> bag1;
    ArrayBasedBag<String> empty;

    public void setUp() {
        bag1 = new ArrayBasedBag<String>();
        empty = new ArrayBasedBag<String>();

        bag1.addAnywhere("hi");
        bag1.addAnywhere("hello");
        bag1.addAnywhere("hola");
    }

    public void testGetInd() {
        Exception e = null;
        try {
            bag1.getInd(5);
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
            bag1.getFirst();
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testGetLast() {
        Exception e = null;
        try {
            bag1.getLast();
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testContains() {
        assertTrue(bag1.contains("hi"));
        assertFalse(bag1.contains("bye"));
        assertFalse(empty.contains("hi"));
    }

    public void testAddInd() {
        Exception e = null;
        try {
            bag1.addInd("hi", 5);
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
            bag1.addFront("hi");
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
            bag1.addEnd("hi");
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testAddAnywhere() {
        bag1.addAnywhere("howdy");

        assertTrue(bag1.contains("howdy"));
        assertEquals(4, bag1.length());
    }

    public void testRemoveObj() {
        assertEquals(bag1.remove("hi"), "hi");

        assertEquals(bag1.length(), 2);
        assertFalse(bag1.contains("hi"));

        assertNull(bag1.remove("hi"));
    }

    public void testRemoveInd() {
        Exception e = null;
        try {
            bag1.removeInd(2);
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
            bag1.removeFirstInd();
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testRemoveLastInd() {
        Exception e = null;
        try {
            bag1.removeLastInd();
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testIsEmpty() {
        assertFalse(bag1.isEmpty());
        assertTrue(empty.isEmpty());
    }

    public void testToArray() {
        String[] exp = { "hi", "hello", "hola" };
        Object[] out = bag1.toArray();
        for (int i = 0; i < exp.length; i++) {
            assertEquals(exp[i], out[i]);
        }
    }

    public void testLength() {
        assertEquals(3, bag1.length());
        assertEquals(0, empty.length());
    }

    public void testEquals() {
        assertFalse(bag1.equals(null));
        assertFalse(bag1.equals(3));
        assertTrue(bag1.equals(bag1));

        assertFalse(bag1.equals(empty));

        empty.addAnywhere("bye");
        empty.addAnywhere("cya");
        empty.addAnywhere("goodbye");

        assertFalse(bag1.equals(empty));

        ArrayBasedBag<String> bag2 = new ArrayBasedBag<String>();
        bag2.addAnywhere("hello");
        bag2.addAnywhere("hi");
        bag2.addAnywhere("hola");
        assertTrue(bag1.equals(bag2));
    }

}
