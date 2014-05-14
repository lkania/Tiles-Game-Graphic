package input_data;
import java.io.IOException;

import core.Board;
import core.Game;
import core.State;
import displays.DisplayConsole;
import displays.Displayable;
import displays.GraphicDisplay;


public class Main {

	public static void main(String[] args) {
		
		int human_points,computer_points;
		Integer timeOrDepth;
		boolean time=false,prune=false,tree=false;
		char[][] matrix;
		
		if(args.length < 4)
			return;
		
		if("-file".equals(args[0]))
		{
			FileBoard fb = new FileBoard();
			try {
				fb.readData(args[1]);
				matrix = fb.getBoard();
				human_points = fb.otherPoints();
				computer_points = fb.turnPoints();
			} catch (IOException e) {
				
				return;
			}
		}
		else
			return;
				
		Board board = new Board(matrix);
		
		if("-maxtime".equals(args[2]) || "-depth".equals(args[2]))
		{
			timeOrDepth = Integer.parseInt(args[3]);
			if(timeOrDepth==null)
			{
				System.out.println("ERROR: wrong integer");
				return;
			}			
			if("-maxtime".equals(args[2]))
			{
				time=true;
				timeOrDepth*=1000;
			}
		}
		else
			return;
				
		if(args.length > 5 && ("-prune".equals(args[5]) || "-tree".equals(args[5])))
		{
			if("-prune".equals(args[5]))
				prune = true;
			else
				tree=true;
		}
		else
			return;
		
		if(args.length==7 && tree==false)
			if( "-tree".equals(args[6]))
				tree=true;
			else
				return;
		
		State state = new State(board,human_points,computer_points,prune,time,timeOrDepth,timeOrDepth,tree);
		Displayable display;
				
		if("-console".equals(args[4]))
		{
			display = new DisplayConsole(state);
			display.display();
			state.chooseComputerMove();
			display.display();
			return;
		}
		else if("-visual".equals(args[4]))
			display = new GraphicDisplay(state);
		else
		{
			System.out.println("ERROR: Display not especified");
			return;
		}
		
		Game.newGame(state,display);
		
		return;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
