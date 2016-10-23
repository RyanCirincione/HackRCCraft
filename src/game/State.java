package game;
import java.util.ArrayList;

public class State {
	static int SHARD_WIDTH = 640, SHARD_HEIGHT = 480;
	public int allegiance;
	
	State() {
		players = 2;
		characters = new Character[players];
		for(int i = 0; i < characters.length; i++) 
			characters[i] = new Character();
		shards = new Shard[players];
		for(int i = 0; i < shards.length; i++) {
			Shard shard = shards[i] = new Shard();
			shard.units = new ArrayList<>(players);
			for(int j = 0; j < players; j++) {
				shard.units.add(new ArrayList<>());
			}
			shard.characters = new Character[players];
			shard.buildings = new Tilemap<>();
		}
	}
	
	int players;
	
	public static class Shard {
		public ArrayList<ArrayList<Unit>> units;
		public Character[] characters;
		public Tilemap<Building> buildings;
	}
	
	public Shard[] shards;
	public Character[] characters;
	
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
		for(int i = 0; i < characters.length; i++)
		{
			double x = characters[i].box.x();
			double y = characters[i].box.y();
			x = x + characters[i].speed.getX();
			y = y + characters[i].speed.getY();
			characters[i].box.setX((float) x);
			characters[i].box.setY((float) y);
		}
		for(int i = 0; i < shards.length; i++)
		{
			for(int j = 0; j < shards[i].units.size(); j++)
			{
				for(int q = 0; q < shards[i].units.get(j).size(); q++)
				{
					shards[i].units.get(j).get(q).update(this);
				}
			}
		}
	}
	void merge(State state) {
		//TODO: Merge states
	}
}
