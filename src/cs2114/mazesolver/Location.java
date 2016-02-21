package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 *  Class Location creates object of type Location
 *  to represent the location of things in x-y
 *  coordinates.
 *
 *  @author Tuan Nguyen tuanhmng
 *  @version 2013.10.16
 */

public class Location implements ILocation
{
    //fields
    private int xc;
    private int yc;

    // ----------------------------------------------------------
    /**
     * Create a new Location object.
     * @param x is the x-coordinate.
     * @param y is the y-coordinate.
     */
    //constructor
    public Location(int x, int y)
    {
        xc = x;
        yc = y;
    }

    // ----------------------------------------------------------
    /**
     * Method equals check whether two Location
     * objects are the same.
     * @Override equals
     * @param l is the Location object to be compared.
     * @return true if the two locations are the same
     * location.
     */
    public boolean equals(Object l)
    {
        if (l == null)
        {
            return false;
        }
        else if (!(l instanceof ILocation))
        {
            return false;
        }
        else
        {
            ILocation d = (ILocation) l;
            return (xc == d.x() && yc == d.y());
        }
    }

    /**
     * Method toString() returns the coordinate of
     * object Location.
     * @return String "(x,y)".
     * @Override toString().
     */
    public String toString()
    {
        return "(" + Integer.toString(xc) + ", " +
            Integer.toString(yc) + ")";

    }

    // ----------------------------------------------------------
    // ----------------------------------------------------------
    /**
     * Method east gets a new location that
     * represents the (x, y) coordinates one
     * cell east of this location.
     * @Override
     * @return a new location.
     */
    public Location east()
    {
        return new Location(xc + 1, yc);
    }


    // ----------------------------------------------------------
    /**
     * Method north gets a new location that
     * represents the (x, y) coordinates one
     * cell north of this location.
     * @Override
     * @return a new location.
     */
    public Location north()
    {
        return new Location(xc, yc - 1);

    }


    // ----------------------------------------------------------
    /**
     * Method south gets a new location that
     * represents the (x, y) coordinates one
     * cell south of this location.
     * @Override
     * @return a new location.
     */
    public Location south()
    {
        return new Location(xc, yc + 1);
    }


    // ----------------------------------------------------------
    /**
     * Method west gets a new location that
     * represents the (x, y) coordinates one
     * cell west of this location.
     * @Override
     * @return a new location.
     */
    public Location west()
    {
        return new Location(xc - 1, yc);
    }


    // ----------------------------------------------------------
    /**
     * Method x returns the x-coordinate of the location l.
     * @return x-coordinate.
     * @Override
     */
    public int x()
    {
        return xc;
    }


    // ----------------------------------------------------------
    /**
     * Method y returns the y-coordinate of location l.
     * @return y-coordinate.
     * @Override
     */
    public int y()
    {
        return yc;
    }


}
