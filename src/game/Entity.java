package game;

public abstract class Entity {
	public Hitbox box;
	public float health;
	public int player;
	public boolean dead;
	
	void takeHit(float amount) {
		if(health < amount) dead = true;
		else health -= amount;
	}
}
