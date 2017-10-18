package reversi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import reversi.Board.Cellstate;

public class GameNode {
	private Cellstate[][] board;
	private Set<Position> adjs;
	private Cellstate playerTurn;
	private Map<Position, Integer> scores;
	
	public GameNode(Cellstate[][] board, Set<Position> adjs, Cellstate playerTurn) {
		this.board = board;
		this.adjs = adjs;
		this.playerTurn = playerTurn;
		scores = new HashMap<Position, Integer>();
	}

	public void addScore(Position p, int val) {
		scores.put(p, val);
	}
	
	public Cellstate[][] getBoard(){
		return board;
	}
	
	public Set<Position> getAdjecent(){
		return adjs;
	}
	
	public Cellstate getTurn(){
		return playerTurn;
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
