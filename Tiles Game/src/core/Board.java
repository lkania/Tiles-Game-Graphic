package core;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Board implements Cloneable{

	
	private final int BREAK_POINT = 5;

	private char[][] board;  
	
	public Board clone()
	{
		char[][] newMatrix = new char[board.length][];

		for(int i=0;i<board.length;i++)
		{
			newMatrix[i]=board[i].clone();			
		}
		
		return new Board(newMatrix); 
		
	}

	public Board(char[][] board)
	{
		this.board=board;
	}
	
	public Board(int rows,int columns,int colors)
	{
		generateRandomBoard(rows,columns,colors);
	}

	private int delete(int row, int column, char colour)
	{
		int tiles = 0;

		if(board[column][row]==colour)
		{
			board[column][row]=' ';
			tiles++;

			for(int columnIndex=column-1; columnIndex<=column+1; columnIndex++)

				for(int rowIndex=row-1; rowIndex<=row+1; rowIndex++)

					if(checkPosition(rowIndex,columnIndex) && (Math.abs(rowIndex-row)+Math.abs(columnIndex-column)==1))

						tiles+= delete(rowIndex,columnIndex,colour);

		}

		return tiles;
	}

	private boolean checkPosition(int rowIndex,int columnIndex)
	{
		return rowIndex>=0 && rowIndex<board[0].length && columnIndex>=0 && columnIndex<board.length; 
	}

	private int delete(int row, int column,boolean gravityON)
	{
		if(!checkTile(row,column) || !hasneighbours(row,column))
			return 0;
		
		char colour = board[column][row];
		int tiles = delete(row, column, colour);
		if(gravityON) gravity();
		return points(tiles);
	}

	public int delete(int row,int column)
	{
		return delete(row,column,true);
	}
	
	private boolean hasneighbours(int row, int column) {

		for(int columnIndex=column-1; columnIndex<=column+1; columnIndex++)

			for(int rowIndex=row-1; rowIndex<=row+1; rowIndex++)

				if(checkPosition(rowIndex,columnIndex) && (Math.abs(rowIndex-row)+Math.abs(columnIndex-column)==1))

					if(board[columnIndex][rowIndex]==board[column][row])
						return true;

		return false;

	}


	private boolean checkTile(int row,int column) {
		if(board[column][row]==' ')
			return false;

		return true;
	}

	public void gravity()
	{
		for(int column=0;column<board.length;column++)
			gravityColumn(column);

		deleteNullColumns();

	}

	private void deleteNullColumns()
	{
		int left=0,right=board.length-1;

		while(left<right)
		{
			if(board[left][board[0].length-1]!=' ')
				left++;
			else if(board[right][board[0].length-1]==' ')
				right--;
			else
			{
				char[] aux = board[left];

				for(int i=left;i<right;i++)
					board[i]=board[i+1];

				board[right]=aux;
			}

		}
	}

	private void gravityColumn(int column)
	{
		int left=0,right=board[0].length-1;

		while(left<right)
		{
			if(board[column][left]==' ')
				left++;
			else if(board[column][right]!=' ')
				right--;
			else
			{
				for(int i=right;i>left;i--)
					board[column][i]=board[column][i-1];

				board[column][left]=' ';
			}

		}
	}


	public void print() {

		for(int row=0;row < board[0].length;row++)
		{
			for(int column=0;column< board.length;column++)
			{
				System.out.print(board[column][row]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}


	private void generateRandomBoard(int rows,int columns,int colors)
	{
		char[][] newBoard = new char[columns][rows];

		for(int column=0;column<columns;column++)
			for(int row=0;row<rows;row++)
				newBoard[column][row] = (char)((int) (Math.random()*colors)+49);


		this.board=newBoard;

	}
	
	
	private int points(int tiles)
	{
		if(tiles==0)
			return 0;
		
		if(tiles<=BREAK_POINT)
			return (int) Math.pow(2,tiles-2);
		
		return 2*tiles;
		
	}

	
	public List<Point> regions() {

		List<Point> regions = new ArrayList<Point>();

		Board newBoard = clone();
		
		

		for(int column=0;column<newBoard.board.length; column++) {
			for(int row=0; row< newBoard.board[0].length; row++) {
				if(newBoard.delete(row, column,false) != 0) {
					regions.add(new Point(row, column));
				}
			}
		}
		
		return regions;
	}
 
	public boolean endGame()
	{
		return regions().isEmpty();
	}

	public char[][] getMatrix() {
		return board;
	}

	public boolean isEmpty() {
		return board[0][board[0].length-1]==' ';
	}










}
