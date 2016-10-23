package game;
import java.util.ArrayList;

public class State {
	static int SHARD_WIDTH = 640, SHARD_HEIGHT = 480;
	int allegiance;
	
	State() {
		players = 2;
		characters = new Character[players];
		for(int i = 0; i < characters.length; i++) 
			characters[i] = new Character();
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
	Character[] characters;
	
	void update() 
	{
		for(int i = 0; i < characters.length; i++)
		{
			double x = characters[i].box.x();
			double y = characters[i].box.y();
			x = x + characters[i].speed.getX();
			y = y + characters[i].speed.getY();
			characters[i].box.setX((float) x);
			characters[i].box.setY((float) y);
		}
		
	}
	
	void merge(State state) {
		//TODO: Merge states
	}
}
