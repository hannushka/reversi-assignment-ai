package reversiassignment;

import java.util.Arrays;

public class Position {
	int r, c;

	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public boolean equals(Object other){
		if (other instanceof Position) {
			Position p = (Position) other;
			return p.r==r && p.c==c;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(new Object[] { new Integer(r), new Integer(c) });
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append((r+1));
		sb.append((char)(97+c));
		return sb.toString();
	}
}
