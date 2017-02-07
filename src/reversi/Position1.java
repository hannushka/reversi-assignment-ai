package reversi;


import java.util.Arrays;

public class Position1 {
	protected int r, c;

	public Position1(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public boolean equals(Object other){
		if (other instanceof Position1) {
			Position1 p = (Position1) other;
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
