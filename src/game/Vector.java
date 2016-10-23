package game;

import java.io.Serializable;

/**
 * MIT License

Copyright (c) 2016 Jason Carrete

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 * Represents a mathematical vector in a 2-dimensional space.
 * This class is mutable so any operations done on an instance of this class
 * will change the internal variables.
 */
public class Vector implements Serializable {
	public static final double MAX_DOUBLE_PRECISION = 0.0000000000001;
	/**
	 * Compares two doubles for approximate equality.
	 * Uses {@value #MAX_DOUBLE_PRECISION} as the margin of error.
	 * @param d1 double 1
	 * @param d2 double 2
	 * @return true if the absolute value of {@code d1 - d2} equals 0, otherwise false
	 */
	public static boolean equals(double d1, double d2) {
		return equals(d1, d2, MAX_DOUBLE_PRECISION);
	}
	
	/**
	 * Compares two doubles for approximate equality.
	 * @param d1 double 1
	 * @param d2 double 2
	 * @param margin the margin of error
	 * @return true if the absolute value of {@code d1 - d2} equals 0, otherwise false
	 */
	public static boolean equals(double d1, double d2, double margin) {
		return Math.abs(d1 - d2) < margin;
	}
	/** x component of this {@link Vector}. */
	private double x;
	/** y component of this {@link Vector}. */
	private double y;
	
	/**
	 * Both the x and y components are set to 0.
	 */
	public Vector() {
		this(0, 0);
	}
	
	/**
	 * @param x the x-component
	 * @param y the y-component
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Copy constructor.
	 * @param v the {@link Vector} to be copied
	 */
	public Vector(Vector v) {
		this(v.x, v.y);
	}
	
	/**
	 * Adds this {@link Vector} to the specified Vector2 <i>v</i>.
	 * @param v Vector2 summand
	 * @return this Vector2 after being added with <i>v</i>
	 */
	public Vector add(Vector v) {
		this.x += v.x;
		this.y += v.y;
		return this;
	}
	
	/**
	 * Subtracts the specified {@link Vector} <i>v</i> from this Vector2.
	 * @param v Vector2 subtrahend
	 * @return this Vector2 after the subtraction operation
	 */
	public Vector sub(Vector v) {
		this.x -= v.x;
		this.y -= v.y;
		return this;
	}
	
	/**
	 * Scales this {@link Vector} by <i>scale</i>.
	 * @param scale amount to scale this Vector2 by
	 * @return this Vector2 after it has been scaled
	 */
	public Vector scl(double scale) {
		this.x *= scale;
		this.y *= scale;
		return this;
	}
	
	/**
	 * @param v other {@link Vector} operand.
	 * @return the dot product of this Vector2 and <i>v</i>
	 */
	public double dot(Vector v) {
		return this.x * v.x + this.y * v.y;
	}
	
	/**
	 * @param v other {@link Vector} operand
	 * @return the cross product of this Vector2 and <i>v</i>
	 */
	public double crs(Vector v) {
		return this.x * v.y - this.y * v.x;
	}
	
	/**
	 * @return the magnitude of this {@link Vector}
	 */
	public double mag() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	/**
	 * Normalizes this {@link Vector}.
	 * @return this Vector2 after it has been normalized
	 */
	public Vector nor() {
		double mag = this.mag();
		if(!isZero()) {
			x /= mag;
			y /= mag;
		}
		return this;
	}
	
	/**
	 * Rotates this {@link Vector} by <i>degrees</i>.
	 * @param degrees degrees to rotate by
	 * @return this Vector2 after it has been rotated
	 */
	public Vector rotate(double degrees) {
		return this.setAngle(degrees + this.angle());
	}
	
	/**
	 * Rotates this {@link Vector} by <i>radians</i>.
	 * @param radians radians to rotate by
	 * @return this Vector2 after it has been rotated
	 */
	public Vector rotateRad(double radians) {
		return this.setAngleRad(radians + this.angleRad());
	}
	
