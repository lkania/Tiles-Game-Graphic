package view;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import core.Board;
import core.Game;
import core.State;


public class TilePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Game game;
	private int tileSize;
	private Point position;
	private Board board;
	private volatile boolean selected;
	
	public TilePanel(State state, Point position, int tileSize, GDisplay gd, Game game) {
		this.game = game;
		this.tileSize = tileSize;
		this.board = state.getBoard();
		this.position = position;
		
		this.addMouseListener(new MyMouseAdapter(gd));
	}
	
	public void paint(Graphics gg) {
		Graphics2D g = (Graphics2D)gg;
		
		g.setColor(getColor(board.getMatrix()[position.y][position.x]));
		g.fillRect(1, 1, tileSize-2, tileSize-2);
		
		if(selected) {
			g.setColor(Color.black);
			g.setStroke(new BasicStroke(tileSize/48));
			g.drawRect(3, 3, tileSize-6, tileSize-6);
			//g.drawRect(4, 4, tileSize-8, tileSize-8);
			g.drawRect(5, 5, tileSize-10, tileSize-10);
			//g.drawRect(6, 6, tileSize-12, tileSize-12);
			g.drawRect(7, 7, tileSize-14, tileSize-14);
		}
	}
	
	private Color getColor(char color) {
		switch(color) {
		case '1':
			return Color.red;
		case '2':
			return Color.blue;
		case '3':
			return Color.green;
		case '4' :
			return Color.yellow;
		case '5':
			return Color.magenta;
		case '6':
			return Color.cyan;
		default:
			return Color.white;
				
		}
	}
	
	private class MyMouseAdapter implements MouseListener {
		
		private GDisplay gd;
		
		Cursor normalCursor, blankCursor;
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		
		public MyMouseAdapter(GDisplay gd) {
			this.gd = gd;
			normalCursor = gd.getContentPane().getCursor();
			blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				    cursorImg, new Point(0, 0), "blank cursor");
		}

		@Override
		public void mouseClicked(MouseEvent me) {
			game.humanMove(position);
		}

		@Override
		public void mouseEntered(MouseEvent me) {
			selected = true;
			gd.getContentPane().setCursor(blankCursor);
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			selected = false;
			gd.getContentPane().setCursor(normalCursor);
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
