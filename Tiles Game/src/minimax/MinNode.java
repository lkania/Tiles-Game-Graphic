package minimax;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import core.Board;


public class MinNode extends Node {
	
	public MinNode(Board board,double min_points,double max_points, Point position)
	{
		super(board,min_points,max_points,position, Integer.MAX_VALUE);
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
			
			children.add(new MaxNode(newBoard,min_points+points,max_points,p));
		}
		
		return children;
	}

	@Override
	public void update(double value,Point bestPlay) {
		
		if(value<this.value)
		{
			this.value=value;
			this.bestPlay=bestPlay;
		}
		
		if(value<beta) 
		{
			beta=value;
		
		}
	}
	
	public boolean isTerminal()
	{
		boolean ans = super.isTerminal();
		if(ans)
		{
			isTerminal = true;
			if( board.isEmpty())
				max_points*=1.3;
		}
		
		return ans;
		
	}
	
	@Override
	public boolean alphaBetaPrune() {
		return value<=alpha;
	}
	
}
