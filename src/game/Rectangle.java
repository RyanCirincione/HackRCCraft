package game;

import java.nio.ByteBuffer;

//TODO: Stub
public class Rectangle extends Hitbox {
	Vector pos, dim;
	
	public Rectangle()
	{
		pos = new Vector();
		dim = new Vector();
	}
	
	@Override
	public double x()
	{
		return pos.getX();
	}

	@Override
	public Hitbox setX(double x)
	{
		pos.setX(x);
		return null;
	}

	@Override
	public double y()
	{
		return (float)pos.getY();
	}

	@Override
	public Hitbox setY(double y)
	{
		pos.setY(y);
		return this;
	}
	
	double width()
	{
		return dim.getX();
	}
	
	Hitbox setWidth(double width)
	{
		dim.setX(width);
		return this;
	}
	
	double height()
	{
		return (float)dim.getY();
	}
	
	Hitbox setHeight(double height)
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
	
	public void serialize(ByteBuffer buffer) {
		buffer.put((byte)0);
		pos.serialize(buffer);
		dim.serialize(buffer);
	}
	
	public void deserialize(ByteBuffer buffer) {
		pos.deserialize(buffer);
		dim.deserialize(buffer);
	}
}
