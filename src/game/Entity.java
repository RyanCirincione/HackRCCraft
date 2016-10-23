package game;

public abstract class Entity {
	Hitbox box;
	float health;
	int player;
	boolean dead;
	
	void takeHit(float amount) {
		if(health < amount) dead = true;
		else health -= amount;
	}
	
	public void merge(Entity other) {
		box.merge(other.box);
		health = other.health;
		player = other.player;
		dead = other.dead;
	}
}
