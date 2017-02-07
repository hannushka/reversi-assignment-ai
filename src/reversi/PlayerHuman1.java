package reversi;

import java.util.Scanner;
import java.util.Set;

import reversi.Board1.Cellstate1;

public class PlayerHuman1 implements Player1 {
	private Scanner sc;
	private Cellstate1 color;
	private Board1 board;
	private Helper1 helper;

	public PlayerHuman1(Scanner scanner, Cellstate1 c, Board1 b, Helper1 h) {
		sc = scanner;
		color = c;
		board = b;
		helper = h;
	}

	public int playerMove() {
		if (isMovePossible()) {
			Set<Position1> adjs = board.getAdjs();
			System.out.println("Your turn " + "player " + color + "! Where would you like to place a marker?");
			Position1 p = getMoveFromPlayer(adjs);
			Set<Position1> flips = helper.evaluateMove(p, color, board);
			// Placing and flipping
			if (!flips.isEmpty()) {
				System.out.println("Player " + color + " placed marker at " + p.toString());
				board.placeMarker(p, color);
				board.flipMarkers(flips, color);
				return flips.size()+1;
			} else {
				System.out.println("Move not possible");
			}
		} else {
			System.out.println("No move possible for player " + color);
		}
		return 0;
	}

	private Position1 getMoveFromPlayer(Set<Position1> adjs) {
		System.out.print("Your choice: ");
		String input = sc.next();
		System.out.println(input);
		Position1 p = board.getPositionFromCoords(input);
		while (!input.matches("[1-8][a-h]") || !board.isMoveAllowed(p) && input.length() == 0) {
			System.out.println("That move is not allowed. Try again!");
			input = sc.next();
			p = board.getPositionFromCoords(input);
		}
		System.out.println("You chose " + p.toString());
		return p;
	}

	private boolean isMovePossible() {
		Set<Position1> possibleMoves = board.getAdjs();
		Set<Position1> flips;
		for (Position1 p : possibleMoves) {
			flips = helper.evaluateMove(p, color, board);
			if (flips.size() > 0)
				return true;
		}
		return false;
	}

	public Cellstate1 getColor() {
		return color;
	}
}
