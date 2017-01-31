
public class Position{
	protected int row, col;
	
	/**
	 * @param r: row coord
	 * @param c: col coord
	 */
	public Position(int r, int c){
		row = r;
		col = c;
	}
	
	
	public boolean equals(Object p){
		Position p2 = (Position)p;
		return (row == p2.row) && (col == p2.col);
	}
	
	public String toString(){
		char c = (char) (col+97);
		String pos = (row+1) + ""+ c;
		return pos;
	}

	
}
