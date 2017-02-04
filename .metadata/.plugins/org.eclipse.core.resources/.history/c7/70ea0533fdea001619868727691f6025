package reversiassignment;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import reversiassignment.Board.Cellstate;

public class PlayerAI implements Player{
	private Cellstate color;
	private Board board;
	private int score;
	
	public PlayerAI(Cellstate c, Board b, int s) {
		this.color = c;
		board = b;
		score = s;
	}
	
	public int playerMove() {
		// Player is choosing position for next move
		Iterator<Position> itr1 = board.getAdjs().iterator();
		Set<Position> flips;
		Position p;
		do {
			p = itr1.next();
			flips = evaluateMove(p, color, board);
		} while (flips.isEmpty() && itr1.hasNext());
		// Placing and flipping
		if (!flips.isEmpty()) {
			System.out.println("Player " + color + " placed marker at " + p.toString());
			board.placeMarker(p, color);
			board.flipMarkers(flips, color);
		}
		return flips.size();
	}
	
	/**
	 * -----------------------
	 * |r-1,c-1|r-1,c|r-1,c+1| 
	 * -----------------------
	 * | r,c-1 | r,c | r,c+1 |
	 * -----------------------
	 * |r+1,c-1|r+1,c|r+1,c+1|
	 * -----------------------
	 * 
	 * If position for move is r,c check surrounding tiles for possible score. These are
	 *diagonal left upward, vertical upward, diagonal right upward
	 *horizontal left, horizontal right, diagonal left downward
	 *vertical downward, diagonal right downward
	 * */
	
	private Set<Position> evaluateMove(Position pos, Cellstate player, Board board) {
		Set<Position> flips = new HashSet<Position>();
		Cellstate opponent;
		if (player == Cellstate.BLACK)
			opponent = Cellstate.WHITE;
		else
			opponent = Cellstate.BLACK;
		for (int dr = -1; dr < 2; dr++) {
			for (int dc = -1; dc < 2; dc++) {
				flips.addAll(checkDirectionOfPos(pos, dr, dc, player, opponent, board));
			}
		}
		return flips;
	}

	private Set<Position> checkDirectionOfPos(Position pos, int dr, int dc, Cellstate player, Cellstate opponent, Board board) {	
		int r = pos.r + dr;
		int c = pos.c + dc;
		Set<Position> flips = new HashSet<Position>();
		if (board.isAllowedCoords(r, c) && board.getCellstate(r, c) == opponent) {
			return stepDirection(r, c, dr, dc, player, flips, board);
		} else {
			return flips;
		}
	}

	private Set<Position> stepDirection(int r, int c, int dr, int dc, Cellstate player, Set<Position> flips, Board board) {
		flips.add(new Position(r,c));
		r = r + dr;
		c = c + dc;
		if (!board.isAllowedCoords(r, c) || board.getCellstate(r, c) == Cellstate.EMPTY)
			return new HashSet<Position>();
		else if (board.getCellstate(r, c) == player)
			return flips;
		return stepDirection(r, c, dr, dc, player, flips, board);
	}
	
	public Cellstate getColor() {
		return color;
	}
	
	public void modifyScore(int points) {
		score += points;
	}
	
	public int getScore() {
		return score;
	}

}
