package reversi;


import java.util.Scanner;

import reversi.Board.Cellstate;


public class Game {
	private Board board;
	private Scanner sc;
	private Player player1, player2;
	private int scorePlayer1, scorePlayer2;

	/**
	 * 
	 * @param player1type - 0 if AI player
	 * @param player2type - 0 if AI player
	 */
	public Game(int player1type, int player2type) {
		board = new Board();
		sc = new Scanner(System.in);
		scorePlayer1 = 2;
		scorePlayer2 = 2;
		if(player1type == 0){
			player1 = new PlayerAI(Cellstate.WHITE, board, new Helper());
		} else {
			player1 = new PlayerHuman(sc, Cellstate.WHITE, board, new Helper());
		}
		if(player2type == 0){
			player2 = new PlayerAI(Cellstate.BLACK, board, new Helper());
		} else {
			player2 = new PlayerHuman(sc, Cellstate.WHITE, board, new Helper());
		}
	
	}

	public void play() {
		int time = sc.nextInt();
		player1.setTimeLimit(time);
		player2.setTimeLimit(time);
		
		Player currentPlayer = player2;
		Player otherPlayer = player1;
		Player tmp;
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
		int p1 = 0, p2 = 0;
		/*Scanner scan = new Scanner(System.in);
		System.out.println("Chose which player to be AI (can be both, don't recommend none).");
		System.out.print("Player white type (0 is AI): ");
		p1 = scan.nextInt();
		System.out.print("Player black type (0 is AI): ");
		p2 = scan.nextInt();*/
		System.out.print("Time limit for AI players (ms): ");
		new Game(p1, p2).play();
		//scan.close();
	}
}