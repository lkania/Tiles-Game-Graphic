import java.awt.Point;
import java.util.List;


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
	protected boolean allChildsTerminal=true;


	public Node(Board board,int min_points,int max_points,Point position, int value)
	{
		this.board = board;	
		this.max_points=max_points;
		this.min_points=min_points;
		this.position=position;
		this.value=value;
	}



	public List<Node> getChilds() {
		return childs;
	}

	public void setChilds(List<Node> childs) {
		this.childs = childs;
	}



	public abstract List<Node> giveChilds();
	
	//	Retorna true si no es necesario seguir recorriendo los hijos
	public boolean alphaBetaPrune() {
		return alpha>=beta;
	}

	public abstract void update(int value, Point p);


	public int heuristicValue() {
		return max_points-min_points;
	}

	public boolean isTerminal() {
		return this.board.endGame();
		
	}

	public int returnValue()
	{
		return value;
	}


	public String format() {
		return hashCode() + "[label=\"ALL: "+allChildsTerminal+ ((position!=null) ? "("+position.x+","+position.y+")":"START ") +((!process) ? "\" style=filled color=grey ":" "+nicePrint(value)+"\"")+"]" +";";
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

}


