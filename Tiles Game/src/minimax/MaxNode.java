package minimax;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import core.Board;


public class MaxNode extends Node {

	
	public MaxNode(Board board, double min_points,double max_points,Point position)
	{
		super(board,min_points,max_points,position, Integer.MIN_VALUE);
	}

	@Override
	public List<Node> giveChildren() {
		
		
		List<Point> regions = board.regions();
		
		if(regions.isEmpty())
			return null;
		
		children = new LinkedList<Node>();
		bestPlay=regions.get(0);
		for(Point p : regions)
		{
			
			Board newBoard = board.clone();
			int points = newBoard.delete(p.x, p.y);
			
			children.add(new MinNode(newBoard,min_points,max_points+points,p));
		}
		
		return children;
	}
	
	@Override
	public void update(double value,Point bestPlay) {
		if(value>this.value)
		{
			this.value=value;
			this.bestPlay=bestPlay;
		}
				
		if(value>alpha) 
		{	
			alpha=value;
			
		}
		
	}
	
	public boolean isTerminal()
	{
		boolean ans = super.isTerminal();
		if(ans)
		{
			isTerminal = true;
			if( board.isEmpty())
				min_points*=1.3;
		}
		
		return ans;
	}

		

	@Override
	public String format() {
		
		return super.format()+this.toString()+" [shape=box];" ;
	}

	@Override
	public boolean alphaBetaPrune() {
		return value>=beta;
	}

		
}
