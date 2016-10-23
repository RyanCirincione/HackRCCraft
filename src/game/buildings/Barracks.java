package game.buildings;

import game.Building;
import game.Circle;
import game.Hitbox;
import game.Rectangle;
import game.State;
import game.Unit;
import game.units.SimpleRobot;

public class Barracks extends Building
{
	public int maxCooldown;
	public int cooldown;
	//if cooldown is zero, add a robot and reset to 60
	//if not, reduce cooldown by 1
	public Barracks(Rectangle box, float health, int player, int shard, int maxCooldown)
	{
		this.box = box;
		this.health = health;
		this.player = player;
		this.shard = shard;
		this.maxCooldown = maxCooldown;
		cooldown = 0;
	}
	
	public void update(State state)
	{
		if(cooldown == 0)
		{
			State.Shard newShard = state.shards[this.shard];
			Unit robot = new SimpleRobot();
			System.out.println("Barracks: Spawned new robot");
			Circle circ = new Circle();
			circ.setRadius(32);
			robot.box = circ;
			robot.box.setX(box.x());
			robot.box.setY(box.y());
			newShard.units.get(this.player).add(robot);
			cooldown = maxCooldown;
		}
		else
		{
			cooldown--;
		}
	}
}
