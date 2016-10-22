import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class Networking {
	static abstract class NetworkedInstance extends Listener {
		EndPoint point; 
		State state;
		Connection other;
		Runnable send;
		
		NetworkedInstance() {
			state = new State(2);
		}
		
		public void start() {
			point.getKryo().register(State.class);
			point.addListener(this);
		}
		
		@Override
		public void connected (Connection connection) {
			other = connection;
			System.out.println("Connection established: " + connection);
		}
		
		@Override
		public void received(Connection connection, Object data) {
			state.merge((State)data);
		}
		
		void update() {
			state.update();
			send.run();
		}
	}
	
	static class GameServer extends NetworkedInstance {
		
		GameServer() throws IOException {
			super();
			Server server = new Server();
			server.start();
			server.bind(8888, 8889);
			point = server;
			send = () -> server.sendToAllTCP(state);
		}
	}
	static class GameClient extends NetworkedInstance {
		
		GameClient(String ip) throws IOException {
			super();
			Client client = new Client();
			client.start();
			client.connect(5000, ip, 8888, 8889);
			point = client;
			send = () -> client.sendTCP(state);
		}
		
		
	}
}
