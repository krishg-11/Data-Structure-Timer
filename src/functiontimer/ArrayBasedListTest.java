/**
 * 
 */
package functiontimer;

import student.TestCase;

/**
 * @author Krish Ganotra
 * @version 11.29.2021
 */
public class ArrayBasedListTest extends TestCase {

    ArrayBasedList<String> arr1;
    ArrayBasedList<String> empty;

    public void setUp() {
        arr1 = new ArrayBasedList<String>();
        empty = new ArrayBasedList<String>();

        arr1.addEnd("hi");
        arr1.addEnd("hello");
        arr1.addEnd("hola");
    }

    public void testGet() {
        assertEquals("hi", arr1.getFirst());
        assertEquals("hola", arr1.getLast());
        assertEquals("hello", arr1.getInd(1));

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
        arr1.addInd("howdy", 1);

        assertEquals("hi", arr1.getInd(0));
        assertEquals("howdy", arr1.getInd(1));
        assertEquals("hello", arr1.getInd(2));
        assertEquals("hola", arr1.getInd(3));

        assertEquals(4, arr1.length());
    }

    public void testAddFront() {
        arr1.addFront("howdy");

        assertEquals("howdy", arr1.getInd(0));
        assertEquals("hi", arr1.getInd(1));
        assertEquals("hello", arr1.getInd(2));
        assertEquals("hola", arr1.getInd(3));

        assertEquals(4, arr1.length());
    }

    public void testAddEnd() {
        arr1.addEnd("howdy");

        assertEquals("hi", arr1.getInd(0));
        assertEquals("hello", arr1.getInd(1));
        assertEquals("hola", arr1.getInd(2));
        assertEquals("howdy", arr1.getInd(3));

        assertEquals(4, arr1.length());
    }

    public void testAddAnywhere() {
        arr1.addAnywhere("howdy");

        assertEquals("hi", arr1.getInd(0));
        assertEquals("hello", arr1.getInd(1));
        assertEquals("hola", arr1.getInd(2));
        assertEquals("howdy", arr1.getInd(3));

        assertEquals(4, arr1.length());
    }

    public void testRemoveObj() {
        assertEquals(arr1.remove("hi"), "hi");

        assertEquals(arr1.length(), 2);
        assertEquals("hello", arr1.getInd(0));
        assertEquals("hola", arr1.getInd(1));

        assertNull(arr1.remove("hi"));
    }

    public void testRemoveInd() {
        assertEquals(arr1.removeInd(1), "hello");

        assertEquals(arr1.length(), 2);
        assertEquals("hi", arr1.getInd(0));
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
        assertEquals(arr1.removeFirstInd(), "hi");

        assertEquals(arr1.length(), 2);
        assertEquals("hello", arr1.getInd(0));
        assertEquals("hola", arr1.getInd(1));
    }

    public void testRemoveLastInd() {
        assertEquals(arr1.removeLastInd(), "hola");

        assertEquals(arr1.length(), 2);
        assertEquals("hi", arr1.getInd(0));
        assertEquals("hello", arr1.getInd(1));
    }

    public void testIsEmpty() {
        assertFalse(arr1.isEmpty());
        assertTrue(empty.isEmpty());
    }

    public void testToArray() {
        String[] exp = { "hi", "hello", "hola" };
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

        empty.addEnd("hello");
        empty.addEnd("hi");
        empty.addEnd("hola");

        assertFalse(arr1.equals(empty));

        ArrayBasedList<String> arr2 = new ArrayBasedList<String>();
        arr2.addEnd("hi");
        arr2.addEnd("hello");
        arr2.addEnd("hola");
        assertTrue(arr1.equals(arr2));
    }

}
