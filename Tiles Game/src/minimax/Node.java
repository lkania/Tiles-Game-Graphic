package minimax;
import java.awt.Point;
import java.util.List;

import core.Board;


public abstract class Node {

	
	protected Board board;
	protected int alpha=Integer.MIN_VALUE;
	protected int beta=Integer.MAX_VALUE;
	protected int min_points;
	protected int max_points;
	protected Point position; 
	protected List<Node> childs=null;
	protected Point bestPlay;
	protected boolean process=false;
	protected int value;
	protected boolean isTerminal = false;
	

	public Node(Board board,int min_points,int max_points,Point position, int value)
	{
		this.board = board;	
		this.max_points=max_points;
		this.min_points=min_points;
		this.position=position;
		this.value=value;
	}

	public abstract List<Node> giveChilds();
	
	//	Retorna true si no es necesario seguir recorriendo los hijos
	public boolean alphaBetaPrune() {
		return alpha>=beta;
	}

	public abstract void update(int value, Point p);


	public int heuristicValue() {
		if(isTerminal)
			return terminalValue(max_points-min_points);
		
		return max_points-min_points;
	}

	protected int terminalValue(int p) {
		return (max_points>min_points) ? Integer.MAX_VALUE/2+p:Integer.MIN_VALUE/2+p;
	}
	

	public boolean isTerminal() {
		return this.board.endGame();
	}

	public int returnValue()
	{
		return value;
	}


	public String format() {
		return hashCode() + "[label=\""+ ((position!=null) ? "("+position.x+","+position.y+")":"START ") +((!process) ? "\" style=filled color=grey ":" "+nicePrint(value)+"\"")+"]" +";";
	}

	@Override
	public String toString() {
		return String.valueOf(hashCode());
	}


	private String nicePrint(int value)
	{
		if(value==Integer.MAX_VALUE)
			return "+INF";
		
		if(value==Integer.MIN_VALUE)
			return "-INF";
		
		return String.valueOf(value);


	
	}

	public List<Node> getChilds() {
		return childs;
	}

}


