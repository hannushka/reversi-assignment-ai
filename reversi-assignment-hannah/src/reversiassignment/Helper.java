package reversiassignment;

import java.util.HashSet;
import java.util.Set;

import reversiassignment.Board.Cellstate;

public class Helper {
	private Board board;

	public Helper(Board board) {
		this.board = board;
	}

	public Set<Position> evaluateMove(Position pos, Cellstate player) {
		Set<Position> flips = new HashSet<Position>();
		Cellstate opponent;
		if (player == Cellstate.BLACK)
			opponent = Cellstate.WHITE;
		else
			opponent = Cellstate.BLACK;
		for (int dr = -1; dr < 2; dr++) {
			for (int dc = -1; dc < 2; dc++) {
				flips.addAll(checkDirectionOfPos(pos, dr, dc, player, opponent));
			}
		}
		return flips;
	}

	private Set<Position> checkDirectionOfPos(Position pos, int dr, int dc, Cellstate player, Cellstate opponent) {	
		int r = pos.r + dr;
		int c = pos.c + dc;
		Set<Position> flips = new HashSet<Position>();
		if (board.isAllowedCoords(r, c) && board.getCellstate(r, c) == opponent) {
			return stepDirection(r, c, dr, dc, player, flips);
		} else {
			return flips;
		}
	}

	private Set<Position> stepDirection(int r, int c, int dr, int dc, Cellstate player, Set<Position> flips) {
		flips.add(new Position(r,c));
		r = r + dr;
		c = c + dc;
		if (!board.isAllowedCoords(r, c) || board.getCellstate(r, c) == Cellstate.EMPTY)
			return new HashSet<Position>();
		else if (board.getCellstate(r, c) == player)
			return flips;
		return stepDirection(r, c, dr, dc, player, flips);
	}
}
