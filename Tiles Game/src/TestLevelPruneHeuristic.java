
public class TestLevelPruneHeuristic {

	public static void main(String[] args) {

		int size = 16;
		int max_colors = 2;
		int numberTest=100;
		int level=5;

		double totalDuration = 0;	

		System.out.println("With LevelLimit: "+level);
		for(int row=2,column=2;row<=size && column<=size;row++,column++)
		{
			System.out.println("\tWith "+row+"X"+column);
			for(int colors=2;colors<=max_colors;colors++)
			{
								
				int quantityTest=0;
				for( ;quantityTest<numberTest;quantityTest++)
				{

					Board board = new Board(row,column,colors);
					MiniMax minimax = new MiniMax(true,false,level,0,false);
					long duration = minimax.testPrune(board);
					totalDuration+=duration;
				}

				totalDuration/=quantityTest;

				System.out.println("\t\t Colors: "+colors+" [AVG]Duration: "+totalDuration );
			}	
		}

	}
}









