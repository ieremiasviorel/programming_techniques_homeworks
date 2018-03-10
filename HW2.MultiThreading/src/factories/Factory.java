package factories;

import java.util.Random;

import model.Client;
import model.Server;

public class Factory {
	
	public static Random random = new Random();

	public static Client generateClient(int id, int min, int max) {
		return new Client(id, min + (int)((max - min) * random.nextDouble()));
	}
	
	public static Server generateServer() {
		return new Server(1 + 0.5 * random.nextFloat());
	}
	
	public static int generateWaitTime(int min, int max) {
		return min + (int)(random.nextDouble() * (max - min));
	}
}