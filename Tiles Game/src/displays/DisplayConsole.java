package displays;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import core.State;

public class DisplayConsole implements Displayable {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	State state;

	public DisplayConsole(State s) {
		state = s;
	}

	public Point getHumanMove() {
		System.out.println();
		int x=0, y=0;
		try {
			System.out.println("Ingrese x");
			x = Integer.parseInt(br.readLine());
			System.out.println("Ingrese y");
			y = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Point(x, y);
	}

	@Override
	public void display() {
		char[][] board = state.getBoard().getMatrix();

		System.out.println("Human points: " + state.getHuman_points());
		System.out.println("Computer points: " + state.getAi_points());
		System.out.println();

		for (int row = 0; row <= board[0].length+1; row++) {
			for (int column = 0; column <= board.length+1; column++) {
				if(row == 0 && column > 1 && column<12){
					System.out.print((column - 2)+"  ");
				} else if(row == 0 && column >=12){
					System.out.print((column-2) + " ");
				} else if(column == 0 && row > 1 && row<12){
					System.out.print((row-2) + "  ");
				} else if(column == 0 && row >= 12){
					System.out.print((row-2) + " ");
				} else if(row == 1) {
					System.out.print("---");
				} else if(column == 1) {
					System.out.print("| ");
				} else if(column != 0 && row != 0) {
					System.out.print(board[column-2][row-2] + "  ");
				} else
					System.out.print("   ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
