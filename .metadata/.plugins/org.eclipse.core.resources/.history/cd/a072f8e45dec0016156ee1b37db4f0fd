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

	public void play() {
		//player1 = new PlayerHuman(sc, Cellstate.WHITE, board, new Helper());
		player1 = new PlayerAI(Cellstate.WHITE, board, new Helper());
		player2 = new PlayerAI(Cellstate.BLACK, board, new Helper());
		Player currentPlayer = player2;
		Player otherPlayer = player1;
		Player tmp;
		while (!board.gameOver()) {
			System.out.print(board.toString());
			board.printScore();
			currentPlayer.playerMove();
			//Next player
			tmp = currentPlayer;
			currentPlayer = otherPlayer;
			otherPlayer = tmp;
		}
		System.out.print(board.toString());
		board.printScore();
		sc.close();
	}

	public static void main(String[] args) throws InterruptedException {
		new Game().play();
	}
}