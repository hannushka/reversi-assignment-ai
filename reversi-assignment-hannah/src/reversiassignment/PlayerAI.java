package reversiassignment;

import java.util.Set;

import reversiassignment.Board.Cellstate;

public class PlayerAI implements Player {
	private Cellstate color;
	private Cellstate opponent;
	private Board board;
	private Helper helper;

	public PlayerAI(Cellstate c, Board b, Helper h) {
		this.color = c;
		if (c == Cellstate.WHITE)
			opponent = Cellstate.BLACK;
		else
			opponent = Cellstate.WHITE;
		board = b;
		helper = h;
	}

	public int playerMove() {
		GameNode root = new GameNode(board, color);
		miniMax(root, 10, Integer.MIN_VALUE, Integer.MAX_VALUE);
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

	private int miniMax(GameNode node, int depth, int alpha, int beta) {
		Board nodeBoard = node.board;
		if (nodeBoard.gameOver() || depth == 0)
			return calcScoreMinMax(nodeBoard.getBoard());
		Set<Position> possibleMoves = nodeBoard.getAdjs();
		Set<Position> flips;
		Cellstate playerTurn;
		int bestVal;
		if (node.playerTurn == color) { // Maximize
			playerTurn = opponent;
			bestVal = alpha;
		} else { // Minimize
			playerTurn = color;
			bestVal = beta;
		}
		boolean breaking = false;
		for (Position p : possibleMoves) {
			flips = helper.evaluateMove(p, node.playerTurn, node.board);
			if (!flips.isEmpty()) { // if legal move for player
				Board newBoard = new Board(nodeBoard.getBoard(), possibleMoves);
				newBoard.placeMarker(p, node.playerTurn);
				newBoard.flipMarkers(flips, node.playerTurn);
				int score;
				if (node.playerTurn == color) {
					score = miniMax(new GameNode(newBoard, playerTurn), depth - 1, bestVal, beta);
					bestVal = Math.max(bestVal, score);
					if (bestVal <= beta)
						breaking = true;
				} else {
					score = miniMax(new GameNode(newBoard, playerTurn), depth - 1, alpha, bestVal);
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

	private int calcScoreMinMax(Cellstate[][] board) {
		int score = 0;
		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				if (board[i][j] == color)
					score++;
				else if (board[i][j] != Cellstate.EMPTY)
					score--;
			}
		}
		return score;
	}
}
