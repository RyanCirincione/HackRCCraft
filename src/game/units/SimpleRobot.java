package game.units;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.State;
import game.Unit;

public class SimpleRobot extends Unit {
	
	public SimpleRobot(){
		try
		{
			img = ImageIO.read(new File("res/Mob.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void update(State state) {
		if (this.player == state.allegiance) {
			box.setX(box.x() + 2);
		} else {
			box.setX(box.y() - 2);
		}
		if (box.x() <= 0) {
			dead = true;
		}
		State.Shard thisShard = state.shards[this.shard];
		if ((box.x() + 1 > 0 && box.x() - 1 > 0 && box.y() + 1 > 0 && box.y() - 1 > 0)) {
			for (int i = 0; i < thisShard.units.size(); i++) {
				if (thisShard.units.get(state.allegiance).get(i).box.x() == box.x() - 1
						|| thisShard.units.get(state.allegiance).get(i).box.x() == box.x() + 1) {
					thisShard.units.get(state.allegiance).get(i).takeHit(1);
				}
				if (thisShard.units.get(state.allegiance).get(i).box.y() == box.y() - 1
						|| thisShard.units.get(state.allegiance).get(i).box.y() == box.y() + 1) {
					thisShard.units.get(state.allegiance).get(i).takeHit(1);
				}
			}
			if (thisShard.buildings.get((int) box.x() + 1, (int) box.y()) != null) {
				thisShard.buildings.get((int) box.x() + 1, (int) box.y()).takeHit(1);
			}
			if (thisShard.buildings.get((int) box.x() - 1, (int) box.y()) != null) {
				thisShard.buildings.get((int) box.x() - 1, (int) box.y()).takeHit(1);
			}
			if (thisShard.buildings.get((int) box.x(), (int) box.y() + 1) != null) {
				thisShard.buildings.get((int) box.x(), (int) box.y() + 1).takeHit(1);
			}
			if (thisShard.buildings.get((int) box.x() - 1, (int) box.y() - 1) != null) {
				thisShard.buildings.get((int) box.x(), (int) box.y() - 1).takeHit(1);
			}
		}
	}

}
