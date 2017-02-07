package reversi;


import java.util.Scanner;

import reversi.Board.Cellstate;


public class Game {
	private Board board;
	private Scanner sc;
	private Player player1, player2;
	private int scorePlayer1, scorePlayer2;

	public Game() {
		board = new Board();
		sc = new Scanner(System.in);
		scorePlayer1 = 2;
		scorePlayer2 = 2;
	}

	public void play() {
		int time = sc.nextInt();
		
		//player1 = new PlayerHuman(sc, Cellstate.WHITE, board, new Helper());
		player1 = new PlayerAI(Cellstate.WHITE, board, new Helper(), time);
		player2 = new PlayerAI(Cellstate.BLACK, board, new Helper(), time);
		Player currentPlayer = player2;
		Player otherPlayer = player1;
		Player tmp;
		int points;
		while (!board.gameOver()) {
			//Print board and score
			System.out.print(board.toString());
			System.out.println("Current score: W-" + scorePlayer1 + " B-" + scorePlayer2);
			//Current player makes move
			points = currentPlayer.playerMove();
			if (currentPlayer == player1 && points != 0) {
				scorePlayer1 += points;
				scorePlayer2 -= points-1;
			} else if (currentPlayer == player2 && points != 0) {
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
		sc.close();
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Time limit (ms): ");
		new Game().play();
	}
}