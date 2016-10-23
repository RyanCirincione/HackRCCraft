package game;

public abstract class Entity {
	public Hitbox box;
	public float health;
	public int player;
	public int shard;
	public boolean dead;
	
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
