import java.awt.Point;
import java.util.LinkedList;
import java.util.List;


public class MaxNode extends Node {

	
	public MaxNode(Board board, int min_points,int max_points,Point position)
	{
		super(board,min_points,max_points,position, Integer.MIN_VALUE);
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
			
			childs.add(new MinNode(newBoard,min_points,max_points+points,p));
		}
		
		super.childs = childs;
		return childs;
	}
	
	


	@Override
	public void update(int value,Point bestPlay) {
		if(value>this.value)
			this.value=value;
				
		if(value>alpha) 
		{	
			
			alpha=value;
			this.bestPlay=bestPlay;
		}
		
	}
	
	public boolean isTerminal()
	{
		boolean ans = super.isTerminal();
		max_points+= (ans) ? 0.3*max_points:0;
		return ans;
		
	}


	@Override
	public String format() {
		
		return super.format()+this.toString()+" [shape=box];" ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
