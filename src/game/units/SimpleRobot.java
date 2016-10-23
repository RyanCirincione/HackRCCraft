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
	}

}
