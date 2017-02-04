package reversiassignment;

import reversiassignment.Board.Cellstate;

public interface Player {
	public int playerMove();
	public Cellstate getColor();
	public void modifyScore(int points);
	public int getScore();
	
}
