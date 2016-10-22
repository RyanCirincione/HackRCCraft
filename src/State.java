import java.util.ArrayList;

public class State {
	static int SHARD_WIDTH = 640, SHARD_HEIGHT = 480;

	State(int numPlayers) {
		players = numPlayers;
		shards = new Shard[players];
		for(Shard shard : shards) {
			shard.units = new ArrayList<>(players);
			for(int i = 0; i < shard.units.size(); i++) {
				shard.units.add(new ArrayList<>());
			}
			shard.characters = new Character[players];
			shard.buildings = new Tilemap<>(640, 480);
		}
	}
	
	int players;
	
	class Shard {
		ArrayList<ArrayList<Unit>> units;
		Character[] characters;
		Tilemap<Building> buildings;
	}
	
	Shard[] shards;
	
	void update() {
		
	}
}
