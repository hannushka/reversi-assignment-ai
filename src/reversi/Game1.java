package reversi;


import java.util.Scanner;

import reversi.Board1.Cellstate1;


public class Game1 {
	private Board1 board;
	private Scanner sc;
	private Player1 player1, player2;
	private int scorePlayer1, scorePlayer2;

	public Game1() {
		board = new Board1();
		sc = new Scanner(System.in);
		scorePlayer1 = 2;
		scorePlayer2 = 2;
	}

	public void play() {
		int time = sc.nextInt();
		//player1 = new PlayerHuman(sc, Cellstate.WHITE, board, new Helper());
		player1 = new PlayerAI1(Cellstate1.WHITE, board, new Helper1(), time);
		player2 = new PlayerAI1(Cellstate1.BLACK, board, new Helper1(), time);
		Player1 currentPlayer = player2;
		Player1 otherPlayer = player1;
		Player1 tmp;
		int points;
		boolean lastPlayerFailed = false;
		while (!board.gameOver()) {
			//Print board and score
			System.out.print(board.toString());
			System.out.println("Current score: W-" + scorePlayer1 + " B-" + scorePlayer2);
			//Current player makes move
			points = currentPlayer.playerMove();
			if (points == 0) {
				if (!lastPlayerFailed)
					lastPlayerFailed = true;
				else 
					break;
			} else if (currentPlayer == player1) {
				scorePlayer1 += points;
				scorePlayer2 -= points-1;
			} else if (currentPlayer == player2) {
				scorePlayer1 -= points-1;
				scorePlayer2 += points;
			}
			//Next player
			tmp = currentPlayer;
			currentPlayer = otherPlayer;
			otherPlayer = tmp;
		}
		System.out.print(board.toString());
		System.out.println("Current score: W-" + scorePlayer1 + " B-" + scorePlayer2);
		if (scorePlayer1 > scorePlayer2)
			System.out.print("Player " + player1.getColor() + " wins!");
		else
			System.out.print("Player " + player2.getColor() + " wins!");
		sc.close();
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Time limit (ms): ");
		new Game1().play();
	}
}