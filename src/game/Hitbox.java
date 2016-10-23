package game;

import java.io.Serializable;

public abstract class Hitbox implements Serializable {
	private static final long serialVersionUID = 1L;
	public abstract double x();
	public abstract double centerX();
	public abstract Hitbox setX(double x);
	public abstract double y();
	public abstract double centerY();
	public abstract Hitbox setY(double y);
	abstract boolean contains(Vector point);
	abstract boolean collides(Circle circ);
	abstract boolean collides(Rectangle rect);
	abstract void merge(Hitbox other);
	public String toString()
	{
		return x() + " " + y();
	}
}
