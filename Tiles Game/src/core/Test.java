package core;

import input_data.FileBoard;

import java.io.Console;
import java.io.IOException;

import displays.DisplayConsole;
import displays.Displayable;

public class Test {
	
	public static void main(String[] args) {
		
		int human_points,computer_points;
		Double timeOrDepth;
		boolean time=false,prune=false,tree=false,console=false;
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
		{
			System.err.println("args: -file not found");
			return;
		}
				
		Board board = new Board(matrix);
		board.gravity();
		
		if("-maxtime".equals(args[2]) || "-depth".equals(args[2]))
		{
			timeOrDepth = Double.parseDouble(args[3]);
			if(timeOrDepth==null)
			{
				System.err.println("args: time/depth number not specified");
				return;
			}			
			if("-maxtime".equals(args[2]))
			{
				time=true;
				timeOrDepth*=1000;
			}
		}
		else
		{
			System.err.println("args: maxtime/depth not specified");
			return;
		}
				
		
		if("-console".equals(args[4]))
		{
			console=true;
		}
		else if("-visual".equals(args[4]))
		{
			console=false;
		}
		else
		{
			System.out.println("ERROR: Display not especified");
			return;
		}
		
		
		
		if(args.length > 5 && ("-prune".equals(args[5]) || "-tree".equals(args[5])))
		{
			if("-prune".equals(args[5])){
				prune = true;
			}else{
				if(console){
					tree=true;
				}else{
					System.err.println("args: tree only available with console");
					return;
				}
			}
		}
		else
		{	if(args.length > 5){
				System.err.println("args: prune/tree not specified");
				return;
			}
		}
		
		if(args.length==7 && tree==false)
		{
			if( "-tree".equals(args[6]))
				tree=true;
			else
			{
				
					System.err.println("args: 7th argument not valid");
					return;
				
			}
		}
		
		State state = new State(board,human_points,computer_points,prune,time,(int)timeOrDepth.doubleValue(),timeOrDepth,tree);
		Displayable display;
				
		display = new DisplayConsole(state);
		display.display();
		state.chooseComputerMove();
		display.display();
		//String input = System.console().readLine();
		state.chooseComputerMove();
		display.display();

		
		
	}
	

}
