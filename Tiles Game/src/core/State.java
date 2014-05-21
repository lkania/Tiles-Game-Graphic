package core;
import java.awt.Point;

import minimax.MiniMax;


public class State {

	private Board board;
	private int human_points;
	private int ai_points;
	private MiniMax minimax;
	private volatile boolean humanTurn;
	
	public State(Board board, int human_points, int ai_points,boolean pruneAlphaBeta,boolean timeLimit, int maxLevel, double timeMax, boolean makeTree) {
		super();
		this.board = board;
		this.human_points = human_points;
		this.ai_points = ai_points;
		this.minimax = new MiniMax(pruneAlphaBeta,timeLimit,maxLevel,timeMax,makeTree);
		this.humanTurn = true;
	}

	public void chooseComputerMove() {
		Point move = minimax.solution(board,ai_points,human_points);
		
		if(move==null)
			return;
		
		ai_points+=board.delete(move.x,move.y);

	}
	
	public boolean isHumanTurn()
	{
		return humanTurn;
	}
	
	public void toggleTurn()
	{
		humanTurn=!humanTurn;
	}

	public boolean humanMove(Point move)
	{
		if(move==null)
			return false;
		
		int points =board.delete(move.x,move.y);
		
		human_points+=points;
		
		return points!=0;
	}

	public boolean isOver()
	{	
		if(board.endGame())
		{
			if(!board.isEmpty())
				return true;
			
			if(humanTurn)
				human_points*=1.3;
			else
				ai_points*=1.3;
			return true;
		}
		
		return false;
		
	}
	
	public int getHuman_points() {
		return human_points;
	}

	public int getAi_points() {
		return ai_points;
	}

	public Board getBoard() {
		return board;
	}
	
	
}
