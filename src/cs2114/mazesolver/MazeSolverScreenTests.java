package cs2114.mazesolver;

import sofia.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import sofia.graphics.ShapeView;

//-------------------------------------------------------------------------
/**
 *  Tests methods in the MazeSolverScreen class.
 *
 *  @author Tuan Nguyen tuanhmng
 *  @version 2013.10.16
 */
public class MazeSolverScreenTests extends
    student.AndroidTestCase<MazeSolverScreen>
{
    //~ Fields ................................................................

    private ShapeView shapeView;
    private TextView infoLabel;

    // This field will store the pixel width/height of a cell in the maze.
    private int cellSize;
    private Button drawWalls;
    private Button eraseWalls;
    private Button setStart;
    private Button setGoal;
    private Button solve;


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public MazeSolverScreenTests()
    {
        super(MazeSolverScreen.class);
    }


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Initializes the text fixtures.
     */
    public void setUp()
    {
        float viewSize =
            Math.min(shapeView.getWidth(), shapeView.getHeight());
        cellSize = (int) viewSize / 10;
    }

    // ----------------------------------------------------------
    /**
     * Tests the button drawWalls.
     * Implicitly tests processTouch and drawWalls.
     */
    public void testDrawWalls()
    {
        clickCell(3, 3);
        click(drawWalls);
        clickCell(1, 1);
        clickCell(0, 0);
        clickCell(9, 9);
        Maze maze = getScreen().getMaze();
        assertEquals(MazeCell.WALL, maze.getCell(new Location(1, 1)));
        assertEquals(Color.yellow, getScreen().getColor(1, 1));
        assertFalse(maze.getCell(new Location(0, 0)).equals(MazeCell.WALL));
        assertFalse(maze.getCell(new Location(9, 9)).equals(MazeCell.WALL));
    }

    // ----------------------------------------------------------
    /**
     * Tests the button eraseWalls.
     * Implicitly tests processTouch and eraseWalls.
     */
    public void testEraseWalls()
    {
        click(drawWalls);
        clickCell(2, 2);
        click(eraseWalls);
        clickCell(2, 2);
        clickCell(0, 0);
        clickCell(9, 9);
        clickCell(5, 5);
        Maze maze = getScreen().getMaze();
        assertEquals(MazeCell.UNEXPLORED, maze.getCell(new Location(2, 2)));
        assertEquals(Color.black, getScreen().getColor(2, 2));
    }
    // ----------------------------------------------------------
    /**
     * Tests the button setStart.
     * Assert that the Location of the start is the same as
     * the location of cell clicked.
     * Implicitly tests processTouch and setStart.
     */
    public void testSetStart()
    {
        clickCell(2, 2); //evaluate all conditions in processTouch to false
        click(setStart);
        clickCell(0, 0);
        clickCell(2, 2);
        Maze maze = getScreen().getMaze();
        assertTrue((maze.getStartLocation()).equals(new Location(2, 2)));
        assertEquals(Color.red, getScreen().getColor(2, 2));
        assertEquals(Color.black, getScreen().getColor(0, 0));
    }

    // ----------------------------------------------------------
    /**
     * Tests the button setGoal.
     * Assert that the Location of the goal is the same as
     * the location of cell clicked.
     * Implicitly tests processTouch and setGoal.
     */
    public void testSetGoal()
    {
        click(setGoal);
        clickCell(9, 9);
        clickCell(8, 8);
        Maze maze = getScreen().getMaze();
        assertTrue((maze.getGoalLocation()).equals(new Location(8, 8)));
        assertEquals(Color.green, getScreen().getColor(8, 8));
        assertEquals(Color.black, getScreen().getColor(9, 9));
    }

    // ----------------------------------------------------------
    /**
     * Tests the button solve for a case
     * when a solution is possible.
     */
    public void testSolve()
    {
        click(drawWalls);
        clickCell(1, 0);
        clickCell(3, 1);
        clickCell(5, 2);
        clickCell(5, 3);
        clickCell(5, 4);
        clickCell(5, 5);
        clickCell(4, 6);
        clickCell(3, 6);
        clickCell(2, 5);
        clickCell(1, 4);
        click(solve);
        assertEquals("(0, 0) (0, 1) (1, 1) (2, 1) (2, 2) " +
            "(3, 2) (4, 2) (4, 3) (4, 4) (4, 5) " +
            "(3, 5) (3, 4) (2, 4) (2, 3) (1, 3) " +
            "(0, 3) (0, 4) (0, 5) (1, 5) (1, 6) " +
            "(2, 6) (2, 7) (3, 7) (4, 7) (5, 7) " +
            "(6, 7) (7, 7) (8, 7) (9, 7) (9, 8) " +
            "(9, 9)",
            (String) infoLabel.getText());
        assertEquals(Color.purple, getScreen().getColor(3, 3));
    }

    // ----------------------------------------------------------
    /**
     * Tests the button solve in a case when
     * a solution is not possible.
     */
    public void testSolvee()
    {
        click(drawWalls);
        clickCell(1, 0);
        clickCell(1, 1);
        clickCell(1, 2);
        clickCell(0, 2);
        touchDownCell(3, 3);
        touchMoveCell(3, 4);
        touchUp();
        click(solve);
        assertEquals("No solution was possible.",
            (String) infoLabel.getText());
    }

    //~ Private methods .......................................................

    // ----------------------------------------------------------
    /**
     * Simulates touching down on the middle of the specified cell in the maze.
     */
    private void touchDownCell(int x, int y)
    {
        touchDown(shapeView, (x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates moving the finger instantaneously to the middle of the
     * specified cell in the maze.
     */
    private void touchMoveCell(int x, int y)
    {
        touchMove((x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates clicking the middle of the specified cell in the maze. This is
     * equivalent to calling: touchDownCell(x, y); touchUp();
     */
    private void clickCell(int x, int y)
    {
        touchDownCell(x, y);
        touchUp();
    }
}
