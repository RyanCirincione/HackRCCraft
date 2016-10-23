package game;

import java.nio.ByteBuffer;

public abstract class Entity {
	public Hitbox box;
	public float health;
	public int player;
	public int shard;
	public boolean dead;
	
	public Entity() {
		box = null;
	}
	
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
	
	public void serialize(ByteBuffer buffer) {
		box.serialize(buffer);
		buffer.putFloat(health);
		buffer.putInt(player);
		buffer.putInt(shard);
		buffer.put(dead ? (byte)1 : (byte)0);
	}
}
