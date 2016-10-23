package game.units;

import game.State;
import game.Unit;

public class SimpleRobot extends Unit {

	@Override
	public void update(State state) {
		if (this.player == state.allegiance) {
			box.setX(box.x() + 1);
		} else {
			box.setX(box.y() -1);
		}
		if (box.x() <= 0) {
			dead = true;
		}
		State.Shard thisShard = state.shards[this.shard];
//		for (int i = 0; i < thisShard.units.size(); i++) {
//			if (thisShard.units.get(state.allegiance).get(i).box.x() == box.x() - 1
//					|| thisShard.units.get(state.allegiance).get(i).box.x() == box.x() + 1) {
//				thisShard.units.get(state.allegiance).get(i).health -= 1;
//			}
//			if (thisShard.units.get(state.allegiance).get(i).box.y() == box.y() - 1
//					|| thisShard.units.get(state.allegiance).get(i).box.y() == box.y() + 1) {
//				thisShard.units.get(state.allegiance).get(i).health -= 1;
//			}
//		}
//		if ((box.x() + 1 > 0 && box.x() - 1 > 0 && box.y() + 1 > 0 && box.y() - 1 > 0)) {
//			if (thisShard.buildings.get((int) box.x() + 1, (int) box.y()) != null) {
//				thisShard.buildings.get((int) box.x() + 1, (int) box.y()).health--;
//			}
//			if (thisShard.buildings.get((int) box.x() - 1, (int) box.y()) != null) {
//				thisShard.buildings.get((int) box.x() - 1, (int) box.y()).health--;
//			}
//			if (thisShard.buildings.get((int) box.x(), (int) box.y() + 1) != null) {
//				thisShard.buildings.get((int) box.x(), (int) box.y() + 1).health--;
//			}
//			if (thisShard.buildings.get((int) box.x() - 1, (int) box.y() - 1) != null) {
//				thisShard.buildings.get((int) box.x(), (int) box.y() - 1).health--;
//			}
//		}
	}

}
