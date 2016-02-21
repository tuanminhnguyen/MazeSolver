package cs2114.mazesolver;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Class LocationTest tests the methods of
 *  class Location.
 *
 * @author Tuan Nguyen tuanhmng
 *  @version 2013.10.16
 */
public class LocationTest extends TestCase
{
    //fields
    private Location testLocation;
    private Location l1;
    private Location l2;
    private Location l3 = null;

    /**
     * Method setUp() sets up a new testLocation
     * for testing purposes.
     */
    public void setUp()
    {
        testLocation = new Location(0, 0);
        l1 = new Location(1, 1);
        l2 = new Location(1, 0);
    }

    // ----------------------------------------------------------
    /**
     * Method testEquals() tests method equals().
     */
    public void testEquals()
    {
        Integer x = new Integer(3);
        assertFalse(l1.equals(x));
        assertFalse(l1.equals(l3));
        l3 = l2.south();
        assertTrue(l3.equals(l1));
        assertFalse(testLocation.equals(l2));
    }

    /**
     * Method testToString() tests method toString().
     */
    public void testToString()
    {
        assertEquals("(0, 0)", testLocation.toString());
    }

    // ----------------------------------------------------------
    /**
     * Method testEast() tests method east().
     */
    public void testEast()
    {
        l1 = l1.east();
        assertEquals("(2, 1)", l1.toString());
    }

    /**
     * Method testNorth() tests method north().
     */
    public void testNorth()
    {
        l1 = l1.north();
        assertEquals("(1, 0)", l1.toString());
    }

    /**
     * Method testSouth tests method south.
     */
    public void testSouth()
    {
        l1 = l1.south();
        assertEquals("(1, 2)", l1.toString());
    }

    /**
     * Method testWest tests method west.
     */
    public void testWest()
    {
        l1 = l1.west();
        assertEquals("(0, 1)", l1.toString());
    }

    /**
     * Method testX() tests method x().
     */
    public void testX()
    {
        assertEquals(1, l1.x());
    }

    /**
     * Method testY() tests method y().
     */
    public void testY()
    {
        assertEquals(1, l1.y());
    }

}
