import java.util.LinkedList;
import java.util.List;

public class Game {
	
	private Board board;
	
	public Game(Board b){
		board = b;
		b.start();
	}
	
	/**
	 * 
	 * @param playerColour - the colour of the player who wants to play next
	 * @return a list of legal placement of tiles
	 */
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
