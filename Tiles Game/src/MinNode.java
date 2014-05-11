import java.awt.Point;
import java.util.LinkedList;
import java.util.List;


public class MinNode extends Node {
	
	public MinNode(Board board,int min_points,int max_points, Point position)
	{
		super(board,min_points,max_points,position, Integer.MAX_VALUE);
	}

	@Override
	public List<Node> giveChilds() {

		this.allChildsTerminal=true;
		
		if(this.getChilds()!=null)
			return this.getChilds();
		
		List<Point> regions = board.regions();
		
		if(regions.size()==0)
			return null;
		
		List<Node> childs = new LinkedList<Node>();
		
		for(Point p : regions)
		{
			Board newBoard = board.clone();
			int points = newBoard.delete(p.x, p.y);
			
			childs.add(new MaxNode(newBoard,min_points+points,max_points,p));
		}
		
		super.childs = childs;
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
		min_points+= (ans) ? 0.3*min_points:0;
		return ans;
		
	}

	
		
	

}
