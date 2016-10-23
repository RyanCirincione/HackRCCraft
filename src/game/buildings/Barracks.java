package game.buildings;

import game.Building;
import game.State;
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
			newShard.units.get(this.player).add(new SimpleRobot());
			cooldown = 60;
		}
		else
		{
			cooldown--;
		}
	}
}
