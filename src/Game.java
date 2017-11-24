public class Game implements ArrowListener{
	public static void main(String args[])
	{
		Game game = new Game();
		game.play();
	}
	
	private BoundedGrid<Block> grid;
	private BlockDisplay display;
	
	public Game()
	{
		grid = new BoundedGrid<Block>(4,4);
		display = new BlockDisplay(grid);
		display.setTitle("2048");
		display.setArrowListener(this);
		new Block(grid);

	}
	
	public void play()
	{
		display.showBlocks();
		while(true)
		{}
	}
	
	public void clearCombined() //resets the "combined" boolean on all blocks
	{
		for(int r = 0; r < grid.getNumRows(); r++)
			for(int c = 0; c < grid.getNumCols(); c++)
				if(grid.get(new Location(r,c)) instanceof Block)
					grid.get(new Location(r,c)).combine(false);
	}
	
	public void upPressed()
	{
		for(int c = 0; c < grid.getNumCols(); c++) //left column to right
		{
			boolean add = true;
			for(int r = 0; r < grid.getNumRows(); r++) //move down through each col's blocks
			{
				Location loc = new Location(r,c);
				if(grid.isValid(loc))
					if(grid.get(loc) != null)
						grid.get(loc).slide(-1, 0);
			}
		}
		new Block(grid);
		clearCombined();
		display.showBlocks();
	}
	public void downPressed()
	{
		for(int c = 0; c < grid.getNumCols(); c++) //left column to right
		{
			boolean add = true;
			for(int r = grid.getNumRows() - 1; r >= 0; r--) //move up through each col's blocks
			{
				Location loc = new Location(r,c);
				if(grid.isValid(loc))
					if(grid.get(loc) != null)
						grid.get(loc).slide(1, 0);
			}
		}
		new Block(grid);
		clearCombined();
		display.showBlocks();
		
	}
	public void leftPressed()
	{
		for(int r = 0; r < grid.getNumRows(); r++) //top row to bottom
		{
			boolean add = true;
			for(int c = 0; c < grid.getNumCols(); c++) //move each block starting with left most 
			{
				Location loc = new Location(r,c);
				if(grid.isValid(loc))
					if(grid.get(loc) != null)
						grid.get(loc).slide(0, -1);
			}
		}
		new Block(grid);
		clearCombined();
		display.showBlocks();
		
	}
	public void rightPressed()
	{
		for(int r = 0; r < grid.getNumRows(); r++) //top row to bottom
		{
			boolean add = true;
			for(int c = grid.getNumCols() - 1; c >= 0; c--) //move each block starting with right most 
			{
				Location loc = new Location(r,c);
				if(grid.isValid(loc))
					if(grid.get(loc) != null)
						grid.get(loc).slide(0, 1);
			}
		}
		new Block(grid);
		clearCombined();
		display.showBlocks();
	}
}
