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
