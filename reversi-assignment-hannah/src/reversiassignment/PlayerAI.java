package reversiassignment;

import java.util.Set;

import reversiassignment.Board.Cellstate;

public class PlayerAI implements Player {
	private Cellstate color;
	private Board board;
	private Helper helper;

	public PlayerAI(Cellstate c, Board b, Helper h) {
		this.color = c;
		board = b;
		helper = h;
	}
	
	private int calcScore(Cellstate[][] board) {
		int score = 0;
		for (int i = 0 ; i < Board.SIZE ; i++) {
			for (int j = 0 ; j < Board.SIZE ; j++) {
				if (board[i][j] == color)
					score++;
				else if (board[i][j] != Cellstate.EMPTY)
					score--;
			}
		}
		return score;
	}
	
	private int miniMax(GameNode node, int depth) {
		Board nodeBoard = node.board;
		if (nodeBoard.gameOver() || depth == 0)
			return calcScore(nodeBoard.getBoard());
		Set<Position> possibleMoves = nodeBoard.getAdjs();
		Set<Position> flips;
		for (Position p : possibleMoves) {
			flips = helper.evaluateMove(p, node.playerTurn, node.board);
			if (!flips.isEmpty()) {
				Board newBoard = new Board(nodeBoard.getBoard(), possibleMoves);
				newBoard.placeMarker(p, node.playerTurn);
				newBoard.flipMarkers(flips, node.playerTurn);
				Cellstate playerTurn;
				if (node.playerTurn == Cellstate.WHITE)
					playerTurn = Cellstate.BLACK;
				else
					playerTurn = Cellstate.WHITE;
				node.scores.put(p, miniMax(new GameNode(newBoard, playerTurn), depth-1));
			}
		}
		if (node.playerTurn == color) {
			int max = Integer.MIN_VALUE;
			int tmp;
			for (Position p : node.scores.keySet()) {
				tmp = node.scores.get(p);
				if (tmp > max) 
					max = tmp;
			}
			return max;
		} else {
			int min = Integer.MAX_VALUE;
			int tmp;
			for (Position p : node.scores.keySet()) {
				tmp = node.scores.get(p);
				if (tmp < min) 
					min = tmp;
			}
			return min;
		}
	}

	public void playerMove() {
		GameNode root = new GameNode(board, color);
		miniMax(root, 7);
		Position p = null;
		int max = Integer.MIN_VALUE;
		int tmp;
		for (Position p2 : root.scores.keySet()) {
			tmp = root.scores.get(p2);
			if (tmp >= max) {
				max = tmp;
				p = p2;
			}
		}
		if (root.scores.keySet().size() > 0) {
			Set<Position> flips = helper.evaluateMove(p, color, board);
			// Placing and flipping
			if (!flips.isEmpty()) {
				System.out.println("Player " + color + " placed marker at " + p.toString());
				board.placeMarker(p, color);
				board.flipMarkers(flips, color);
			}
		}
	}

	public Cellstate getColor() {
		return color;
	}

}
