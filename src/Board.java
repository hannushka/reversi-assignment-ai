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

	/**
	 * Sets ut the starting position of the board
	 */
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
	
	/**
	 * Places a tile at position p with colour colour
	 * OBS: Do NOT check if the position is allowed!
	 * 
	 * @param p - the position to put the tile
	 * @param colour - the colour of the tile to be put, Has to be BLACK or WHITE
	 * @return true - if succeded with placement
	 */
	public boolean placeMarker(Position p, int colour){
		if(colour != BLACK && colour != WHITE){
			return false;
		}
		board[p.row][p.col] = colour;
		adjacent.remove(p);
		
		addAdjacent(p, -1, -1);
		addAdjacent(p, -1, 0);
		addAdjacent(p, -1, 1);
		addAdjacent(p, 0, -1);
		addAdjacent(p, 0, 1);
		addAdjacent(p, 1, -1);
		addAdjacent(p, 1, 0);
		addAdjacent(p, 1, 1);
		return true;
		//TODO: check if allowed?
	}

	private void addAdjacent(Position p, int dr, int dc){
		int r = p.row+dr;
		int c = p.col+dc;
		
		if(check(r) && check(c) && board[r][c] == EMPTY){
			adjacent.add(new Position(r, c));
		}
	}
	
	/**
	 * Returns a list of adjacent tiles to those on the board, any legal move has to be adjacent.
	 * 
	 * @return a list of adjacent tiles to the ones on the board
	 */
	public List<Position> getAdjacent() {
		return adjacent;
	}

	/**
	 * Checks if a position is legal to place a tile in
	 * 
	 * @param pos - the position to check
	 * @param pColour - the colour of the tile to be put
	 **/
	public boolean legal(Position pos, int pColour) {
		if (!adjacent.contains(pos) || board[pos.row][pos.col] != EMPTY) {
			return false;
		}

		if (allowedDirection(pos.row, pos.col, -1, -1, pColour) && 
				legal(pos.row, pos.col, -1, -1, pColour)) {
			return true;
		}
		if (allowedDirection(pos.row, pos.col, -1, 0, pColour) && 
				legal(pos.row, pos.col, -1, 0, pColour)) {
			return true;
		}
		if (allowedDirection(pos.row, pos.col, -1, 1, pColour) && 
				legal(pos.row, pos.col, -1, 1, pColour)) {
			return true;
		}
		if (allowedDirection(pos.row, pos.col, 0, -1, pColour) && 
				legal(pos.row, pos.col, 0, -1, pColour)) {
			return true;
		}
		if (allowedDirection(pos.row, pos.col, 0, 1, pColour) && 
				legal(pos.row, pos.col, 0, 1, pColour)) {
			return true;
		}
		if (allowedDirection(pos.row, pos.col, 1, -1, pColour) && 
				legal(pos.row, pos.col, 1, -1, pColour)) {
			return true;
		}
		if (allowedDirection(pos.row, pos.col, 1, 0, pColour) && 
				legal(pos.row, pos.col, 1, 0, pColour)) {
			return true;
		}
		if (allowedDirection(pos.row, pos.col, 1, 1, pColour) && 
				legal(pos.row, pos.col, 1, 1, pColour)) {
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
		if(pC == colour){
			return true;
		}
		return legal(newR, newC, dRow, dCol, pC);
		
		//TODO: done?
	}

	private boolean allowedDirection(int r, int c, int dr, int dc, int pC){
		if(check(r+dr) && check(c+dc)){
			if(pC == WHITE && board[r+dr][c+dc] == BLACK){
				return true;
			}
			if(pC == BLACK && board[r+dr][c+dc] == WHITE){
				return true;
			}
		}
		return false;
	}
	
	private boolean check(int coord) {
		return (coord >= 0) && (coord < 8);
	}

	/**
	 * Returns the current layout of the board as a string.
	 * 
	 * @return a string with the current layout of the board.
	 */
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
