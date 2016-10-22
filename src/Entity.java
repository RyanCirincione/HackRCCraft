
public abstract class Entity {
	Hitbox box;
	float health;
	int player;
	boolean dead;
	
	void takeHit(float amount) {
		if(health < amount) dead = true;
		else health -= amount;
	}
}
