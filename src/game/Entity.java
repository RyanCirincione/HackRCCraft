package game;

import java.io.Serializable;

public abstract class Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	public Hitbox box;
	public float health;
	/**@player The player that controls the entity. */
	public int player;
	public int shard;
	public boolean dead;
	
	public Entity() {
		box = null;
	}
	
	public void takeHit(float amount) {
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
