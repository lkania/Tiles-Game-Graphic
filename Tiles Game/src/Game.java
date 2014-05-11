import java.awt.Point;
import java.io.IOException;


public class Game {

	public static void main(String[] args) {
		FileBoard fb = new FileBoard();
		try {
			fb.readData("src/file.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		//State state = new State(new Board(fb.getBoard()), fb.otherPoints(), fb.turnPoints(), true, false, 3, 0);
		State state = new State(new Board(6,6,3), fb.otherPoints(), fb.turnPoints(), true, true, 3, 5000,false);
		Displayable displayable = new GraphicDisplay(state);

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
		
		System.out.println("Termino el programa");

	}
}
