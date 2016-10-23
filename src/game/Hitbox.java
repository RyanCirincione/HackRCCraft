package game;

public abstract class Hitbox {
	public abstract float x();
	public abstract Hitbox setX(float x);
	public abstract float y();
	public abstract Hitbox setY(float y);
	abstract boolean contains(Vector point);
	abstract boolean collides(Circle circ);
	abstract boolean collides(Rectangle rect);
	abstract void merge(Hitbox other);
}
