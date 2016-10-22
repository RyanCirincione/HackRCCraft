import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

public class Networking {
	static class NetworkedInstance extends Listener {
		EndPoint point; 
		State state;
		Connection other;
		
		NetworkedInstance() {
			state = new State(2);
		}
		
		public void start() {
			point.getKryo().register(State.class);
			point.addListener(this);
		}
		
		public void received (Connection connection, Object object) {
			if(other == null) {
				ObjectSpace.registerClasses(point.getKryo());
				ObjectSpace objectSpace = new ObjectSpace();
				objectSpace.register(0, state);
				objectSpace.addConnection(connection);
				System.out.println("Connection established: " + connection);
			}
			other = connection;
	   }
		
		void update() {
			if(other == null) return;
			state.update();
			State otherState = ObjectSpace.getRemoteObject(other, 0, State.class);
			otherState.merge(state);
		}
	}
	
	static class GameServer extends NetworkedInstance {
		
		GameServer() throws IOException {
			super();
			Server server = new Server();
			server.start();
			server.bind(8888, 8889);
			point = server;
			start();
		}
	}
	static class GameClient extends NetworkedInstance {
		
		GameClient(String ip) throws IOException {
			super();
			Client client = new Client();
			client.start();
			client.connect(5000, ip, 8888, 8889);
			point = client;
			start();
		}
		
		
	}
}
