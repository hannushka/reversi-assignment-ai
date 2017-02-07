package reversi;


import java.util.Set;

import reversi.Board1.Cellstate1;

public class PlayerAI1 implements Player1 {
	private Cellstate1 color;
	private Cellstate1 opponent;
	private Board1 board;
	private Helper1 helper;
	private int timeLimit;

	public PlayerAI1(Cellstate1 c, Board1 b, Helper1 h, int timeLimit) {
		this.color = c;
		this.timeLimit = timeLimit;
		if (c == Cellstate1.WHITE)
			opponent = Cellstate1.BLACK;
		else
			opponent = Cellstate1.WHITE;
		board = b;
		helper = h;
	}

	public int playerMove() {
		GameNode1 root = new GameNode1(board.getBoard(), board.getAdjs(),color);
		miniMax(root, System.currentTimeMillis()+timeLimit, Integer.MIN_VALUE, Integer.MAX_VALUE);
		Position1 p = root.getMaxPos();
		boolean movePossible = true;
		if (p == null) {
			movePossible = false;
		} else {
			Set<Position1> flips = helper.evaluateMove(p, color, board);
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

	private int miniMax(GameNode1 node, long stopTime, int alpha, int beta) {
		Board1 nodeBoard = new Board1(node.board, node.adjs);
		if (nodeBoard.gameOver() || System.currentTimeMillis() == stopTime)
			return calcScoreMinMax(nodeBoard.getBoard());
		Set<Position1> possibleMoves = nodeBoard.getAdjs();
		Set<Position1> flips;
		Cellstate1 playerTurn;
		int bestVal;
		if (node.playerTurn == color) { // Maximize
			playerTurn = opponent;
			bestVal = alpha;
		} else { // Minimize
			playerTurn = color;
			bestVal = beta;
		}
		boolean breaking = false;
		for (Position1 p : possibleMoves) {
			flips = helper.evaluateMove(p, node.playerTurn, nodeBoard);
			if (!flips.isEmpty()) { // if legal move for player
				Board1 newBoard = new Board1(node.board, possibleMoves);
				newBoard.placeMarker(p, node.playerTurn);
				newBoard.flipMarkers(flips, node.playerTurn);
				int score;
				if (node.playerTurn == color) {
					score = miniMax(new GameNode1(newBoard.getBoard(), newBoard.getAdjs(), playerTurn), stopTime, bestVal, beta);
					bestVal = Math.max(bestVal, score);
					if (bestVal <= beta)
						breaking = true;
				} else {
					score = miniMax(new GameNode1(newBoard.getBoard(), newBoard.getAdjs(), playerTurn), stopTime, alpha, bestVal);
					bestVal = Math.min(bestVal, score);
					if (bestVal <= alpha)
						breaking = true;
				}
				node.addScore(p, score);
			}
			if (breaking)
				break;
		}
		if (node.playerTurn == color)
			return node.getMaxValue();
		else
			return node.getMinValue();
	}

	private int calcScoreMinMax(Cellstate1[][] board) {
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

	public Cellstate1 getColor() {
		return color;
	}
}
