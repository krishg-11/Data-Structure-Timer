/**
 * 
 */
package functiontimer;

import student.TestCase;

/**
 * @author Krish Ganotra
 * @version 12.06.2021
 */
public class BinarySearchTreeTest extends TestCase {

    BinarySearchTree<String> tree;
    BinarySearchTree<String> emptyTree;

    /**
     * Instantiate Variables
     */
    public void setUp() {
        tree = new BinarySearchTree<String>();
        emptyTree = new BinarySearchTree<String>();
        tree.addAnywhere("c");
        tree.addAnywhere("a");
        tree.addAnywhere("b");
        tree.addAnywhere("g");
        tree.addAnywhere("e");
        tree.addAnywhere("d");
        tree.addAnywhere("f");
        tree.addAnywhere("h");

    }

    public void testGetInd() {
        Exception e = null;
        try {
            tree.getInd(0);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

       /**
     * Test findMin method
     */
    public void testGetFirst() {
        assertEquals(tree.getFirst(), "a");
        assertNull(emptyTree.getFirst());

    }
    
    public void testGetLast() {
        assertEquals(tree.getLast(), "h");
        assertNull(emptyTree.getLast());
    }

    public void testContains() {
       assertTrue(tree.contains("a"));
       assertFalse(emptyTree.contains("a"));
       assertFalse(tree.contains("no"));
    }

    public void testAddAnywhere() {
        assertEquals(tree.toString(), "(a, b, c, d, e, f, g, h)");
        tree.addAnywhere("bye");
        assertEquals(tree.toString(), "(a, b, bye, c, d, e, f, g, h)");

        Exception e = null;
        try {
            tree.addAnywhere("a");
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof DuplicateItemException);
    }

    public void testAddEnd() {
        Exception e = null;
        try {
            tree.addEnd("hi");
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
            tree.addFront("hi");
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
            tree.addInd("hi", 0);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    /**
     * Test remove method
     */
    public void testRemove() {
        assertEquals(tree.toString(), "(a, b, c, d, e, f, g, h)");
        tree.remove("f");
        assertEquals(tree.toString(), "(a, b, c, d, e, g, h)");
        tree.remove("e");
        assertEquals(tree.toString(), "(a, b, c, d, g, h)");
        tree.remove("a");
        assertEquals(tree.toString(), "(b, c, d, g, h)");
        tree.remove("c");
        assertEquals(tree.toString(), "(b, d, g, h)");
        tree.remove("c");
        assertEquals(tree.toString(), "(b, d, g, h)");

        emptyTree.remove("c");
        assertEquals(emptyTree.toString(), "()");
    }

    public void testRemoveFirstInd() {
        assertEquals(tree.toString(), "(a, b, c, d, e, f, g, h)");
        tree.removeFirstInd();
        assertEquals(tree.toString(), "(b, c, d, e, f, g, h)");
        tree.removeFirstInd();
        assertEquals(tree.toString(), "(c, d, e, f, g, h)");
        
        assertEquals(tree.length(), 6);
    }

    public void testRemoveLastInd() {
        assertEquals(tree.toString(), "(a, b, c, d, e, f, g, h)");
        tree.removeLastInd();
        assertEquals(tree.toString(), "(a, b, c, d, e, f, g)");
        tree.removeLastInd();
        assertEquals(tree.toString(), "(a, b, c, d, e, f)");
        
        assertEquals(tree.length(), 6);
    }

    public void testRemoveInd() {
        Exception e = null;
        try {
            tree.removeInd(0);
            fail();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNotNull(e);
        assertTrue(e instanceof InvalidFunctionException);
    }

    public void testLength() {
        assertEquals(tree.length(), 8);
        assertEquals(emptyTree.length(), 0);
    }

    /**
     * Test isEmpty method
     */
    public void testIsEmpty() {
        assertTrue(emptyTree.isEmpty());
        assertFalse(tree.isEmpty());
    }

    public void testToArray() {

    }
}
