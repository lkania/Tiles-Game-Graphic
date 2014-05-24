package minimax;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import core.Board;


public class MinNode extends Node {
	
	public MinNode(Board board,int min_points,int max_points, Point position)
	{
		super(board,min_points,max_points,position, Integer.MAX_VALUE);
	}

	@Override
	public List<Node> giveChilds() {

		List<Point> regions = board.regions();
		
		if(regions.isEmpty())
			return null;
		
		childs = new LinkedList<Node>();
		bestPlay=regions.get(0);
		for(Point p : regions)
		{
			Board newBoard = board.clone();
			int points = newBoard.delete(p.x, p.y);
			
			childs.add(new MaxNode(newBoard,min_points+points,max_points,p));
		}
		
		return childs;
	}

	@Override
	public void update(int value,Point bestPlay) {
		
		if(value<this.value)
			this.value=value;
		
		if(value<beta) 
		{
			beta=value;
			this.bestPlay=bestPlay;
		}
	}
	
	public boolean isTerminal()
	{
		boolean ans = super.isTerminal();
		if(ans && board.isEmpty())
		{
			min_points*=1.3;
			isTerminal = true;
		}
		return ans;
		
	}
	
	
}
