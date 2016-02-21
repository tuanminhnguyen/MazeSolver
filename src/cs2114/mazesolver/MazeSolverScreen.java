package cs2114.mazesolver;

import android.widget.TextView;
import sofia.app.ShapeScreen;
import sofia.graphics.RectangleShape;
import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 *  Class MazeSolverScreen creates a MazeSolverScreen, adds it
 *  to the canvas. A MazeSolverScreen consists of a grid of
 *  square cells, each of which can be turned into
 *  a starting point, a goal, a wall, or not a wall. All cells are
 *  initially blacked. If in "drawWalls" mode and a cell is touched, it
 *  changes to yellow. If in "eraseWalls" mode and a Wall (yellow) cell
 *  is touched, it changes back to black. Solution path is indicated
 *  in blue. Start is marked with a red rectangle; goal is marked with a green
 *  rectangle. The solution is also printed at the bottom. If there is no
 *  solution, the text "No solution was possible." is printed.
 *
 *  @author  Tuan Nguyen tuanhmng
 *  @version 2013.10.15
 */
public class MazeSolverScreen extends ShapeScreen
{
    //~ Fields ................................................................
    private Maze gameMaze;
    private RectangleShape[][] grid;
    private String state;
    private RectangleShape goal;
    private RectangleShape start;
    private float side;
    private TextView infoLabel;
    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Create a new MazeSolverScreen object.
     */
    public void initialize()
    {
        gameMaze = new Maze(10);
        grid = new RectangleShape[10][10];
        float size = Math.min(getWidth(), getHeight());
        side = size / 10;
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                RectangleShape tile = new RectangleShape(i * side, j * side,
                    (i + 1) * side, (j + 1) * side);
                tile.setColor(Color.red);
                tile.setFillColor(Color.black);
                add(tile);
                grid[i][j] = tile;
            }
        }
        //change the tile at start location to red
        int x = gameMaze.getStartLocation().x();
        int y = gameMaze.getStartLocation().y();
        start = grid[x][y];
        start.setFillColor(Color.red);

        //change the tile at goal location to green
        x = gameMaze.getGoalLocation().x();
        y = gameMaze.getGoalLocation().y();
        goal = grid[x][y];
        goal.setFillColor(Color.green);
    }

    // ----------------------------------------------------------
    /**
     * Changes the state of MazeSolverScreen to "drawWalls".
     */
    public void drawWallsClicked()
    {
        state = "drawWalls";
    }

    /**
     * Changes the state of MazeSolverScreen to "eraseWalls".
     */
    public void eraseWallsClicked()
    {
        state = "eraseWalls";
    }

    /**
     * Changes the state of MazeSolverScreen to "setStart".
     */
    public void setStartClicked()
    {
        state = "setStart";
    }

    /**
     * Changes the state of MazeSolverScreen to "setGoal".
     */
    public void setGoalClicked()
    {
        state = "setGoal";
    }

    // ----------------------------------------------------------
    /**
     * Processes touch-trigger according to the state
     * if the tile touched is not null and state is not null.
     * @param x is the x-coordinate of the pixel being clicked.
     * @param y is the y-coordinate of the pixel being clicked.
     */
    public void processTouch(float x, float y)
    {
        int x0 = (int) (x / side);
        int y0 = (int) (y / side);
        RectangleShape tile = grid[x0][y0];
        if (tile != null && state != null)
        {
            if (state.equals("setGoal"))
            {
                if (tile != goal)

                {
                    goal.setFillColor(Color.black);
                    goal = tile;
                    goal.setFillColor(Color.green);
                    gameMaze.setGoalLocation(new Location(x0, y0));
                }
            }
            else if (state.equals("setStart"))
            {
                if (tile != start)
                {
                    start.setFillColor(Color.black);
                    goal = tile;
                    goal.setFillColor(Color.red);
                    gameMaze.setStartLocation(new Location(x0, y0));
                }
            }
            else if (state.equals("drawWalls"))
            {
                if (tile != start && tile != goal
                    && tile.getColor() != Color.yellow)
                {
                    tile.setFillColor(Color.yellow);
                    gameMaze.setCell(new Location(x0, y0), MazeCell.WALL);
                }
            }
            else
            {
                if (tile != start && tile != goal
                    && tile.getColor() != Color.black)
                {
                    tile.setFillColor(Color.black);
                    gameMaze.deWall(new Location(x0, y0));
                }
            }

        }
    }

// ----------------------------------------------------------
    /**
     * Calls method processTouch(x, y) when the screen is touched down.
     * @param x is the x-coordinate of the pixel being touched.
     * @param y is the y-coordinate of the pixel being touched.
     */
    public void onTouchDown(float x, float y)
    {
        processTouch(x, y);
    }

    /**
     * Calls method processTouch(x, y) when the screen is touched drag.
     * @param x is the x-coordinate of the pixel being touched.
     * @param y is the y-coordinate of the pixel being touched.
     */
    public void onTouchMove(float x, float y)
    {
        processTouch(x, y);
    }


// ----------------------------------------------------------
    /**
     * Calls method solve() in Maze class when the
     * solve button is clicked.
     */
    public void solveClicked()
    {

        String sol = gameMaze.solve();
        if (sol == null)
        {
            infoLabel.setText("No solution was possible.");
        }
        else
        {
            infoLabel.setText(sol);
            for (int i = 0; i < 10; i++)
            {
                for (int j = 0; j < 10; j++)
                {
                    if (gameMaze.getCell(new Location(i, j)) ==
                        MazeCell.CURRENT_PATH)
                    {
                        grid[i][j].setFillColor(Color.blue);
                    }
                    else if (gameMaze.getCell(new Location(i, j)) ==
                        MazeCell.FAILED_PATH)
                    {
                        grid[i][j].setFillColor(Color.purple);
                    }
                }
            }
            start.setFillColor(Color.red);
            goal.setFillColor(Color.green);
        }
    }

// ----------------------------------------------------------
    /**
     * getMaze() returns the current state of
     * gameMaze.
     * @return outputMaze.
     */
    public Maze getMaze()
    {
        Maze outputMaze = gameMaze;
        return outputMaze;
    }

// ----------------------------------------------------------
    /**
     * Gets the color of the tile at (x,y) index.
     * @param x the x-index of the cell touched.
     * @param y the y-index of the cell touched.
     * @return the color of the tile at (x,y) index.
     */
    public Color getColor(int x, int y)
    {
        return grid[x][y].getFillColor();
    }
}
