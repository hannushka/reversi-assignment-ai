package reversi;

import java.util.HashSet;
import java.util.Set;

import reversi.Board.Cellstate;

public class Helper {

	/**
	 * ----------------------- |r-1,c-1|r-1,c|r-1,c+1| ----------------------- |
	 * r,c-1 | r,c | r,c+1 | ----------------------- |r+1,c-1|r+1,c|r+1,c+1|
	 * -----------------------
	 * 
	 * If position for move is r,c check surrounding tiles for possible score.
	 * These are diagonal left upward, vertical upward, diagonal right upward
	 * horizontal left, horizontal right, diagonal left downward vertical
	 * downward, diagonal right downward
	 */

	public Set<Position> evaluateMove(Position pos, Cellstate player, Board board) {
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

	private Set<Position> checkDirectionOfPos(Position pos, int dr, int dc, Cellstate player, Cellstate opponent,
			Board board) {
		int r = pos.getRow() + dr;
		int c = pos.getColumn() + dc;
		Set<Position> flips = new HashSet<Position>();
		if (board.isAllowedCoords(r, c) && board.getCellstate(r, c) == opponent) {
			return stepDirection(r, c, dr, dc, player, flips, board);
		} else {
			return flips;
		}
	}

	private Set<Position> stepDirection(int r, int c, int dr, int dc, Cellstate player, Set<Position> flips,
			Board board) {
		flips.add(new Position(r, c));
		r = r + dr;
		c = c + dc;
		if (!board.isAllowedCoords(r, c) || board.getCellstate(r, c) == Cellstate.EMPTY)
			return new HashSet<Position>();
		else if (board.getCellstate(r, c) == player)
			return flips;
		return stepDirection(r, c, dr, dc, player, flips, board);
	}
}
