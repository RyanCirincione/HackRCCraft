package game.buildings;
import game.Building;
import game.Hitbox;
import game.State;
import game.Unit;
/**Basic tower that attacks nearest thing to it.*/
public class BasicTower extends Building{
	/** @range Maximum distance the building can shoot in pixels.*/
	private int range;
	/** @damage Amount of hit points in damage the tower does.*/ 
	private int damage;
	/** @delay Number of ticks before tower can attack again.*/
	private int maxDelay;
	private int currentDelay = 0;
	
	public BasicTower(Hitbox box, float health, int player, int shard, int range, int damage, int maxDelay)
	{
		this.box = box;
		this.health = health;
		this.player = player;
		this.shard = shard;
		this.range = range;
		this.damage = damage;
		this.maxDelay = maxDelay;
	}
	
	public void update(State state)
	{
		/** @closest Unit that is closest to the tower.*/
		Unit closest = null;
		/** @distance Square of distance in pixels between tower and the closest unit.*/
		double distance = Integer.MAX_VALUE;
		if(currentDelay != 0)
		{
			currentDelay--;
		} else
		{
			for(int x = 0; x < state.shards[shard].units.size(); x++)
			{
				for(int y = 0; y < state.shards[shard].units.get(x).size(); y++)
				{
					if(state.shards[shard].units.get(x).get(y).player == player)
					{
						break;
					} else
					{
						if(closest == null)
						{
							closest = state.shards[shard].units.get(x).get(y);
							distance = Math.pow(state.shards[shard].units.get(x).get(y).box.centerX(), 2) +
									 Math.pow(state.shards[shard].units.get(x).get(y).box.centerY(), 2);
						} else if(Math.pow(state.shards[shard].units.get(x).get(y).box.centerX(), 2) +
								Math.pow(state.shards[shard].units.get(x).get(y).box.centerY(), 2) < distance)
						{
							closest = state.shards[shard].units.get(x).get(y);
							distance = Math.pow(state.shards[shard].units.get(x).get(y).box.centerX(), 2) +
									Math.pow(state.shards[shard].units.get(x).get(y).box.centerY(), 2);
						}
					}
				}
			}
			if(Math.pow(range, 2) > distance && currentDelay == 0)
			{
				currentDelay = maxDelay;
				System.out.println("Before " + closest.health);
				closest.takeHit(damage);
				System.out.println("After " + closest.health);
			}
		}
	}
}