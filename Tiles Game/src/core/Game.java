package core;
import java.awt.Point;

import view.GDisplay;

public class Game {

	private State state;
	private GDisplay display;
	private MinimaxRunner minimaxRunner;
	
	public Game(State state) {
		this.state = state;
		this.display = new GDisplay(state, this);
		this.minimaxRunner = new MinimaxRunner(state, display);
	}
	
	public synchronized void computerMove() {
		minimaxRunner.start();
	}
	
	public synchronized void humanMove(Point move) {
		if (state.isHumanTurn() && state.humanMove(move)) {
			display.display();
			
			if(state.isOver()) {
				
			} else {
				computerMove();
				state.toggleTurn();
			}
		}
	}
	
	
}