	/**
	 * Checks if this {@link Vector} is zero.
	 * @return true if both components equal 0, otherwise false
	 */
	public boolean isZero() {
		return equals(x, 0) && equals(y, 0);
	}
	
	/**
	 * @return a new {@link Vector} with the same x and y components as this Vector2
	 */
	public Vector cpy() {
		return new Vector(this);
	}
	
	/**
	 * Calculates the angle of this {@link Vector} relative to a {@literal <1, 0>} Vector2.
	 * The returned angle will always be within the range of [0, 360] degrees.
	 * Angles increase as they rotate counter-clockwise about (0, 0) starting at {@literal <1, 0>}.
	 * @return this Vector2's angle in degrees
	 */
	public double angle() {
		return Math.toDegrees(this.angleRad());
	}
	
	/**
	 * Calculates the angle between this {@link Vector} and <i>v</i>.
	 * The angle will be within the range of [-180, 180].
	 * <p>{@code v.angle(u) + v.angle() == u.angle()}.
	 * <p>{@code v.angle(u)} could be read as: "<i>u</i> is ____ degrees away from <i>v</i>".
	 * @param v reference Vector2
	 * @return the angle between this Vector2 and <i>v</i> in degrees
	 */
	public double angle(Vector v) {
		return Math.toDegrees(this.angleRad(v));
	}
	
	/**
	 * Calculates the angle of this {@link Vector} relative to a {@literal <1, 0>} Vector2.
	 * The returned angle will always be within the range of [0, 2π] radians.
	 * Angles increase as they rotate counter-clockwise about (0, 0) starting at {@literal <1, 0>}.
	 * @return this Vector2's angle in radians
	 */
	public double angleRad() {
		double angle = Math.atan2(y, x);
		return angle < 0 ? angle + 2 * Math.PI : angle;
	}
	
	/**
	 * Calculates the angle between this {@link Vector} and <i>v</i>.
	 * The angle will be within the range of [-π, π].
	 * <p>{@code v.angleRad(u) + v.angleRad() == u.angleRad()}.
	 * <p>{@code v.angleRad(u)} could be read as: "<i>u</i> is ____ radians away from <i>v</i>".
	 * @param v reference Vector2
	 * @return the angle between this Vector2 and <i>v</i> in radians
	 */
	public double angleRad(Vector v) {
		return Math.atan2(this.crs(v), this.dot(v));
	}
	
	/**
	 * @return the x-component
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @return the y-component
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Sets this {@link Vector}'s x-component.
	 * @param x the x-component
	 * @return this Vector2 after its x-component has been set
	 */
	public Vector setX(double x) {
		this.x = x;
		return this;
	}
	
	/**
	 * Sets this {@link Vector}'s y-component.
	 * @param y the y-component
	 * @return this Vector2 after its y-component has been set
	 */
	public Vector setY(double y) {
		this.y = y;
		return this;
	}
	
	/**
	 * Sets both the x and y components of this {@link Vector}.
	 * @param x the x-component
	 * @param y the y-component
	 * @return this Vector2 after its components have been set
	 */
	public Vector set(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	/**
	 * Sets this {@link Vector}'s angle in degrees.
	 * @param degrees the angle
	 * @return this Vector2 after its angle has been set
	 */
	public Vector setAngle(double degrees) {
		return this.setAngleRad(Math.toRadians(degrees));
	}
	
	/**
	 * Sets this {@link Vector}'s angle in radians.
	 * @param radians the angle
	 * @return this Vector2 after its angle has been set
	 */
	public Vector setAngleRad(double radians) {
		double magnitude = this.mag();
		this.set(Math.cos(radians), Math.sin(radians));
		this.scl(magnitude);
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Vector)) return false;
		Vector v = (Vector)obj;
		return equals(this.x, v.x) && equals(this.y, v.y);
	}
	
	@Override
	public int hashCode() {
		long bits = Double.doubleToLongBits(x);
		bits ^= Double.doubleToLongBits(y);
		return ((int)bits) ^ ((int)(bits >> 32));
	}
	
	@Override
	public String toString() {
		return "<" + x + ", " + y + ">";
	}
}