package reversiassignment;

import java.util.Scanner;

import reversiassignment.Board.Cellstate;

public class Game {
	private Board board;
	private Scanner sc;
	private Player player1, player2;

	public Game() {
		board = new Board();
		sc = new Scanner(System.in);
	}
	
	private void setUp() {
		int choice;
		do {
			System.out.println("Which color would you like to play? 1 = black, 2 = white");
			choice = sc.nextInt();
		} while (choice != 1 && choice != 2);
		if (choice == 1) {
			player1 = new PlayerAI(Cellstate.WHITE, board, 2);
			player2 = new PlayerHuman(sc, Cellstate.BLACK, board, 2);
		} else {
			player1 = new PlayerHuman(sc, Cellstate.WHITE, board, 2);
			player2 = new PlayerAI(Cellstate.BLACK, board, 2);
		}
		System.out.print(board.toString()); //Print board
	}

	public void play() {
		setUp();
		Player currentPlayer = player1;
		Player otherPlayer = player2;
		Player tmp;
		while (!board.gameOver()) {
			int flips = currentPlayer.playerMove(); //Player makes move
			currentPlayer.modifyScore(flips+1);
			otherPlayer.modifyScore(-flips);
			System.out.print(board.toString()); //Print new board
			System.out.println("Score: B-" + player1.getScore() + " W-" + player2.getScore());
			//Next player
			tmp = currentPlayer;
			currentPlayer = otherPlayer;
			otherPlayer = tmp;
		}
		sc.close();
	}

	public static void main(String[] args) throws InterruptedException {
		new Game().play();
	}
}