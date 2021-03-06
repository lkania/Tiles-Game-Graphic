package massive_test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import core.Board;
import minimax.MiniMax;


public class TestPruneVSNoPrune {

	public static void main(String[] args) {
		csvFormat();
	}


	public static void csvFormat()
	{
		System.out.println("Comienza la creacion del Archivo");
		BufferedWriter writer = null;
		int size = 11;
		int max_colors = 5;
		int numberTest=100;
		int levelMax=4;
		double totalDurationWithPoda = 0,totalDurationWithOutPoda = 0;	

		try {
			File logFile = new File("datePruneHeuristic.txt");

			writer = new BufferedWriter(new FileWriter(logFile));
			System.out.println("row col colors level Poda NoPoda");
			writer.write("row col colors level Poda NoPoda");
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
								long durationWithPoda = minimax.testPrune(board);
								totalDurationWithPoda+=durationWithPoda;
								
								MiniMax minimax2 = new MiniMax(false,false,level,0,false);
								long durationWithOutPoda = minimax2.testPrune(board);
								totalDurationWithOutPoda+=durationWithOutPoda;
																
								
							}

							totalDurationWithPoda/=quantityTest;
							totalDurationWithOutPoda/=quantityTest;

							writer.write(String.valueOf(totalDurationWithPoda)+" "+String.valueOf(totalDurationWithOutPoda));
							System.out.print(String.valueOf(totalDurationWithPoda)+" "+String.valueOf(totalDurationWithOutPoda));
							System.out.println();
							writer.newLine();

						}
					}

				}

				
			}
			
			writer.close();
			
			System.out.println("Finaliza la creacion del Archivo");

		} catch (Exception e) {
			e.printStackTrace();
			try {
				writer.close();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}





	}

}









