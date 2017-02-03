package reversiassignment;

import java.util.*;

/**
 * -----------------------
 * |r-1,c-1|r-1,c|r-1,c+1| 
 * -----------------------
 * | r,c-1 | r,c | r,c+1 |
 * -----------------------
 * |r+1,c-1|r+1,c|r+1,c+1|
 * -----------------------
 * 
 * If position is r,c for addAdj and isLegalMove check surrounding tiles. These are
 *diagonal left upward, vertical upward, diagonal right upward
 *horizontal left, horizontal right, diagonal left downward
 *vertical downward, diagonal right downward
 * */

public class Board {
	public enum Cellstate {
		BLACK, WHITE, EMPTY
	}
	private Cellstate[][] board;
	private Set<Position> adj;

	public Board() {
		board = new Cellstate[8][8];
		adj = new HashSet<Position>();
		//initiating board
		for (int i = 0 ; i < 8 ; i++) {
			for (int j = 0 ; j < 8 ; j++) {
				board[i][j] = Cellstate.EMPTY;
			}
		}
		// starting position for game
		board[3][3] = Cellstate.WHITE;
		board[3][4] = Cellstate.BLACK;
		board[4][3] = Cellstate.BLACK;
		board[4][4] = Cellstate.WHITE;
		addAdj(3,3);
		addAdj(3,4);
		addAdj(4,3);
		addAdj(4,4);
	}
	
	public Board(Cellstate[][] board, Set<Position> adj) {
		this.board = board;
		this.adj = adj;
	}

	public void placeMarker(Position p, Cellstate color) {
		board[p.r][p.c] = color;
		addAdj(p.r,p.c);
		adj.remove(p);
	}
	
	public void flipMarkers(Set<Position> ps, Cellstate player) {
		for (Position p : ps)
			board[p.r][p.c] = player;
	}

	private void addAdj(int r, int c) {
		int tmpR, tmpC;
		for (int dr = -1; dr < 2; dr++) {
			for (int dc = -1; dc < 2; dc++) {
				tmpR = r + dr;
				tmpC = c + dc;
				if (isAllowedCoords(tmpR,tmpC) && board[tmpR][tmpC] == Cellstate.EMPTY) 
					adj.add(new Position(tmpR,tmpC));
			}
		}
	}
	
	public boolean gameOver() {
		return adj.isEmpty();
	}

	public boolean isAllowedCoords(int r, int c) {
		return r < 8 && r >= 0 && c < 8 && c >= 0;
	}
	
	public Cellstate getCellstate(int r, int c) {
		return board[r][c];
	}
	
	public Set<Position> getAdjs() {
		return adj;
	}
	
	public Cellstate[][] getBoard() {
		return board;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  a  b  c  d  e  f  g  h\n");
		for (int i = 0 ; i < 8 ; i++) {
			sb.append(i+1);
			for (int j = 0 ; j < 8 ; j++) {
				switch (board[i][j]) {
				case EMPTY:
					sb.append("|_|");
					break;
				case BLACK:
					sb.append("|B|");
					break;
				case WHITE:
					sb.append("|W|");
					break;
				default:
					break;
				}
			}
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
}
