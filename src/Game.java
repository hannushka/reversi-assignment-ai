import java.util.LinkedList;
import java.util.List;

public class Game {
	
	private Board board;
	
	public Game(Board b){
		board = b;
		b.start();
	}
	
	public List<Position> possibleMoves(int playerColour){
		List<Position> moves = new LinkedList<Position>();
		List<Position> adj = board.getAdjacent();
		for(Position pos : adj){
			if(board.legal(pos, playerColour)){
				moves.add(pos);
			}
		}
		return moves;
	}
}
