import java.util.LinkedList;
import java.util.List;

public class Game {
	
	private Board board;
	private boolean turnBlack;
	
	public Game(){
		board = new Board();
		board.start();
		turnBlack = true;
	}
	
	/**
	 * 
	 * @param playerColour - the colour of the player who wants to play next
	 * @return a list of legal placement of tiles, returns empty list if no available
	 */
	public List<Position> possibleMoves(int playerColour){
		List<Position> moves = new LinkedList<Position>();
		List<Position> adj = board.getAdjacent();
		for(Position pos : adj){
			if(board.isLegal(pos, playerColour, false)){
				moves.add(pos);
			}
		}
		return moves;
	}
	
	/**
	 * 
	 * @param playerColour - the colour of the player who wants to play next
	 * @return a placement of a legal tile, null if no available
	 */
	public Position possibleFirstMove(int playerColour){
		List<Position> adj = board.getAdjacent();
		for(Position pos : adj){
			if(board.isLegal(pos, playerColour, false)){
				return pos;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param p - position to add a tile in
	 * @param colour - colour of tile to be added (Have to be BLACK or WHITE, not checked)
	 * @return true if succeeded
	 */
	public boolean placeTile(Position p, int colour){
		if(colour == Board.BLACK){
			turnBlack = false;
		} else { //colour is WHITE
			turnBlack = true;
		}
		return board.placeMarker(p, colour);
	}
	
	/**
	 * Returns the current game as a string. Contains how many placed tiles 
	 * each colour has and whose turn it is. 
	 * 
	 * @return a string with the current game.
	 */
	public String toString(){
		StringBuilder game = new StringBuilder(); 
		game.append("Black:\t" + board.getNbrTiles(Board.BLACK) + "\n");
		if(turnBlack) game.append("My turn\n");
		game.append(board.toString() + "\n");
		game.append("White:\t" + board.getNbrTiles(Board.WHITE) + "\n");
		if(!turnBlack) game.append("My turn\n");
		return game.toString();
	}
}
