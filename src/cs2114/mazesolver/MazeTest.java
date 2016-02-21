package cs2114.mazesolver;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Class MazeTest tests the methods in the Maze class.
 *
 *  @author Tuan Nguyen tuanhmng
 *  @version 2013.10.16
 */
public class MazeTest extends TestCase
{
    //fields
    private MazeCell ue = MazeCell.UNEXPLORED;
    private MazeCell cp = MazeCell.CURRENT_PATH;
    private MazeCell w = MazeCell.WALL;
    private MazeCell fp = MazeCell.FAILED_PATH;
    private MazeCell iv = MazeCell.INVALID_CELL;

    private int size = 10;
    private Maze testMaze;
    private ILocation l;
    private ILocation p;
    private ILocation start;
    private ILocation goal;
    private ILocation upper1 = new Location(8, 11);
    private ILocation upper2 = new Location(11, 8);
    private ILocation lower1 = new Location(-1, 5);
    private ILocation lower2 = new Location(5, -1);

    /**
     * Method setUp() sets up a testMaze for
     * testing purposes.
     */
    public void setUp()
    {
        testMaze = new Maze(size);
        l = new Location(5, 5);
        p = new Location(7, 7);
        start = new Location(0, 0);
        goal = new Location(size - 1, size - 1);

    }

    // ----------------------------------------------------------
    /**
     * Method testSetCell() tests
     * setCell(ILocation l, MazeCell mc).
     */
    public void testSetCell()
    {
        assertEquals(ue, testMaze.getCell(l));
        testMaze.setCell(l, cp);
        assertEquals(cp, testMaze.getCell(l));
        testMaze.setCell(l, fp);
        assertEquals(fp, testMaze.getCell(l));
        testMaze.setCell(l, w);
        assertEquals(w, testMaze.getCell(l));
        testMaze.setCell(l, fp);
        assertEquals(w, testMaze.getCell(l));
        testMaze.setCell(new Location(start.x(), start.y()), w);
        assertEquals(ue, testMaze.getCell(start));
        testMaze.setCell(new Location(goal.x(), goal.y()), w);
        assertEquals(ue, testMaze.getCell(goal));

        testMaze.setCell(lower1, w);
        testMaze.setCell(lower2, w);
        testMaze.setCell(upper1, w);
        testMaze.setCell(upper2, w);
    }

    // ----------------------------------------------------------
    /**
     * Method testGetCell() tests getCell(ILocation l).
     */
    public void testGetCell()
    {
        assertEquals(ue, testMaze.getCell(l));
        testMaze.setCell(l, cp);
        assertEquals(cp, testMaze.getCell(l));
        testMaze.setCell(l, fp);
        assertEquals(fp, testMaze.getCell(l));
        testMaze.setCell(l, w);
        assertEquals(w, testMaze.getCell(l));

        assertEquals(iv, testMaze.getCell(lower1));
        assertEquals(iv, testMaze.getCell(lower2));
        assertEquals(iv, testMaze.getCell(upper1));
        assertEquals(iv, testMaze.getCell(upper2));
    }

    /**
     * Method testGetGoalLocation() tests
     *  method getGoalLocation().
     */
    public void testGetGoalLocation()
    {
        assertTrue(goal.equals(testMaze.getGoalLocation()));
    }

    /**
     * Method testGetStartLocation() tests
     * method getStartLocation().
     */
    public void testGetStartLocation()
    {
        assertTrue(start.equals(testMaze.getStartLocation()));
    }

    /**
     * Method testSetGoalLocation() tests
     *  method setGoalLocation().
     */
    public void testSetGoalLocation()
    {
        testMaze.setCell(l, w);
        testMaze.setGoalLocation(l);
        assertTrue(l.equals(testMaze.getGoalLocation()));
        assertEquals(testMaze.getCell(l), ue);

        testMaze.setGoalLocation(lower1);
        testMaze.setGoalLocation(lower2);
        testMaze.setGoalLocation(upper1);
        testMaze.setGoalLocation(upper2);
    }

    /**
     * Method testSetStartLocation() tests
     * method getStartLocation().
     */
    public void testSetStartLocation()
    {
        testMaze.setCell(p, w);
        testMaze.setStartLocation(p);
        assertTrue(p.equals(testMaze.getStartLocation()));
        assertEquals(testMaze.getCell(p), ue);

        testMaze.setStartLocation(lower1);
        testMaze.setStartLocation(lower2);
        testMaze.setStartLocation(upper1);
        testMaze.setStartLocation(upper2);

    }

    /**
     * Method testSize() tests method size().
     */
    public void testSize()
    {
        assertEquals(size, testMaze.size());
    }

    /**
     * Method testSolve() tests method solve() for a maze
     * that has no solution.
     */
    public void testSolvee()
    {
        testMaze.setCell(new Location(1, 0), w);
        testMaze.setCell(new Location(3, 1), w);
        testMaze.setCell(new Location(5, 2), w);
        testMaze.setCell(new Location(5, 3), w);
        testMaze.setCell(new Location(5, 4), w);
        testMaze.setCell(new Location(5, 5), w);
        testMaze.setCell(new Location(4, 6), w);
        testMaze.setCell(new Location(3, 6), w);
        testMaze.setCell(new Location(2, 5), w);
        testMaze.setCell(new Location(1, 4), w);
        testMaze.setCell(new Location(0, 4), w);
        testMaze.setCell(new Location(2, 0), w);
        testMaze.setCell(new Location(4, 1), w);

        assertEquals(null, testMaze.solve());
    }

    /**
     * Method testSolve() tests method solve() for a maze
     * that has a solution.
     */
    public void testSolve()
    {
        testMaze.setCell(new Location(1, 0), w);
        testMaze.setCell(new Location(3, 1), w);
        testMaze.setCell(new Location(5, 2), w);
        testMaze.setCell(new Location(5, 3), w);
        testMaze.setCell(new Location(5, 4), w);
        testMaze.setCell(new Location(5, 5), w);
        testMaze.setCell(new Location(4, 6), w);
        testMaze.setCell(new Location(3, 6), w);
        testMaze.setCell(new Location(2, 5), w);
        testMaze.setCell(new Location(1, 4), w);
        assertEquals("(0, 0) (0, 1) (1, 1) (2, 1) (2, 2) " +
            "(3, 2) (4, 2) (4, 3) (4, 4) (4, 5) " +
            "(3, 5) (3, 4) (2, 4) (2, 3) (1, 3) " +
            "(0, 3) (0, 4) (0, 5) (1, 5) (1, 6) " +
            "(2, 6) (2, 7) (3, 7) (4, 7) (5, 7) " +
            "(6, 7) (7, 7) (8, 7) (9, 7) (9, 8) " +
            "(9, 9)", testMaze.solve());
    }

    // ----------------------------------------------------------
    /**
     * Tests method deWall().
     */
    public void testDeWall()
    {
        testMaze.setCell(l, w);
        testMaze.deWall(l);
        assertEquals(ue, testMaze.getCell(l));
    }

}
