import java.util.LinkedList;
import java.util.List;

public class Board {
	public static final int BLACK = 1;
	public static final int WHITE = -1;
	public static final int EMPTY = 0;
	List<Position> adjacent;

	private int[][] board;

	public Board() {
		board = new int[8][8];
		adjacent = new LinkedList<Position>();
		/*
		 * for(int i = 0; i < 8; i++){ for(int j = 0; j < 8; j++){ board[i][j] =
		 * EMPTY; } } is done implicit
		 */
	}

	public void start() {
		board[3][3] = WHITE;
		board[3][4] = BLACK;
		board[4][4] = WHITE;
		board[4][3] = BLACK;

		adjacent.add(new Position(2, 2));
		adjacent.add(new Position(2, 3));
		adjacent.add(new Position(2, 4));
		adjacent.add(new Position(2, 5));
		adjacent.add(new Position(3, 2));
		adjacent.add(new Position(3, 5));
		adjacent.add(new Position(5, 2));
		adjacent.add(new Position(5, 3));
		adjacent.add(new Position(5, 4));
		adjacent.add(new Position(5, 5));
		adjacent.add(new Position(4, 2));
		adjacent.add(new Position(4, 5));

	}

	public List<Position> getAdjacent() {
		return adjacent;
	}

	/**
	 * @param pos:
	 *            the position to check
	 **/
	public boolean legal(Position pos, int pColour) {
		if (!adjacent.contains(pos) || board[pos.row][pos.col] != EMPTY) {
			return false;
		}

		if (legal(pos.row, pos.col, -1, -1, pColour)) {
			return true;
		}
		if (legal(pos.row, pos.col, -1, 0, pColour)) {
			return true;
		}
		if (legal(pos.row, pos.col, -1, 1, pColour)) {
			return true;
		}
		if (legal(pos.row, pos.col, 0, -1, pColour)) {
			return true;
		}
		if (legal(pos.row, pos.col, 0, 1, pColour)) {
			return true;
		}
		if (legal(pos.row, pos.col, 1, -1, pColour)) {
			return true;
		}
		if (legal(pos.row, pos.col, 1, 0, pColour)) {
			return true;
		}
		if (legal(pos.row, pos.col, 1, 1, pColour)) {
			return true;
		}

		return false;
	}

	private boolean legal(int row, int col, int dRow, int dCol, int pC) {
		int newR = row + dRow;
		int newC = col + dCol;
		if (!check(newR) || !check(newC)){
			return false;
		}
		int colour = board[newR][newC];
		if(colour == EMPTY){
			return false;
		}
		
		//TODO
		// changing something to test a theory
		
		return legal(newR, newC, dRow, dCol, pC);
	}

	private boolean check(int coord) {
		return (coord >= 0) && (coord < 8);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("|--------|\n");
		for (int i = 0; i < 8; i++) {
			sb.append("|");
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == EMPTY) {
					sb.append(" ");
				} else if (board[i][j] == WHITE) {
					sb.append("W");
				} else if (board[i][j] == BLACK) {
					sb.append("B");
				}

			}
			sb.append("|\n");
		}
		sb.append("|--------|");
		return sb.toString();
	}
}
