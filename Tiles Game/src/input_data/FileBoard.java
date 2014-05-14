package input_data;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileBoard {
	int let;
	BufferedReader fBuffer ;
	FileReader f;
	int [] gamedata=new int[4];
	char[][] board;

	private void  open(String s) throws FileNotFoundException{
		f=new FileReader(s);
		fBuffer = new BufferedReader(f);
	}
	public void  readData(String s) throws IOException {

		open(s);
		for(int i=0;i<gamedata.length;i++)
			gamedata[i]=readLineInetegr();
		readBoard();
		close();

	}





	private void readBoard() throws IOException {
		createBoard();

		String s;

		for(int i=0;i<getRows() ;i++){
			s=fBuffer.readLine();
			if(s==null){
				throw new IOException();
			}
			char[] c=s.toCharArray();

			for(int j=0;j<getColumn() ;j++){
				board[j][i]=c[j];
			}


		}

	}
	private void createBoard() {
		board=new char[gamedata[0]][gamedata[1]];

	}

	private void close() throws IOException{
		f.close();
		fBuffer.close();
	}
	private int readLineInetegr() throws IOException {

		String s=fBuffer.readLine();
		if(s==null){
			throw new IOException();
		}
		return Integer.parseInt(s);

	}

	public char[][] getBoard(){
		return board;
	}

	public int getRows(){
		return gamedata[0];
	}
	public int getColumn(){
		return gamedata[1];
	}
	public int turnPoints(){
		return gamedata[2];
	}
	public int otherPoints(){
		return gamedata[3];
	}
}
