package massive_test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import core.Board;
import minimax.MiniMax;


public class TestLevelPruneHeuristic {

	public static void main(String[] args) {
		csvFormat();
	}


	public static void csvFormat()
	{
		System.out.println("Comienza la creación del Archivo");
		BufferedWriter writer = null;
		int size = 14;
		int max_colors = 5;
		int numberTest=100;
		int levelMax=10;
		double totalDuration = 0;	

		try {
			File logFile = new File("datePruneHeuristic.txt");

			writer = new BufferedWriter(new FileWriter(logFile));
			System.out.println("row col colors level time");
			writer.write("row col colors level time");
			writer.newLine();
			for(int level=1;level<=levelMax;level++)
			{
				for(int row=2;row<=size;row++)
				{
					for(int column=2;column<=size;column++)
					{
						for(int colors=2;colors<=max_colors;colors++)
						{
							System.out.print(row+" "+column+" "+colors+" "+level+" ");
							writer.write(row+" "+column+" "+colors+" "+level+" ");
							int quantityTest=0;
							for( ;quantityTest<numberTest;quantityTest++)
							{

								Board board = new Board(row,column,colors);
								MiniMax minimax = new MiniMax(true,false,level,0,false);
								long duration = minimax.testPrune(board);
								totalDuration+=duration;
							}

							totalDuration/=quantityTest;

							writer.write(String.valueOf(totalDuration));
							System.out.print(String.valueOf(totalDuration));
							System.out.println();
							writer.newLine();

						}
					}

				}

				
			}
			
			writer.close();
			
			System.out.println("Finalizó la creación del Archivo");

		} catch (Exception e) {
			e.printStackTrace();
			try {
				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}





	}

}









