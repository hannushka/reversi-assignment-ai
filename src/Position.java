
public class Position{
	private int row, col;
	
	/**
	 * @param row: row coord in board def (1-8)
	 * @param col: col coord in board def (a-h)
	 */
	public Position(int row, char col){
		this.row = row-1;
		this.col = col-97;
	}
	
	/**
	 * @param row: row coord in board index (0-7)
	 * @param col: col coord in board index (0-7)
	 */
	public Position(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return col;
	}

	public boolean equals(Object p){
		Position p2 = (Position)p;
		return (row == p2.row) && (col == p2.col);
	}
	
	public String toString(){
		char c = (char) (col+97);
		String pos = (row+1) + "" + c;
		return pos;
	}

	
}
