package game;
import java.util.ArrayList;

public class Tilemap <T> {
	static int TILE_SIZE = 32;
	ArrayList<ArrayList<T>> data;
	
	Tilemap(int width, int height) {
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
}
