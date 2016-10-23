package game;

public class Character extends Entity 
{
	private static final long serialVersionUID = 1L;
	public Vector speed;
	
	public Character() {
		speed = new Vector();
	}
	
	@Override
	public void merge(Entity other) {
		super.merge(other);
		Character c = (Character)other;
		speed.set(c.speed.getX(), c.speed.getY());
	}
	
}
