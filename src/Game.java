
public class Game {

	private AiPlayer white, black;
	private boolean turnBlack;
	private boolean gameOver, blackDone, whiteDone;

	public Game(AiPlayer w, AiPlayer b) {
		white = w;
		black = b;
		turnBlack = true;
		gameOver = false;
	}

	public void play() {
		while (!gameOver) {
			if (turnBlack) {
				blackDone = black.doTurn();
			} else {
				whiteDone = white.doTurn();
			}
			turnBlack = !turnBlack;
			System.out.println(toString());
			if (black.fullBoard() || (!whiteDone && !blackDone)) {
				gameOver = true;
				String winner;
				int blackTiles = black.nbrTiles();
				int whiteTiles = white.nbrTiles();
				if(blackTiles > whiteTiles) {
					winner = "Black won!";
				} else if (blackTiles < whiteTiles) {
					winner = "White won!";
				} else {
					winner = "It's a tie!";
				}
				
				System.out.println(winner);
			}
			
		}
	}

	/**
	 * Returns the current game as a string. Contains how many placed tiles each
	 * colour has and whose turn it is.
	 * 
	 * @return a string with the current game.
	 */
	public String toString() {
		StringBuilder game = new StringBuilder();
		game.append("Black: " + black.nbrTiles() + "\n");
		if (turnBlack) {
			game.append("My turn\n");
			game.append(black.printBoard() + "\n");
		} else {
			game.append(white.printBoard() + "\n");
		}
		game.append("White: " + white.nbrTiles() + "\n");
		if (!turnBlack)
			game.append("My turn\n");
		return game.toString();
	}
}
