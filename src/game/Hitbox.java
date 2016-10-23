package game;

import java.nio.ByteBuffer;

public abstract class Hitbox {
	public abstract double x();
	public abstract Hitbox setX(double x);
	public abstract double y();
	public abstract Hitbox setY(double y);
	abstract boolean contains(Vector point);
	abstract boolean collides(Circle circ);
	abstract boolean collides(Rectangle rect);
	abstract void merge(Hitbox other);
	abstract void serialize(ByteBuffer buffer);
	abstract void deserialize(ByteBuffer buffer);
}
