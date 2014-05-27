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
	public void  readData(String s) throws Exception  {

		try {
			open(s);
		} catch (FileNotFoundException e) {
			
			throw new Exception(e.getMessage());
		}
		
			for(int i=0;i<gamedata.length;i++)
				gamedata[i]=readLineInetegr();
			if(getRows()<0 || getColumn()<0)
				throw new Exception(" Wrong dimension board in file");
			if(turnPoints()<0 || otherPoints()<0)
				throw new Exception("Negative points in file");
			readBoard();
			close();
			
		

	}





	private void readBoard() throws Exception {
		createBoard();

		String s;

		for(int i=0;i<getRows() ;i++){
			s=fBuffer.readLine();
			if(s==null){
				throw new Exception("Archive board wrong ");
			}
			char[] c=s.toCharArray();

			if(c.length!=getColumn())
				throw new Exception("Archive board column problem");

			for(int j=0;j<getColumn() ;j++){
				if((c[j]-'0')<0 || (c[j]-'0')>9 )
					throw new Exception("Archive board : element has to be a 1-9 number");
				board[j][i]=(c[j]=='0') ? ' ':c[j];
			}


		}
		if(fBuffer.ready())
			throw new Exception("Extra line");
	}
	private void createBoard() {
		board=new char[gamedata[1]][gamedata[0]];

	}

	private void close() throws Exception {
		try {
			fBuffer.close();
			f.close();
		} catch (IOException e) {
			throw new Exception(e.getMessage());
			
		}
		

	}
	private int readLineInetegr() throws Exception {

		String s=fBuffer.readLine();
		if(s==null){
			throw new Exception("No data board especified");
		}
		Integer res=Integer.parseInt(s);
		if(res==null)
			throw new Exception("Data board must be numbers");
		return res;

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
