package game;
import java.io.IOException;

import org.nustaq.serialization.FSTConfiguration;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class Networking  {
	static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
	
	static abstract class NetworkedInstance extends Listener {
		EndPoint point; 
		State state;
		Connection other;
		Runnable send;
		
		NetworkedInstance() {
			state = new State();
		}
		
		public void start() {
			point.getKryo().register(byte[].class);
			point.addListener(this);
		}
		
		@Override
		public void connected (Connection connection) {
			other = connection;
			System.out.println("Connection established: " + connection);
		}
		
		@Override
		public void received(Connection connection, Object data) {
			State networked = (State)conf.asObject((byte[])data);
			state.merge(networked);
		}
		
		void update() {
			state.update();
			if(other != null)
				send.run();
		}
	}
	
	static class GameServer extends NetworkedInstance {
		
		GameServer() throws IOException {
			super();
			state.allegiance = 0;
			Server server = new Server();
			server.start();
			server.bind(8888, 8889);
			point = server;
			start();
			send = () -> server.sendToAllTCP(conf.asByteArray(state));
		}
	}
	static class GameClient extends NetworkedInstance {
		
		GameClient(String ip) throws IOException {
			super();
			state.allegiance = 1;
			Client client = new Client();
			client.start();
			client.connect(5000, ip, 8888, 8889);
			point = client;
			start();
			send = () -> client.sendTCP(conf.asByteArray(state));
		}
		
		
	}
}
