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
	
	void update() 
	{
		//checks to see if a unit is within a portal Note: portal is represented by a constant value not an entity may change later.
		for(int i = 0; i < players;i++)
		{
			Shard current = shards[i];
			for(int j = 0;j < current.units.get(i).size();j++)
			{
				int tempx = (int)current.units.get(i).get(j).box.x();
				int tempy = (int)current.units.get(i).get(j).box.y();
				if(tempx >= 608)
				{
					if(players == 2)
					{
						//checks to see what aray the unit is in
						//spawns units 1 pixel in front of the portal can be changed by modifing the setx int
						if (i < 1)  
						{
							current.units.get(i+1).add(current.units.get(i).get(j));
							current.units.get(i+1).get(j).box.setX(607);
							
						}
						else
						{	
							current.units.get(i-1).add(current.units.get(i).get(j));
							current.units.get(i+1).get(j).box.setX(607);
						}
						current.units.get(i).remove(current.units.get(i).get(j));
					}
				}
			}
		}
	}
	
	void merge(State state) {
		
	}
}
