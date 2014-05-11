import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Queue;



public class MiniMax {

	private MaxNode initial_node;
	private boolean pruneAlphaBeta;
	private boolean timeLimit;
	private double timeMax;
	private boolean makeTree;
	
	private int maxLevel;

	public MiniMax(boolean pruneAlphaBeta,boolean timeLimit, int maxLevel, double timeMax,boolean makeTree) {
		this.pruneAlphaBeta = pruneAlphaBeta;
		this.timeLimit = timeLimit;
		this.maxLevel=maxLevel;
		this.timeMax=timeMax;
		this.makeTree=makeTree;
	}


	private int solution(Node node,int level,int alpha,int beta)
	{
		
		node.process=true;
		boolean terminal=node.isTerminal();
		if(level == 0 || terminal)
		{	
		
			node.allChildsTerminal=terminal;  /*Necesario para solution con TimeLimit*/
			int heuristicValue = node.heuristicValue();
			node.update(heuristicValue, null);
			return heuristicValue;
		}

		node.alpha=alpha;
		node.beta=beta;
		
		
		for(Node child : node.giveChilds())
		{
			
			node.update(solution(child,level-1,node.alpha,node.beta),child.position);
			
			node.allChildsTerminal = node.allChildsTerminal && child.allChildsTerminal; /*Necesario para solution con TimeLimit*/
						
			if(!pruneAlphaBeta || node.alphaBetaPrune())
			{
				
				return node.returnValue();
			}
		}
		return node.returnValue();
	}
	
	
	private void solution(Node node)
	{
		int level=0; double time = timeMax;
		
		while(time>0)
		{
			//	Luego de cada pasada de solution recursivo, si no se habia llegado a nodos terminales deja el initial_node.allChildsTErminal en false, por lo cual debemos resetearlo. 
			
			initial_node.allChildsTerminal=true; 
			
			long start = System.currentTimeMillis();
			
			solution(initial_node,++level,initial_node.alpha,initial_node.beta);
			
			long end = System.currentTimeMillis();
			
			time-=end-start;
			
			if(initial_node.allChildsTerminal)
				break;
			
		}
		
	}
	
	public long testPrune(Board board)
	{
		long startTime = System.currentTimeMillis();
		solution(board);	
		long endTime = System.currentTimeMillis();;
		long duration = endTime - startTime;
		return duration;
		
	}
	
	

	public Point solution(Board board)
	{
		this.initial_node = new MaxNode(board,0,0,null);

		if(!timeLimit)
			solution(initial_node,maxLevel,initial_node.alpha,initial_node.beta);
		else
			solution(initial_node);
		
		if(makeTree)
			createTree();		
		
		return initial_node.bestPlay;
	}

	public void createTree()
	{
		BufferedWriter writer = null;

		try {
			File logFile = new File("Tree.txt");

			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write("graph tree {");
			writer.newLine();

			Queue<Node> queue = new LinkedList<Node>();
			queue.add(initial_node);
		

			while(!queue.isEmpty())
			{

				Node aux = queue.poll();
				writer.write(aux.format());

				if(aux.getChilds()!=null)
				{
					for(Node child : aux.getChilds())
					{
						StringBuffer sb = new StringBuffer();
						sb.append(aux.toString());
						sb.append(" -- ");
						sb.append(child.toString());
						sb.append(";");
						writer.newLine();
						writer.write(sb.toString());
						writer.newLine();
						queue.add(child);
						if(aux.bestPlay==child.position)
						{
							writer.write(child.toString()+" [ color=red];");
							writer.newLine();
						}
					}
				}
			}
			writer.newLine();
			writer.write("}");
			
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}





}
