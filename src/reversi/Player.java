package reversi;

import reversi.Board.Cellstate;

public interface Player {
	public int playerMove();
	public Cellstate getColor();
	public void setTimeLimit(int timeLimit);
}
