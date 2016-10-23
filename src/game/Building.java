package game;

public abstract class Building extends Entity 
{
	private static final long serialVersionUID = 1L;

	public abstract void update(State state);
}
