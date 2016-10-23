package game;
//TODO: STUB
public class Circle extends Hitbox {
	Vector pos;
	double radius;
	
	public Circle(){
		radius = 0;
	}

	@Override
	public float x()
	{
		return (float)pos.getX();
	}

	@Override
	public Hitbox setX(float x)
	{
		pos.setX(x);
		return this;
	}

	@Override
	public float y()
	{
		return (float)pos.getY();
	}

	@Override
	public Hitbox setY(float y)
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
		float closestX = x();
		float closestY = y();

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
}
