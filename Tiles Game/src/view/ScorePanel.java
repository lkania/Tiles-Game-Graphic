package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import core.State;


public class ScorePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private State state;
	
	public ScorePanel(State state) {
		this.state = state;
	}
	
	public void paint(Graphics gg) {
		Graphics2D g = (Graphics2D)gg;
		
		g.setFont(g.getFont().deriveFont((float)17));
		g.setColor(Color.black);
		g.drawString("Minimax", 40, 15);
		g.drawString("Human:", 0, 40);
		g.drawString("-Points: " + state.getHuman_points(), 15, 60);
		g.drawString("Computer:", 0, 85);
		g.drawString("-Points: " + state.getAi_points(), 15, 105);
	}
	
}
