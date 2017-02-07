package reversi;


import java.util.*;

public class Board1 {
	public enum Cellstate1 {
		BLACK, WHITE, EMPTY
	}
	private Cellstate1[][] board;
	private Set<Position1> adj;
	static final int SIZE = 8;

	public Board1() {
		board = new Cellstate1[SIZE][SIZE];
		adj = new HashSet<Position1>();
		//initiating board
		for (int i = 0 ; i < SIZE ; i++) {
			for (int j = 0 ; j < SIZE ; j++) {
				board[i][j] = Cellstate1.EMPTY;
			}
		}
		// starting position for game
		board[3][3] = Cellstate1.WHITE;
		board[3][4] = Cellstate1.BLACK;
		board[4][3] = Cellstate1.BLACK;
		board[4][4] = Cellstate1.WHITE;
		addAdj(3,3);
		addAdj(3,4);
		addAdj(4,3);
		addAdj(4,4);
	}
	
	public Board1(Cellstate1[][] board, Set<Position1> adj) {
		this.board = new Cellstate1[SIZE][SIZE];
		for (int i = 0 ; i < SIZE ; i++) {
			for (int j = 0 ; j < SIZE ; j++) {
				this.board[i][j] = board[i][j];
			}
		}
		this.adj = new HashSet<Position1>();
		for (Position1 p : adj) {
			this.adj.add(p);
		}
	}

	public void placeMarker(Position1 p, Cellstate1 color) {
		board[p.r][p.c] = color;
		addAdj(p.r,p.c);
		adj.remove(p);
	}
	
	public void flipMarkers(Set<Position1> ps, Cellstate1 player) {
		for (Position1 p : ps)
			board[p.r][p.c] = player;
	}

	private void addAdj(int r, int c) {
		int tmpR, tmpC;
		for (int dr = -1; dr < 2; dr++) {
			for (int dc = -1; dc < 2; dc++) {
				tmpR = r + dr;
				tmpC = c + dc;
				if (isAllowedCoords(tmpR,tmpC) && board[tmpR][tmpC] == Cellstate1.EMPTY) 
					adj.add(new Position1(tmpR,tmpC));
			}
		}
	}
	
	public boolean gameOver() {
		return adj.isEmpty();
	}

	public boolean isAllowedCoords(int r, int c) {
		return r < SIZE && r >= 0 && c < SIZE && c >= 0;
	}
	
	public Cellstate1 getCellstate(int r, int c) {
		return board[r][c];
	}
	
	public Set<Position1> getAdjs() {
		return adj;
	}
	
	public Cellstate1[][] getBoard() {
		return board;
	}
	
	public boolean isMoveAllowed(Position1 p) {
		return adj.contains(p);
	}
	
	public Position1 getPositionFromCoords(String coords) {
		int r = Character.getNumericValue(coords.charAt(0))-1;
		int c = (int)coords.charAt(1)-97;
		return new Position1(r,c);
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
