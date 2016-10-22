import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
	
	public Game()
	{
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
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				
			}
			
			public void keyReleased(KeyEvent e)
			{
				
			}
		});
		
		this.setFocusable(true);
		this.requestFocus();
	}
	
	public void tick()
	{
		
	}
	
	public void paintComponent(Graphics g)
	{
		
	}
}
