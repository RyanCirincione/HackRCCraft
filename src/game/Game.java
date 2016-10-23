package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel
{
	Networking.NetworkedInstance network;
	
	private static final long serialVersionUID = -8395759457708163217L;
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("HackRCCraft");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Game panel = new Game();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				panel.tick();
				panel.repaint();
			}
		}, 0, 1000/60);
	}
	
	static int G_WIDTH = 800, G_HEIGHT = 600;
	
	public Game()
	{
		boolean host = JOptionPane.showConfirmDialog(null, "Play as host?") == JOptionPane.YES_OPTION;
		if(host) {
			try {
				network = new Networking.GameServer();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				String ip = JOptionPane.showInputDialog("Enter the IP address of the host.");
				network = new Networking.GameClient(ip);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		this.setPreferredSize(new Dimension(G_WIDTH, G_HEIGHT));
		
		this.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent e)
			{
				
			}
			
			public void mouseDragged(MouseEvent e)
			{
				//MOUSEMOVED WONT BE CALLED IF A BUTTON IS DOWN DURING MOVEMENT
			}
		});
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				
			}
			
			public void mouseReleased(MouseEvent e)
			{
				
			}
		});
		State state = network.state;
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_W)
				{
					state.characters[state.allegiance].speed.setY(-1);
				}
				if(e.getKeyCode() == KeyEvent.VK_S)
				{
					state.characters[state.allegiance].speed.setY(1);
				}
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
					state.characters[state.allegiance].speed.setX(-1);
				}
				if(e.getKeyCode() == KeyEvent.VK_D)
				{
					state.characters[state.allegiance].speed.setX(1);
				}
			}
			
			public void keyReleased(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_W)
				{
					state.characters[state.allegiance].speed.setY(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_S)
				{
					state.characters[state.allegiance].speed.setY(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
					state.characters[state.allegiance].speed.setX(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_D)
				{
					state.characters[state.allegiance].speed.setX(0);
				}
			}
		});
		
		this.setFocusable(true);
		this.requestFocus();
	}
	
	public void tick()
	{
		network.update();
	}
	
	public void paintComponent(Graphics gr)
	{
		Graphics2D g = (Graphics2D) gr;
		State.Shard shard = network.state.shards[network.state.allegiance];
		
		g.setColor(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
		
		for(int i = 0; i < shard.units.size(); i++)
			for(int j = 0; j < shard.units.get(i).size(); j++)
			{
				Unit unit = shard.units.get(i).get(j);
				if(shard.units.get(i).get(j).box instanceof Circle)
					g.fillOval((int)(unit.box.x() - ((Circle)unit.box).radius),
							(int)(unit.box.y() - ((Circle)unit.box).radius),
							 (int)(((Circle)unit.box).radius*2), (int)(((Circle)unit.box).radius*2));
				else
					g.fillRect((int)unit.box.x(), (int)unit.box.y(),
							(int)((Rectangle)unit.box).dim.getX(), (int)((Rectangle)unit.box).dim.getY());
			}

		g.setColor(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
		
		for(int x = 0; x < shard.buildings.data.size(); x++)
			for(int y = 0; y < shard.buildings.data.get(x).size(); y++)
				if(shard.buildings.get(x, y) != null)
				{
					g.fillRect(x*Tilemap.TILE_SIZE, y*Tilemap.TILE_SIZE,
							Tilemap.TILE_SIZE, Tilemap.TILE_SIZE);
				}
	}
}
