package reversi;


import java.util.*;

public class Board {
	public enum Cellstate {
		BLACK, WHITE, EMPTY
	}
	private Cellstate[][] board;
	private Set<Position> adj;
	static final int SIZE = 8;

	public Board() {
		board = new Cellstate[SIZE][SIZE];
		adj = new HashSet<Position>();
		//initiating board
		for (int i = 0 ; i < SIZE ; i++) {
			for (int j = 0 ; j < SIZE ; j++) {
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
//		board[1][1] = Cellstate.WHITE;
//		board[1][2] = Cellstate.BLACK;
//		board[2][1] = Cellstate.BLACK;
//		board[2][2] = Cellstate.WHITE;
//		addAdj(1,1);
//		addAdj(1,2);
//		addAdj(2,1);
//		addAdj(2,2);
	}
	
	public Board(Cellstate[][] board, Set<Position> adj) {
		this.board = new Cellstate[SIZE][SIZE];
		for (int i = 0 ; i < SIZE ; i++) {
			for (int j = 0 ; j < SIZE ; j++) {
				this.board[i][j] = board[i][j];
			}
		}
		this.adj = new HashSet<Position>();
		for (Position p : adj) {
			this.adj.add(p);
		}
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
		return r < SIZE && r >= 0 && c < SIZE && c >= 0;
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
	
	public boolean isMoveAllowed(Position p) {
		return adj.contains(p);
	}
	
	public Position getPositionFromCoords(String coords) {
		int r = Character.getNumericValue(coords.charAt(0))-1;
		int c = (int)coords.charAt(1)-97;
		return new Position(r,c);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  a  b  c  d  e  f  g  h\n");
		for (int i = 0 ; i < SIZE ; i++) {
			sb.append(i+1);
			for (int j = 0 ; j < SIZE ; j++) {
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