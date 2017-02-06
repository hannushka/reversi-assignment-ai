package reversiassignment;

import java.util.HashMap;
import java.util.Map;

import reversiassignment.Board.Cellstate;

public class GameNode {
	Board board;
	Cellstate playerTurn;
	Map<Position, Integer> scores;
	
	public GameNode(Board b, Cellstate playerTurn) {
		board = b;
		this.playerTurn = playerTurn;
		scores = new HashMap<Position, Integer>();
	}
	
	public void addScore(Position p, int val) {
		scores.put(p, val);
	}
	
	public int getMaxValue() {
		int max = Integer.MIN_VALUE;
		int tmp;
		for (Position p : scores.keySet()) {
			tmp = scores.get(p);
			if (tmp > max) 
				max = tmp;
		}
		return max;
	}
	
	
	public int getMinValue() {
		int min = Integer.MAX_VALUE;
		int tmp;
		for (Position p : scores.keySet()) {
			tmp = scores.get(p);
			if (tmp < min) 
				min = tmp;
		}
		return min;
	}
	
	public Position getMaxPos() {
		Position p = null;
		int max = Integer.MIN_VALUE;
		int tmp;
		for (Position p2 : scores.keySet()) {
			tmp = scores.get(p2);
			if (tmp >= max) {
				max = tmp;
				p = p2;
			}
		}
		return p;
	}
}
