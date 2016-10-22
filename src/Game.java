import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel
{
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
	
	public void tick()
	{
		
	}
	
	public void paintComponent(Graphics g)
	{
		
	}
}
