import java.awt.Color;
import java.util.ArrayList;
public class Block {
	private BoundedGrid<Block> grid;
	private Location location;
	private Color color;
	private int num = 2;
	private boolean combined;
	
	private static final Color TWO = new Color(238, 228, 218);
	private static final Color FOUR = new Color(236, 224, 202);
	private static final Color EIGHT = new Color(243, 177, 119);
	private static final Color SIXTEEN = new Color(244, 154, 92);
	private static final Color THIRTWO = new Color(248, 122, 97);
	private static final Color SIXFOUR = new Color(246, 94, 57);
	private static final Color ONETWOEIGHT = new Color(235, 203, 116);
	private static final Color TWOFIVESIX = new Color(204, 192, 178);
	private static final Color FIVETWEL = new Color(236, 199, 84);
	private static final Color TENTWOFOUR = new Color(238, 198, 61);

	public Block(BoundedGrid<Block> gr) {
		grid = gr;
		color = pickColor();
		combined = false;
		location = getNewLocation();  
		putSelfInGrid(gr, location);
	}
	
	public Location getNewLocation()
	{
		ArrayList<Location> locs = grid.getEmptyLocations();
		Location loc = locs.get((int) (Math.random() * locs.size()));
		return loc;
	}
	public boolean combined()
	{
		return combined;
	}
	public void combine(boolean value)
	{
		combined = value;
	}
	public void setNumber(int num)
	{
		this.num = num;
	}
	public int getNumber()
	{
		return num;
	}
	public void doubleNum()
	{
		num = num * 2;
	}
	public Color pickColor() {
		switch (num) {
		case 2:
			color = TWO;
			break;
		case 4:
			color = FOUR;
			break;
		case 8:
			color = EIGHT;
			break;
		case 16:
			color = SIXTEEN;
			break;
		case 32:
			color = THIRTWO;
			break;
		case 64:
			color = SIXFOUR;
			break;
		case 128:
			color = ONETWOEIGHT;
			break;
		case 256:
			color = TWOFIVESIX;
			break;
		case 512:
			color = FIVETWEL;
			break;
		case 1024:
			color = TENTWOFOUR;
			break;
		}
		return color;
	}
	public Color getColor() {
		return color;
	}
	public void setColor()
	{
		color = pickColor();
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location loc) {
		location = loc;
	}
	public BoundedGrid<Block> getGrid() {
		return grid;
	}
	public void putSelfInGrid(BoundedGrid<Block> gr, Location loc) {
		if (gr.get(loc) != null)
			gr.get(loc).removeSelfFromGrid();
		gr.put(loc, this);
		location = loc;
		grid = gr;
	}
	public void removeSelfFromGrid() {
		grid.put(location, null);
		location = null;
		grid = null;
	}
	
	public void slide(int delx, int dely) //returns true if block combining has happened, false if otherwise
	{ 
			Location oldloc = location;
			Location loc = location;
			Location goloc = location;
			while (grid.isValid(loc))
			{
				if(grid.get(loc) == null)
					goloc= loc; //this is the location we're going to, we want to save it
				loc = new Location(loc.getRow() + delx, loc.getCol() + dely);
			}
				Location testloc = new Location(goloc.getRow() + delx, goloc.getCol() + dely); //grab a location one more over			
			if(grid.isValid(testloc)) //if that test location is valid
				if(grid.get(testloc) != null)
				if(grid.get(testloc).getNumber() == num && !grid.get(testloc).combined()) //and if its number is the same as ours
				{
					grid.get(testloc).doubleNum(); //double that blocks number
					grid.get(testloc).setColor(); //update its color
					grid.get(testloc).combine(true);
					removeSelfFromGrid(); //remove this block
					return;
				}
			grid.put(oldloc, null);
			putSelfInGrid(grid, goloc);
	}	
}


