package minimax;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import core.Board;


public class MaxNode extends Node {

	
	public MaxNode(Board board, int min_points,int max_points,Point position)
	{
		super(board,min_points,max_points,position, Integer.MIN_VALUE);
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
			
			childs.add(new MinNode(newBoard,min_points,max_points+points,p));
		}
		
		return childs;
	}
	
	@Override
	public void update(int value,Point bestPlay) {
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
		if(ans && board.isEmpty())
		{
			max_points*=1.3;
			
			
		}
		if(ans)
			isTerminal = true;
		return ans;
		
	}

		

	@Override
	public String format() {
		
		return super.format()+this.toString()+" [shape=box];" ;
	}

		
}
