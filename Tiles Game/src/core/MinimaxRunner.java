package core;

import view.GDisplay;

public class MinimaxRunner implements Runnable{
	
	private Thread t;
	private State state;
	private GDisplay display;
	
	public MinimaxRunner(State state, GDisplay display) {
		this.state = state;
		this.display = display;
	}
	
	public void start() {
		t = new Thread(this, "Minimax runner");
		t.start();
	}

	@Override
	public void run() {
		try {
			Thread.currentThread();
			Thread.sleep(500);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		if (!state.isHumanTurn()) {
			state.chooseComputerMove();
			display.display();
			
			if(state.isOver()) {
				display.showResults(state.winner());
			} else {
				state.toggleTurn();
			}
		}
	}

}
