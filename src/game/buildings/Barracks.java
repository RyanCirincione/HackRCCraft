package game.buildings;

import game.Building;
import game.Circle;
import game.State;
import game.Unit;
import game.units.SimpleRobot;

public class Barracks extends Building
{
	public int cooldown;
	//if cooldown is zero, add a robot and reset to 60
	//if not, reduce cooldown by 1
	public void update(State state)
	{
		if(cooldown == 0)
		{
			State.Shard newShard = state.shards[this.shard];
			Unit robot = new SimpleRobot();
			Circle circ = new Circle();
			circ.setRadius(32);
			robot.box = circ;
			robot.box.setX(box.x());
			robot.box.setY(box.y());
			newShard.units.get(this.player).add(robot);
			cooldown = 60;
		}
		else
		{
			cooldown--;
		}
	}
}
