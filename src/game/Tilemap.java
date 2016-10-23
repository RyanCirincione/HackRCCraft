package game;
import java.util.ArrayList;

public class Tilemap <T extends Entity> {
	static int TILE_SIZE = 32;
	ArrayList<ArrayList<T>> data;
	
	Tilemap() {
		int width = 640;
		int height = 480;
		data = new ArrayList<>(width / TILE_SIZE);
		for(int i = 0; i < width / TILE_SIZE; i++) {
			data.add(new ArrayList<>(height / TILE_SIZE));
			for(int j = 0; j < height / TILE_SIZE; j++) {
				data.get(i).add(null);
			}
		}
	}
	
	T get(int x, int y) {
		x /= TILE_SIZE;
		y /= TILE_SIZE;
		return data.get(x).get(y);
	}
	
	void set(T object, int x, int y) {
		x /= TILE_SIZE;
		y /= TILE_SIZE;
		data.get(x).set(y, object);
	}
	
	public void merge(Tilemap<T> other) {
		for(int i = 0; i < data.size(); i++) {
			for(int j = 0; j < data.get(j).size(); j++) {
				data.get(i).get(j).merge(other.data.get(i).get(j));
			}
		}
	}
}
