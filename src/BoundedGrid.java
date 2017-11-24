import java.util.ArrayList;

//A BoundedGrid is a rectangular grid with a finite number of rows and columns.
public class BoundedGrid<E>
{
    private Object[][] occupantArray;  // the array storing the grid elements

    //Constructs an empty BoundedGrid with the given dimensions.
    //(Precondition:  rows > 0 and cols > 0.)
    public BoundedGrid(int rows, int cols)
    {
        occupantArray = new Object[rows][cols];
    }

    public int getNumRows()
    {
        return occupantArray.length;
    }

    public int getNumCols()
    {
        return occupantArray[0].length;
    }

    @SuppressWarnings("unchecked")  
    public E get(Location loc)
    {
        return (E)occupantArray[loc.getRow()][loc.getCol()];
    }

    public E put(Location loc, E obj)
    {
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public boolean isValid(Location loc)
    {
        return(loc.getRow() >= 0 && loc.getRow() < getNumRows() && loc.getCol() >= 0 && loc.getCol() < getNumCols());
    }

    public E remove(Location loc)
    {
        return put(loc,null);
    }

    //returns a list of all occupied locations in this grid
    public ArrayList<Location> getOccupiedLocations()
    {
        //hint:  use the get method to determine if a location is empty
        ArrayList<Location> locs = new ArrayList<Location>();
        for(int r = 0; r < occupantArray.length; r++)
        {
            for(int c = 0; c < occupantArray[r].length; c++)
            {
                Location loc = new Location(r,c);
                if(!(get(loc) == null))
                {
                    locs.add(loc);
                }
            }
        }
        return locs;
    }
    
    
    public ArrayList<Location> getEmptyLocations()
    {
        //hint:  use the get method to determine if a location is empty
        ArrayList<Location> locs = new ArrayList<Location>();
        for(int r = 0; r < occupantArray.length; r++)
        {
            for(int c = 0; c < occupantArray[r].length; c++)
            {
                Location loc = new Location(r,c);
                if(get(loc) == null)
                {
                    locs.add(loc);
                }
            }
        }
        return locs;
    }
}