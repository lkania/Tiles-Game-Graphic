package minimax;
import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Queue;

import core.Board;

public class MiniMax {

	private MaxNode initial_node;
	private boolean pruneAlphaBeta;
	private boolean timeLimit;
	private double timeMax;
	private boolean makeTree;
	private boolean endByTime=false;
	private int maxLevel;

	public MiniMax(boolean pruneAlphaBeta,boolean timeLimit, int maxLevel, double timeMax,boolean makeTree) {
		this.pruneAlphaBeta = pruneAlphaBeta;
		this.timeLimit = timeLimit;
		this.maxLevel=maxLevel;
		this.timeMax=timeMax;
		this.makeTree=makeTree;
	}


	private double solution(Node node,int level,double alpha,double beta,double time)
	{

		long start = System.currentTimeMillis();
		
		node.process=true;
		if(node.isTerminal() || level == 0 || (timeLimit && time<0))
		{	
			
			if(timeLimit && time<0)
				endByTime=true;
			double heuristicValue = node.heuristicValue();
			node.update(heuristicValue, null);
			return heuristicValue;
		}

		node.alpha=alpha;
		node.beta=beta;
		
		
		long end = System.currentTimeMillis();
		
		long timeMain = end-start;
		
		time -= timeMain;
		
		for(Node child : node.giveChildren())
		{
			long startCycle = System.currentTimeMillis();
			
			node.update(solution(child,level-1,node.alpha,node.beta,time),child.position);
			
		
			if(pruneAlphaBeta && node.alphaBetaPrune())
				return node.returnValue();
			
			long endCycle = System.currentTimeMillis();
			
			long timeCycle = endCycle - startCycle;
			
			time -= timeCycle;
			
			
		}
		return node.returnValue();
	}	
	
	private void solutionByTime(Board board,double max_points,double min_points)
	{
		int level=0; double time = timeMax; MaxNode backupNode = null;
		
		initial_node = new MaxNode(board,min_points,max_points,null);
		
		while(!endByTime) 
		{
			backupNode = initial_node; 

			initial_node = new MaxNode(board,min_points,max_points,null);
			
			long start = System.currentTimeMillis();
			
			solution(initial_node,++level,initial_node.alpha,initial_node.beta,time);
						
			if(initial_node.value<=Integer.MIN_VALUE/2 || initial_node.value>=Integer.MAX_VALUE/2)
				break;
			
			long end = System.currentTimeMillis();
			
			time-=(end-start);
					
		}
		
		if(endByTime)
			initial_node = backupNode;
		
		endByTime=false;
	}
	
	public long testPrune(Board board)
	{
		long startTime = System.currentTimeMillis();
		solution(board,0,0);	
		long endTime = System.currentTimeMillis();;
		long duration = endTime - startTime;
		return duration;
		
	}

	public Point solution(Board board,double max_points,double min_points)
	{
		this.initial_node = new MaxNode(board,min_points,max_points,null);

		if(!timeLimit)
			solution(initial_node,maxLevel,initial_node.alpha,initial_node.beta,0);
		else
			solutionByTime(board,max_points,min_points);
		
		if(makeTree)
			createTree();		
		
		return initial_node.bestPlay;
	}

	public void createTree()
	{
		BufferedWriter writer = null;

		try {
			File logFile = new File("Tree.dot");

			writer = new BufferedWriter(new FileWriter(logFile));
			
			System.out.println("El archivo se creo en: "+logFile.getAbsolutePath());
			
			writer.write("graph tree {");
			writer.newLine();

			Queue<Node> queue = new LinkedList<Node>();
			queue.add(initial_node);
		

			while(!queue.isEmpty())
			{

				Node aux = queue.poll();
				writer.write(aux.format());
				writer.newLine();

				if(aux.getChilds()!=null)
				{
					for(Node child : aux.getChilds())
					{
						StringBuffer sb = new StringBuffer();
						sb.append(aux.toString());
						sb.append(" -- ");
						sb.append(child.toString());
						sb.append(";");
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
