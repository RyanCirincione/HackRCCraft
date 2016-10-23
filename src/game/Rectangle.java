package game;
//TODO: Stub
public class Rectangle extends Hitbox {
	Vector pos, dim;
	
	public Rectangle()
	{
		pos = new Vector();
		dim = new Vector();
	}
	
	@Override
	float x()
	{
		return (float)pos.getX();
	}

	@Override
	Hitbox setX(float x)
	{
		pos.setX(x);
		return null;
	}

	@Override
	float y()
	{
		return (float)pos.getY();
	}

	@Override
	Hitbox setY(float y)
	{
		pos.setY(y);
		return this;
	}
	
	float width()
	{
		return (float)dim.getX();
	}
	
	Hitbox setWidth(float width)
	{
		dim.setX(width);
		return this;
	}
	
	float height()
	{
		return (float)dim.getY();
	}
	
	Hitbox setHeight(float height)
	{
		dim.setY(height);
		return this;
	}

	@Override
	boolean contains(Vector point)
	{
		return point.getX() >= pos.getX() && point.getX() < pos.getX() + dim.getX() &&
				point.getY() >= pos.getY() && point.getY() < pos.getY() + dim.getY();
	}

	@Override
	boolean collides(Circle circ)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean collides(Rectangle rect)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public void merge(Hitbox box) {
		Rectangle other = (Rectangle)box;
		pos.set(other.x(), other.y());
		dim.set(other.width(), other.height());
	}
}
