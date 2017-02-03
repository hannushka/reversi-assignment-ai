package reversiassignment;

import java.util.Iterator;
import java.util.Set;

import reversiassignment.Board.Cellstate;

public class Game {
	private Board b;
	private Helper helper;
	private int scoreW, scoreB;

	public Game() {
		b = new Board();
		helper = new Helper(b);
		scoreW = 2;
		scoreB = 2;
	}

	public void play() {
		System.out.print(b.toString());
		Cellstate player = Cellstate.BLACK;
		while (!b.gameOver()) {
			//Player makes move
			playerMove(player);
			//Print new board
				System.out.print(b.toString());
			//Next player
			if (player == Cellstate.BLACK)
				player = Cellstate.WHITE;
			else
				player = Cellstate.BLACK;
		}
		System.out.println("Score: B-" + scoreB + " W-" + scoreW);
	}

	private void playerMove(Cellstate player) {
		// Player is choosing position for next move
		Iterator<Position> itr1 = b.getAdjs().iterator();
		Set<Position> flips;
		Position p;
		do {
			p = itr1.next();
			flips = helper.evaluateMove(p, player);
		} while (flips.isEmpty() && itr1.hasNext());
		// Placing and flipping
		if (!flips.isEmpty()) {
			System.out.println("Player " + player + " placed marker at " + p.toString());
			b.placeMarker(p, player);
			b.flipMarkers(flips, player);
			calcScore(flips.size(),player);
			
		}
	}
	
	private void calcScore(int nbrOfFlips, Cellstate player) {
		if (player == Cellstate.BLACK) { 
			scoreB += 1 + nbrOfFlips;
			scoreW -= nbrOfFlips;
		} else {
			scoreW += 1 + nbrOfFlips;
			scoreB -= nbrOfFlips;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new Game().play();
	}
}