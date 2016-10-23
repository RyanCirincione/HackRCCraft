package game;

public abstract class Hitbox {
	abstract float x();
	abstract Hitbox setX(float x);
	abstract float y();
	abstract Hitbox setY(float y);
	abstract boolean contains(Vector point);
	abstract boolean collides(Circle circ);
	abstract boolean collides(Rectangle rect);
	abstract void merge(Hitbox other);
}
