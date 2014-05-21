package view;
import java.awt.BorderLayout;
import java.awt.Color;
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
		
		JPanel buttons = new JPanel();
		buttons.setBackground(Color.blue);
		buttons.setLayout(new BorderLayout());
		buttons.setBounds(10, 140, 80, 40);
		buttons.add(exit, BorderLayout.CENTER);
		
		exit.setLocation(10,200);
		this.add(buttons);
		this.add(p);
	}
	
}
