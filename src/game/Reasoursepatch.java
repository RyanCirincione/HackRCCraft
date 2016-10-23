package game;

public class Reasoursepatch extends Building 
{
	int x;
	int y;
	int stuff;
	boolean empty = false;
	public Reasoursepatch()
	{
	}
	public int mine()
	{
		if(empty)
		{
			return 0;
		}
		return 10;
	}
	@Override
	public void update(State state) 
	{
		
	}
	
}
