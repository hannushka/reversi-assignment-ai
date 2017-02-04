package reversiassignment;

import java.util.Scanner;

import reversiassignment.Board.Cellstate;

public class PlayerHuman implements Player {
	private Scanner sc;
	private Cellstate color;
	private Board board;
	private int score;
	
	public PlayerHuman(Scanner scanner, Cellstate c, Board b, int s) {
		sc = scanner;
		color = c;
		board = b;
		score = s;
	}
	
	public int playerMove() {
		return 0;
	}

	public Cellstate getColor() {
		return null;
	}

	public void modifyScore(int points) {
		
	}

	public int getScore() {
		return 0;
	}

}
