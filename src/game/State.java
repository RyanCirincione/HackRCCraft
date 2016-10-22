package game;
import java.util.ArrayList;

public class State {
	static int SHARD_WIDTH = 640, SHARD_HEIGHT = 480;

	State() {
		players = 2;
		shards = new Shard[players];
		for(int i = 0; i < shards.length; i++) {
			Shard shard = shards[i] = new Shard();
			shard.units = new ArrayList<>(players);
			for(int j = 0; j < shard.units.size(); j++) {
				shard.units.add(new ArrayList<>());
			}
			shard.characters = new Character[players];
			shard.buildings = new Tilemap<>();
		}
	}
	
	int players;
	
	static class Shard {
		ArrayList<ArrayList<Unit>> units;
		Character[] characters;
		Tilemap<Building> buildings;
	}
	
	Shard[] shards;
	
	void update() {
		
	}
	
	void merge(State state) {
		
	}
}
