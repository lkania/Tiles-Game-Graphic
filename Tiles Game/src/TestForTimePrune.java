
public class TestForTimePrune {


	public static void main(String[] args) {

		int size = 8;
		int max_colors = 2;
		int numberTest=100;
		int time=5000;
		
		double totalDuration = 0;	
		
		System.out.println("With TimeLimit: "+time);
		for(int row=2,column=2;row<=size && column<=size;row++,column++)
		{
			for(int colors=2;colors<=max_colors;colors++)
			{
				System.out.println("\tWith "+row+"X"+column);				
				int quantityTest=0;
				for( ;quantityTest<numberTest;quantityTest++)
				{

					Board board = new Board(row,column,colors);
					MiniMax minimax = new MiniMax(true,true,0,time,false);
					long duration = minimax.testPrune(board);
					totalDuration+=duration;
				}

				totalDuration/=quantityTest;

				System.out.println("\t\t Colors: "+colors+" Duration: "+totalDuration );
			}	
		}
	}

















}
