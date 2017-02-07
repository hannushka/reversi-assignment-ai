package reversi;

import java.util.HashSet;
import java.util.Set;

import reversi.Board1.Cellstate1;

public class Helper1 {

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

	public Set<Position1> evaluateMove(Position1 pos, Cellstate1 player, Board1 board) {
		Set<Position1> flips = new HashSet<Position1>();
		Cellstate1 opponent;
		if (player == Cellstate1.BLACK)
			opponent = Cellstate1.WHITE;
		else
			opponent = Cellstate1.BLACK;
		for (int dr = -1; dr < 2; dr++) {
			for (int dc = -1; dc < 2; dc++) {
				flips.addAll(checkDirectionOfPos(pos, dr, dc, player, opponent, board));
			}
		}
		return flips;
	}

	private Set<Position1> checkDirectionOfPos(Position1 pos, int dr, int dc, Cellstate1 player, Cellstate1 opponent,
			Board1 board) {
		int r = pos.r + dr;
		int c = pos.c + dc;
		Set<Position1> flips = new HashSet<Position1>();
		if (board.isAllowedCoords(r, c) && board.getCellstate(r, c) == opponent) {
			return stepDirection(r, c, dr, dc, player, flips, board);
		} else {
			return flips;
		}
	}

	private Set<Position1> stepDirection(int r, int c, int dr, int dc, Cellstate1 player, Set<Position1> flips,
			Board1 board) {
		flips.add(new Position1(r, c));
		r = r + dr;
		c = c + dc;
		if (!board.isAllowedCoords(r, c) || board.getCellstate(r, c) == Cellstate1.EMPTY)
			return new HashSet<Position1>();
		else if (board.getCellstate(r, c) == player)
			return flips;
		return stepDirection(r, c, dr, dc, player, flips, board);
	}
}
