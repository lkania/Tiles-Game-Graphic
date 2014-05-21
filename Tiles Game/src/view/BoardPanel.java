package view;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;
import core.Game;
import core.State;


public class BoardPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TilePanel[][] tiles;
	
	//width es cantidad de columnas, height es cantidad de filas
	private Dimension boardDimension;
	
	public BoardPanel(State state, int tileSize, GDisplay gd, Game game) {
		boardDimension = new Dimension(state.getBoard().getMatrix().length, state.getBoard().getMatrix()[0].length);
		
		this.setLayout(null);
		
		tiles = new TilePanel[boardDimension.height][boardDimension.width];
		for(int i=0; i<boardDimension.height; i++) {
			for(int j=0; j<boardDimension.width; j++) {
				tiles[i][j] = new TilePanel(state, new Point(i,j), tileSize, gd, game);
				tiles[i][j].setBounds(j*tileSize, i*tileSize, tileSize, tileSize);
				this.add(tiles[i][j]);
			}
		}
	}
}
