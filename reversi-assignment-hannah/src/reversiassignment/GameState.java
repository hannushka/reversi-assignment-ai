package reversiassignment;

import reversiassignment.Board.Cellstate;

public class GameState {
	Board board;
	Cellstate currentPlayer;
	
	public GameState(Board b, Cellstate cP) {
		board = b;
		currentPlayer = cP;
	}

}
