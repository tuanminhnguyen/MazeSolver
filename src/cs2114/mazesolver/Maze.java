package cs2114.mazesolver;

import java.util.Stack;

// -------------------------------------------------------------------------
/**
 *  Class Maze creates a maze and figures out how to
 *  solve it.
 *
 *  @author Tuan Nguyen tuanhmng
 *  @version 2013.10.16
 */
public class Maze implements IMaze
{
    //fields
    private MazeCell ue = MazeCell.UNEXPLORED;
    private MazeCell cp = MazeCell.CURRENT_PATH;
    private MazeCell fp = MazeCell.FAILED_PATH;
    private MazeCell iv = MazeCell.INVALID_CELL;
    private MazeCell w = MazeCell.WALL;
    private int size;
    private MazeCell[][] tmaze;
    private ILocation newcell;
    private ILocation start;
    private ILocation goal;

    // ----------------------------------------------------------
    /**
     * Create a new Maze object.
     * @param s is the size input.
     */
    //constructor
    public Maze(int s)
    {
        size = s;
        tmaze = new MazeCell[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                tmaze[i][j] = ue;
            }
        }
        start = new Location(0, 0);
        goal = new Location(size - 1, size - 1);
    }

    /**
     * Method getCell.
     * @param l is the location to get value.
     * @return the value of l.
     */
    public MazeCell getCell(ILocation l)
    {
        if (l.x() > size - 1 || l.y() > size - 1
            || l.x() < 0 || l.y() < 0)
        {
            return iv;
        }
        else
        {
            return tmaze[l.x()][l.y()];
        }
    }

    /**
     * Method setCell sets the MazeCell value at location
     * l to mc.
     * @param l is to location to be set.
     * @param mc is MazeCell value to be set.
     */
    public void setCell(ILocation l, MazeCell mc)
    {
        if (!(l.x() > size - 1 || l.y() > size - 1
            || l.x() < 0 || l.y() < 0) &&
            tmaze[l.x()][l.y()] != w)
        {
            if (mc == w)
            {
                if (!l.equals(goal) && !l.equals(start))
                {
                    tmaze[l.x()][l.y()] = mc;
                }
            }
            else
            {
                tmaze[l.x()][l.y()] = mc;
            }
        }
    }
    /**
     * Method getGoalLocation.
     * @return goal.
     */
    public ILocation getGoalLocation()
    {
        return goal;
    }

    /**
     * Method getStartLocation.
     * @return start.
     */
    public ILocation getStartLocation()
    {
        return start;
    }

    /**
     * Method setGoalLocation sets goal.
     * @param l is location to be set as goal.
     */
    public void setGoalLocation(ILocation l)
    {
        if ( !(l.x() > size - 1 || l.y() > size - 1
            || l.x() < 0 || l.y() < 0))
        {
            tmaze[l.x()][l.y()] = ue;
            goal = l;
        }

    }


    /**
     * Method setStartLocation sets start.
     * @param l is location to be set as start.
     */
    public void setStartLocation(ILocation l)
    {
        if ( !(l.x() > size - 1 || l.y() > size - 1
            || l.x() < 0 || l.y() < 0))
        {
            tmaze[l.x()][l.y()] = ue;
            start = l;
        }
    }


    /**
     * Method size returns the size of the maze.
     * @return size of maze.
     */
    public int size()
    {
        return size;
    }

    /**
     * Method solve() solves the maze.
     * @return a string representing a solution
     * path if one exists, or null if there is no solution.
     */
    @Override
    public String solve()
    {
        String solution;
        Stack<ILocation> path = new Stack<ILocation>();
        setCell(start, cp);
        path.push(start);
        while (!path.empty() && !path.peek().equals(goal))
        {
            if (getCell(path.peek().east()).equals(ue))
            {
                newcell = path.peek().east();
                setCell(newcell, cp);
                path.push(newcell);
            }
            else if (getCell(path.peek().south()).equals(ue))
            {
                newcell = path.peek().south();
                setCell(newcell, cp);
                path.push(newcell);
            }
            else if (getCell(path.peek().west()).equals(ue))
            {
                newcell = path.peek().west();
                setCell(newcell, cp);
                path.push(newcell);

            }
            else if (getCell(path.peek().north()).equals(ue))
            {
                newcell = path.peek().north();
                setCell(newcell, cp);
                path.push(newcell);
            }
            else
            {
                setCell(path.peek(), fp);
                path.pop();
            }
        }

        if (!path.empty())
        {
            int d = path.size();
            solution = path.pop().toString();
            for (int i = 1; i < d; i++)
            {
                solution = path.pop().toString() + " " + solution;
            }
        }
        else
        {
            solution = null;
        }
        return solution;
    }

    // ----------------------------------------------------------
    /**
     * Sets a cell to UNEXPLORED if it is a WALL.
     * Used by MazeSolveScreen in eraseWalls().
     * @param l is the location of the cell to be set.
     */
    public void deWall(ILocation l)
    {
        if ((!(l.x() > size - 1 || l.y() > size - 1
            || l.x() < 0 || l.y() < 0)) &&
            (tmaze[l.x()][l.y()] == w))
        {
            tmaze[l.x()][l.y()] = ue;
        }
    }

}
