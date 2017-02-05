package reversiassignment;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import reversiassignment.Board.Cellstate;

public class PlayerHuman implements Player {
	private Scanner sc;
	private Cellstate color;
	private Board board;
	private int score;
	private Helper helper;

	public PlayerHuman(Scanner scanner, Cellstate c, Board b, int s, Helper h) {
		sc = scanner;
		color = c;
		board = b;
		score = s;
		helper = h;
	}

	public int playerMove() {
		Set<Position> adjs = board.getAdjs();
		Iterator<Position> itr1 = adjs.iterator();
		System.out.println("Your turn! Where would you like to place a marker? Here are your options:");
		StringBuilder sb = new StringBuilder();
		Position p;
		while (itr1.hasNext()) {
			p = itr1.next();
			sb.append(p.toString());
			sb.append(" ");
		}
		sb.append("\n");
		System.out.println(sb.toString());
		p = getMoveFromPlayer(adjs);
		Set<Position> flips = helper.evaluateMove(p, color, board);
		// Placing and flipping
		if (!flips.isEmpty()) {
			System.out.println("Player " + color + " placed marker at " + p.toString());
			board.placeMarker(p, color);
			board.flipMarkers(flips, color);
		}
		return flips.size();
	}

	private Position getMoveFromPlayer(Set<Position> adjs) {
		System.out.print("Your choice: ");
		String input = sc.next();
		System.out.println(input);
		Position p = board.getPositionFromCoords(input);
		while(!input.matches("[1-8][a-h]") || !board.isMoveAllowed(p)){
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

	public void modifyScore(int points) {
		score += points;
	}

	public int getScore() {
		return score;
	}

}
