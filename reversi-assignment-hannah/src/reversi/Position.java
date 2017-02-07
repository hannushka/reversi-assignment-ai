package reversi;


import java.util.Arrays;

public class Position {
	private int row, column;

	public Position(int r, int c) {
		this.row = r;
		this.column = c;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
	
	@Override
	public boolean equals(Object other){
		if (other instanceof Position) {
			Position p = (Position) other;
			return p.row==row && p.column==column;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(new Object[] { new Integer(row), new Integer(column) });
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append((row+1));
		sb.append((char)(97+column));
		return sb.toString();
	}
}
