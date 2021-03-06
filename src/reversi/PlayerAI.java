package reversi;


import java.util.Set;

import reversi.Board.Cellstate;

public class PlayerAI implements Player {
	private Cellstate color;
	private Cellstate opponent;
	private Board board;
	private Helper helper;
	private int timeLimit;

	public PlayerAI(Cellstate c, Board b, Helper h) {
		this.color = c;
		if (c == Cellstate.WHITE)
			opponent = Cellstate.BLACK;
		else
			opponent = Cellstate.WHITE;
		board = b;
		helper = h;
	}

	public void setTimeLimit(int timeLimit){
		this.timeLimit = timeLimit;
	}
	
	public int playerMove() {
		GameNode root = new GameNode(board.getBoard(), board.getAdjs(),color);
		miniMax(root, System.currentTimeMillis()+timeLimit, Integer.MIN_VALUE, Integer.MAX_VALUE);
		Position p = root.getMaxPos();
		boolean movePossible = true;
		if (p == null) {
			movePossible = false;
		} else {
			Set<Position> flips = helper.evaluateMove(p, color, board);
			if (!flips.isEmpty()) {
				System.out.println("Player " + color + " placed marker at " + p.toString());
				board.placeMarker(p, color);
				board.flipMarkers(flips, color);
				return flips.size()+1;
			} else {
				movePossible = false;
			}
		}
		if (!movePossible)
			System.out.println("No move possible for player " + color);
		return 0;
	}

	private int miniMax(GameNode node, long stopTime, int alpha, int beta) {
		Board nodeBoard = new Board(node.getBoard(), node.getAdjecent());
		if (nodeBoard.gameOver() || System.currentTimeMillis() == stopTime)
			return calcScoreMinMax(nodeBoard.getBoard());
		Set<Position> possibleMoves = nodeBoard.getAdjs();
		Set<Position> flips;
		Cellstate playerTurn;
		int bestVal;
		if (node.getTurn() == color) { // Maximize
			playerTurn = opponent;
			bestVal = alpha;
		} else { // Minimize
			playerTurn = color;
			bestVal = beta;
		}
		boolean breaking = false;
		for (Position p : possibleMoves) {
			flips = helper.evaluateMove(p, node.getTurn(), nodeBoard);
			if (!flips.isEmpty()) { // if legal move for player
				Board newBoard = new Board(node.getBoard(), possibleMoves);
				newBoard.placeMarker(p, node.getTurn());
				newBoard.flipMarkers(flips, node.getTurn());
				int score;
				if (node.getTurn() == color) {
					score = miniMax(new GameNode(newBoard.getBoard(), newBoard.getAdjs(), playerTurn), stopTime, bestVal, beta);
					bestVal = Math.max(bestVal, score);
					if (bestVal <= beta)
						breaking = true;
				} else {
					score = miniMax(new GameNode(newBoard.getBoard(), newBoard.getAdjs(), playerTurn), stopTime, alpha, bestVal);
					bestVal = Math.min(bestVal, score);
					if (bestVal <= alpha)
						breaking = true;
				}
				node.addScore(p, score);
			}
			if (breaking)
				break;
		}
		if (node.getTurn() == color)
			return node.getMaxValue();
		else
			return node.getMinValue();
	}

	private int calcScoreMinMax(Cellstate[][] board) {
		int score = 0;
		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				if (board[i][j] == color)
					score++;
				else if (board[i][j] == opponent)
					score--;
			}
		}
		return score;
	}

	public Cellstate getColor() {
		return color;
	}
}
