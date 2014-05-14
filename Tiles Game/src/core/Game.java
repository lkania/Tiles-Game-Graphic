package core;
import java.awt.Point;

import displays.Displayable;

public class Game {

	public static void newGame(State state,Displayable displayable) {

		Point move = null;

		while(!state.isOver()) {

			displayable.display();

			if(state.isHumanTurn()) {

				while(!state.humanMove(move))
					move = displayable.getHumanMove();
			} else {

				state.chooseComputerMove();
			}

			state.toggleTurn();
		}

		displayable.display();
		

	}
}
