package reversiassignment;

import reversiassignment.Board.Cellstate;

public interface Player {
	public void playerMove();
	public Cellstate getColor();
}
