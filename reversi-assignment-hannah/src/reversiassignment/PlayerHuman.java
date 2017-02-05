package reversiassignment;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import reversiassignment.Board.Cellstate;

public class PlayerHuman implements Player {
	private Scanner sc;
	private Cellstate color;
	private Board board;
	private Helper helper;

	public PlayerHuman(Scanner scanner, Cellstate c, Board b, Helper h) {
		sc = scanner;
		color = c;
		board = b;
		helper = h;
	}

	public void playerMove() {
		Set<Position> adjs = board.getAdjs();
		System.out.println("Your turn " + "player " + color + "! Where would you like to place a marker?");
		Position p = getMoveFromPlayer(adjs);
		Set<Position> flips = helper.evaluateMove(p, color, board);
		// Placing and flipping
		if (!flips.isEmpty()) {
			System.out.println("Player " + color + " placed marker at " + p.toString());
			board.placeMarker(p, color);
			board.flipMarkers(flips, color);
		}
	}

	private Position getMoveFromPlayer(Set<Position> adjs) {
		System.out.print("Your choice: ");
		String input = sc.next();
		System.out.println(input);
		Position p = board.getPositionFromCoords(input);
		while (!input.matches("[1-8][a-h]") || !board.isMoveAllowed(p)) {
			System.out.println("That move is not allowed. Try again!");
			input = sc.next();
			p = board.getPositionFromCoords(input);
		}
		System.out.println("You chose " + p.toString());
		return p;
	}

	public Cellstate getColor() {
		return color;
	}
}
