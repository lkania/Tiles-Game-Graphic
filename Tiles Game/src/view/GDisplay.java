package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.Board;
import core.Game;
import core.State;
import displays.Displayable;

public class GDisplay extends JFrame implements Displayable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DataPanel dataPanel;
	private BoardPanel boardPanel;

	//private State state;

	private int tileSize;
	private int dataPanelWidth = 180;
	private Dimension boardDimension;
	private Dimension screenDimension;

	// private volatile boolean decided;
	//private volatile Point move;

	public GDisplay(State state, Game game) {
		super("Tiles - Estructura de Datos y Algoritmos");
		//this.state = state;
		// this.decided = false;

		// El layout del frame es para que el metodo pack() funcione
		this.setLayout(new BorderLayout());

		// tileSize se define en funcion de la resolucion de la pantalla y de
		// las dimensiones del tablero
		boardDimension = new Dimension(state.getBoard().getMatrix().length,
				state.getBoard().getMatrix()[0].length);
		screenDimension = this.getToolkit().getScreenSize();
		tileSize = (int) (Math.min(screenDimension.width,
				screenDimension.height) / (1.2 * Math.max(
				boardDimension.height, boardDimension.width)));

		// el panel background esta para solucionar el problema de que el
		// setsize del frame tambien
		// considera el tamanio del title bar. que me come parte del frame al
		// imprimir el tablero
		JPanel background = new JPanel();
		background.setLayout(null);
		background.setPreferredSize(new Dimension(boardDimension.width
				* tileSize + dataPanelWidth, boardDimension.height * tileSize));

		this.setResizable(false);

		boardPanel = new BoardPanel(state, tileSize, this, game);
		boardPanel.setBounds(0, 0, boardDimension.width * tileSize,
				boardDimension.height * tileSize);
		dataPanel = new DataPanel(state);
		dataPanel.setBounds(boardDimension.width * tileSize, 0, dataPanelWidth,
				boardDimension.height * tileSize);

		background.add(dataPanel);
		background.add(boardPanel);

		// El pack se encarga de hace encajar el panel background en el exacto
		// espacio que usa.
		this.add(background, BorderLayout.CENTER);
		pack();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		//game.computerMove();
	}
	
	public static void main(String[] args) {
		State s = new State(new Board(10,15,4), 0, 0, true, false, 4, 200, false);
		@SuppressWarnings("unused")
		Game game = new Game(s);
	}

	@Override
	public void display() {
		repaint();
	}
}
