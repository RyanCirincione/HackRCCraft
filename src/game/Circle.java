package game;

import java.nio.ByteBuffer;

//TODO: STUB
public class Circle extends Hitbox {
	Vector pos;
	double radius;
	
	public Circle() {
		pos = new Vector();
		radius = 0;
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
		return this;
	}

	@Override
	public double y()
	{
		return pos.getY();
	}

	@Override
	public Hitbox setY(double y)
	{
		pos.setY(y);
		return this;
	}
	
	float radius(){
		return (float)radius;
	}
	
	Hitbox setRadius(float radius){
		this.radius = radius;
		return this;
	}
	
	@Override
	boolean collides(Circle circ)
	{
		return new Vector(pos).sub(new Vector(circ.x(), circ.y())).mag() < radius() + circ.radius();
	}
	
	@Override
	boolean collides(Rectangle rect)
	{
		double closestX = x();
		double closestY = y();

		if (x() < rect.x()) {
			closestX = rect.x();
		} else if (x() > rect.x() + rect.width()) {
			closestX = rect.x() + rect.width();
		}

		if (y() < rect.y()) {
			closestY = rect.y();
		} else if (y() > rect.y() + rect.height()) {
			closestY = rect.y() + rect.height();
		}

		closestX = closestX - x();
		closestX *= closestX;
		closestY = closestY - y();
		closestY *= closestY;

		return closestX + closestY < radius * radius;
	}

	@Override
	boolean contains(Vector point)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public void merge(Hitbox box) {
		Circle circ = (Circle)box;
		pos.set(circ.x(), circ.y());
		radius = circ.radius;
	}
	
	public void serialize(ByteBuffer buffer) {
		buffer.put((byte)1);
		pos.serialize(buffer);
		buffer.putFloat((float)radius);
	}
	
	public void deserialize(ByteBuffer buffer) {
		buffer.get();
		pos.deserialize(buffer);
		radius = buffer.getFloat();
	}
}
