
public abstract class Hitbox {
	abstract float x();
	abstract float setX(float x);
	abstract float y();
	abstract float setY(float y);
	abstract float left();
	abstract float right();
	abstract float top();
	abstract float bottom();
	abstract boolean contains(Vector point);
	abstract boolean collides(Circle circ);
	abstract boolean collides(Rectangle rect);
}
