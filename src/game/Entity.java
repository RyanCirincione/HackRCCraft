package game;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	public BufferedImage img = null;
	public Hitbox box;
	public float health;
	/** @player The player that controls the entity. */
	public int player;
	public int shard;
	public boolean dead;

	public Entity(Hitbox box, float health, int player, int shard)
	{
		this.box = box;
		this.health = health;
		this.player = player;
		this.shard = shard;
	}
	
	public Entity() {
		box = null;
	}

	public void takeHit(float amount) {
		if (health < amount)
			dead = true;
		else
			health -= amount;
	}

	public void merge(Entity other) {
		box.merge(other.box);
		health = other.health;
		player = other.player;
		dead = other.dead;
	}
}
