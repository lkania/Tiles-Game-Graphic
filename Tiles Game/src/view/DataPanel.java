package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JPanel;

import core.State;


public class DataPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel buttons;
	
	public DataPanel(State state) {
		this.setLayout(null);
		
		this.setBackground(Color.gray);
		ScorePanel p = new ScorePanel(state);
		p.setBounds(10,10,150,130);
		
		JButton exit = new JButton("exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		
		buttons = new JPanel();
		buttons.setBackground(Color.blue);
		buttons.setLayout(new BorderLayout());
		buttons.setBounds(10, 140, 80, 40);
		buttons.add(exit, BorderLayout.CENTER);
		
		exit.setLocation(10,200);
		this.add(buttons);
		this.add(p);
	}
	
	public void displayWinner(final String results) {
		JPanel resultsPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics gg) {
				Graphics2D g = (Graphics2D)gg;
				
				g.setFont(g.getFont().deriveFont((float)17));
				g.setColor(Color.black);
				
				g.drawString(results, 0, 20);
				
			}
		};
		resultsPanel.setLayout(null);
		resultsPanel.setBounds(10, 180, 170, 30);
		this.add(resultsPanel);
		this.repaint();
	}
	
}
