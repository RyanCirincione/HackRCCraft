package game.units;

import game.State;
import game.Unit;

public class SimpleRobot extends Unit {

	@Override
	public void update(State state) {
		if (this.player == state.allegiance) {
			box.setX(1);
		} else {
			box.setX(-1);
		}
		if (box.x() <= 0) {
			dead = true;
		}
		State.Shard thisShard = state.shards[this.shard];
		for(int i = 0; i < thisShard.units.size(); i++)
		{
			if(thisShard.units.get(state.allegiance).get(i).box.x() == box.x() - 1 || 
					thisShard.units.get(state.allegiance).get(i).box.x() == box.x() + 1)
			{
				thisShard.units.get(state.allegiance).get(i).health -= 1;
			}
			if(thisShard.units.get(state.allegiance).get(i).box.y() == box.y() - 1 || 
					thisShard.units.get(state.allegiance).get(i).box.y() == box.y() + 1)
			{
				thisShard.units.get(state.allegiance).get(i).health -= 1;
			}
		}
	}

}
