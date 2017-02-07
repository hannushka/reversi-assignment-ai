package reversi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import reversi.Board1.Cellstate1;

public class GameNode1 {
	Cellstate1[][] board;
	Set<Position1> adjs;
	Cellstate1 playerTurn;
	Map<Position1, Integer> scores;
	
	public GameNode1(Cellstate1[][] board, Set<Position1> adjs, Cellstate1 playerTurn) {
		this.board = board;
		this.adjs = adjs;
		this.playerTurn = playerTurn;
		scores = new HashMap<Position1, Integer>();
	}

	public void addScore(Position1 p, int val) {
		scores.put(p, val);
	}
	
	public int getMaxValue() {
		int max = Integer.MIN_VALUE;
		int tmp;
		for (Position1 p : scores.keySet()) {
			tmp = scores.get(p);
			if (tmp > max) 
				max = tmp;
		}
		return max;
	}
	
	
	public int getMinValue() {
		int min = Integer.MAX_VALUE;
		int tmp;
		for (Position1 p : scores.keySet()) {
			tmp = scores.get(p);
			if (tmp < min) 
				min = tmp;
		}
		return min;
	}
	
	public Position1 getMaxPos() {
		Position1 p = null;
		int max = Integer.MIN_VALUE;
		int tmp;
		for (Position1 p2 : scores.keySet()) {
			tmp = scores.get(p2);
			if (tmp >= max) {
				max = tmp;
				p = p2;
			}
		}
		return p;
	}
}
