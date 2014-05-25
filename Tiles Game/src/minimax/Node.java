package minimax;
import java.awt.Point;
import java.util.List;

import core.Board;


public abstract class Node {

	
	protected Board board;
	protected double alpha=Integer.MIN_VALUE;
	protected double beta=Integer.MAX_VALUE;
	protected double min_points;
	protected double max_points;
	protected Point position; 
	protected List<Node> children=null;
	protected Point bestPlay;
	protected boolean process=false;
	protected double value;
	protected boolean isTerminal = false;
	

	public Node(Board board,double min_points,double max_points,Point position, int value)
	{
		this.board = board;	
		this.max_points=max_points;
		this.min_points=min_points;
		this.position=position;
		this.value=value;
	}

	public abstract List<Node> giveChildren();
	
	//	Retorna true si no es necesario seguir recorriendo los hijos
	public abstract boolean alphaBetaPrune();

	public abstract void update(double value, Point p);


	public double heuristicValue() {
		if(isTerminal)
			return terminalValue(max_points-min_points);
		
		return max_points-min_points;
	}

	protected double terminalValue(double p) {
		return (max_points>min_points) ? Integer.MAX_VALUE/2+p:Integer.MIN_VALUE/2+p;
	}
	

	public boolean isTerminal() {
		return this.board.endGame();
	}

	public double returnValue()
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


	private String nicePrint(double value)
	{
		if(value>=(double) Integer.MAX_VALUE/2)
			return "+INF";
		
		if(value<=(double) Integer.MIN_VALUE/2)
			return "-INF";
		
		return String.valueOf(value);


	
	}

	public List<Node> getChilds() {
		return children;
	}

}


