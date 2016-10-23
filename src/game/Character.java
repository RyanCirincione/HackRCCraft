package game;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Character extends Entity 
{
	private static final long serialVersionUID = 1L;
	public Vector speed;
	
	public Character() {
		speed = new Vector();
		try
		{
			img = ImageIO.read(new File("res/Player.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void merge(Entity other) {
		super.merge(other);
		Character c = (Character)other;
		speed.set(c.speed.getX(), c.speed.getY());
	}
	
}
