
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;


public class GraphicDisplay extends JFrame implements Displayable{
	
	
	private static final long serialVersionUID = 1L;
	private State state;
	Dimension dimension;
	Dimension screenDim;
	int tileSize;
	int dataMenuWidth;
	private Board board;
	private char[][] boardMatrix;
	private volatile Point clicked;
	private volatile Point selected;

	private volatile boolean freeze;
	
	public GraphicDisplay(State state){
		this.state = state;
		board = state.getBoard();
		boardMatrix = board.getMatrix();
		freeze = true;
		dataMenuWidth = 200;
		dimension = new Dimension(boardMatrix.length, boardMatrix[0].length);
		screenDim = this.getToolkit().getScreenSize();
		System.out.println(screenDim);
		tileSize = (int)(Math.min(screenDim.width, screenDim.height)/(1.5*Math.max(dimension.height, dimension.width)));
		selected = new Point(-1,-1);
		this.setSize(dimension.width*tileSize+dataMenuWidth, dimension.height*tileSize);
		this.setResizable(false);
		this.setUndecorated(true);
		
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyVal = e.getKeyCode();
				switch(keyVal) {
				case KeyEvent.VK_ENTER:
					freeze = false;
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(selected.x != -1)
					clicked = selected;
				else
					clicked = null;
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {
				selected.x = -1;
				selected.y = -1;
				repaint();
			}
			
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {}

			@Override
			public void mouseMoved(MouseEvent e) {
				Point aux = e.getLocationOnScreen();
				aux.x = (aux.x)/(tileSize);
				aux.y = (aux.y-tileSize/2)/(tileSize);
				if((aux.x != selected.x || aux.y != selected.y) && aux.x < dimension.width) {
					selected.x = aux.x;
					selected.y = aux.y;
					repaint();
				} if((aux.x>=dimension.width || aux.y>=dimension.height) && selected.x !=-1) {
					selected.x=-1;
					selected.y=-1;
					repaint();
				}
			}
			
		});	
		this.setVisible(true);
	}

	@Override
	public void display() {
		repaint();
		//dataWindow.repaint();
		if(!state.isHumanTurn()) {
			System.out.println("Va a jugar ORS. Presione ENTER para continuar");
			freeze = true;
			while(freeze) {
			}
			freeze = true;
		}
	}
	
	public void paint(Graphics gg) {
		Graphics2D g= (Graphics2D)gg; 
		g.setColor(Color.gray);
		g.fillRect(0, 0, dimension.width*tileSize, dimension.height*tileSize);
		
		for(int i = 0; i<dimension.height; i++) {
			for(int j=0; j<dimension.width; j++) {
				g.setColor(getColor(boardMatrix[j][i]));
				//System.out.println(g.getColor());
				g.fillRect(j*tileSize, i*tileSize, tileSize, tileSize);
			}
		}
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(5));
		for(int i=0; i<dimension.height-1; i++) {
			g.drawLine(0, (i+1)*tileSize, tileSize*dimension.width, (i+1)*tileSize);
		}
		for(int j=0; j<dimension.width-1; j++) {
			g.drawLine((j+1)*tileSize, 0, (j+1)*tileSize, tileSize*dimension.height);
		}
		
		g.drawLine(dimension.width*tileSize, 0, dimension.width*tileSize, dimension.height*tileSize);
		
		g.setColor(Color.orange);
		g.fillRect(dimension.width*tileSize, 0, dataMenuWidth, dimension.height*tileSize);
		
		g.setColor(Color.black);
		g.drawString("Minimax - Graphic display", dimension.width*tileSize + 5, 30);
		g.drawString("Human:", dimension.width*tileSize + 5, 50);
		g.drawString("-points: " + state.getHuman_points(), dimension.width*tileSize + 15, 70);
		g.drawString("Computer:", dimension.width*tileSize + 5, 90);
		g.drawString("-points: " + state.getAi_points(), dimension.width*tileSize + 15, 110);
		
		if(!state.isHumanTurn()) {
			g.drawString("COMPUTER's TURN:", dimension.width*tileSize + 5, 130);
			g.drawString("PRESS ENTER TO", dimension.width*tileSize + 15, 150);
			g.drawString("LET COMPUTER PLAY", dimension.width*tileSize + 15, 165);
		} else {
			g.drawString("YOUR TURN:", dimension.width*tileSize + 5, 130);
			g.drawString("CLICK ON A TILE", dimension.width*tileSize + 15, 150);
			g.drawString("WITH NEIGHBOURS", dimension.width*tileSize + 15, 165);
		}
		if(selected.x != -1 && selected.x < dimension.width) {
			g.setColor(Color.yellow);
			g.drawRect(selected.x*tileSize, selected.y*tileSize, tileSize, tileSize);
		}
		g.setColor(Color.black);
	}
	
	public Color getColor(char color) {
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

	@Override
	public Point getHumanMove() {
		clicked = null;
		while(clicked == null) {
		}
		Point move = clicked;
		clicked = null;
		System.out.println(move);
		return new Point(move.y, move.x);
	}
}

