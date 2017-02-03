import java.util.LinkedList;
import java.util.List;

public class Board {
	public static final int BLACK = 1;
	public static final int WHITE = -1;
	public static final int EMPTY = 0;
	
	private List<Position> adjacent;
	private int[][] board;
	private List<Position> flip;

	public Board() {
		board = new int[8][8];
		adjacent = new LinkedList<Position>();
		flip = new LinkedList<Position>();
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

		adjacent.add(new Position(3, 'c'));
		adjacent.add(new Position(3, 'd'));
		adjacent.add(new Position(3, 'e'));
		adjacent.add(new Position(3, 'f'));
		adjacent.add(new Position(4, 'c'));
		adjacent.add(new Position(4, 'f'));
		adjacent.add(new Position(6, 'c'));
		adjacent.add(new Position(6, 'd'));
		adjacent.add(new Position(6, 'e'));
		adjacent.add(new Position(6, 'f'));
		adjacent.add(new Position(5, 'c'));
		adjacent.add(new Position(5, 'f'));

	}

	/**
	 * Places a tile at position p with colour colour OBS: Do NOT check if the
	 * position is allowed!
	 * 
	 * @param p
	 *            - the position to put the tile
	 * @param colour
	 *            - the colour of the tile to be put, Has to be BLACK or WHITE
	 * @return true - if succeded with placement
	 */
	public boolean placeMarker(Position p, int colour) {
		if (!isLegal(p, colour, true)) {
			return false;
		}
		board[p.getRow()][p.getColumn()] = colour;
		adjacent.remove(p);

		addAdjacent(p, -1, -1);
		addAdjacent(p, -1, 0);
		addAdjacent(p, -1, 1);
		addAdjacent(p, 0, -1);
		addAdjacent(p, 0, 1);
		addAdjacent(p, 1, -1);
		addAdjacent(p, 1, 0);
		addAdjacent(p, 1, 1);
		
		for(Position pos: flip){
			if(board[pos.getRow()][pos.getColumn()] == BLACK){
				board[pos.getRow()][pos.getColumn()] = WHITE;
			} else {
				board[pos.getRow()][pos.getColumn()] = BLACK;
			}
		}
		flip.clear();
		
		return true;
	}

	private void addAdjacent(Position p, int dr, int dc) {
		int r = p.getRow() + dr;
		int c = p.getColumn() + dc;

		Position newP = new Position(r, c);
		if (check(r) && check(c) && board[r][c] == EMPTY && !adjacent.contains(newP)) {
			adjacent.add(newP);
		}
	}

	/**
	 * Returns a list of adjacent tiles to those on the board, any legal move
	 * has to be adjacent.
	 * 
	 * @return a list of adjacent tiles to the ones on the board
	 */
	public List<Position> getAdjacent() {
		return adjacent;
	}

	/**
	 * Checks if a position is legal to place a tile in
	 * 
	 * @param pos
	 *            - the position to check
	 * @param pColour
	 *            - the colour of the tile to be put
	 * @param play - true if trying to place tile, false if just check
	 **/
	public boolean isLegal(Position pos, int pColour, boolean play) {
		if (board[pos.getRow()][pos.getColumn()] != EMPTY || !adjacent.contains(pos)) {
			return false;
		}

		boolean returnValue = false;
		List<Position> localFlip = new LinkedList<Position>();
		if (allowedDirection(pos, -1, -1, pColour) && legal(pos, -1, -1, pColour, localFlip)) {
			returnValue = true;
			for(Position p : localFlip){
				if(play && !flip.contains(p)) flip.add(p);
			}
		}
		localFlip.clear();
		if (allowedDirection(pos, -1, 0, pColour) && legal(pos, -1, 0, pColour, localFlip)) {
			returnValue = true;
			for(Position p : localFlip){
				if(play && !flip.contains(p)) flip.add(p);
			}
		}
		localFlip.clear();
		if (allowedDirection(pos, -1, 1, pColour) && legal(pos, -1, 1, pColour, localFlip)) {
			returnValue = true;
			for(Position p : localFlip){
				if(play && !flip.contains(p)) flip.add(p);
			}
		}
		localFlip.clear();
		if (allowedDirection(pos, 0, -1, pColour) && legal(pos, 0, -1, pColour, localFlip)) {
			returnValue = true;
			for(Position p : localFlip){
				if(play && !flip.contains(p)) flip.add(p);
			}
		} 
		localFlip.clear();
		if (allowedDirection(pos, 0, 1, pColour) && legal(pos, 0, 1, pColour, localFlip)) {
			returnValue = true;
			for(Position p : localFlip){
				if(play && !flip.contains(p)) flip.add(p);
			}
		} 
		localFlip.clear();
		if (allowedDirection(pos, 1, -1, pColour) && legal(pos, 1, -1, pColour, localFlip)) {
			returnValue = true;
			for(Position p : localFlip){
				if(play && !flip.contains(p)) flip.add(p);
			}
		}
		localFlip.clear();
		if (allowedDirection(pos, 1, 0, pColour) && legal(pos, 1, 0, pColour, localFlip)) {
			returnValue = true;
			for(Position p : localFlip){
				if(play && !flip.contains(p)) flip.add(p);
			}
		}
		localFlip.clear();
		if (allowedDirection(pos, 1, 1, pColour) && legal(pos, 1, 1, pColour, localFlip)) {
			returnValue = true;
			for(Position p : localFlip){
				if(play && !flip.contains(p)) flip.add(p);
			}
		}
		return returnValue;
	}

	private boolean legal(Position p, int dRow, int dCol, int pC, List<Position> lF) {
		int newR = p.getRow() + dRow;
		int newC = p.getColumn() + dCol;
		if (!check(newR) || !check(newC)) {
			return false;
		}
		int colour = board[newR][newC];
		if (colour == EMPTY) {
			return false;
		}
		if (pC == colour) {
			return true;
		}
		Position newP = new Position(newR, newC);
		lF.add(newP);
		return legal(newP, dRow, dCol, pC, lF);

	}

	private boolean allowedDirection(Position p, int dr, int dc, int pC) {
		int r = p.getRow();
		int c = p.getColumn();
		if (check(r + dr) && check(c + dc)) {
			if (pC == WHITE && board[r + dr][c + dc] == BLACK) {
				return true;
			}
			if (pC == BLACK && board[r + dr][c + dc] == WHITE) {
				return true;
			}
		}
		return false;
	}

	private boolean check(int coord) {
		return (coord >= 0) && (coord < 8);
	}

	/**
	 * 
	 * @param colour:
	 *            BLACK och WHITE depending on which colour to be counted (not
	 *            checked)
	 * @return nbr of colour tiles on board
	 */
	public int getNbrTiles(int colour) {
		int count = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j] == colour){
					count++;
				}
			}
		}

		return count;
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
