import java.util.LinkedList;
import java.util.List;

public class AiPlayer {
	private Board board;
	private int colour;

	public AiPlayer(Board board, int colour) {
		this.board = board;
		this.colour = colour;
	}

	/**
	 * 
	 * @return false if player could not place a tile
	 */
	public boolean doTurn() {
		Position p = possibleFirstMove();
		if (p != null) {
			placeTile(p);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param p
	 *            - position to add a tile in
	 * @param colour
	 *            - colour of tile to be added (Have to be BLACK or WHITE, not
	 *            checked)
	 * @return true if succeeded
	 */
	public boolean placeTile(Position p) {
		return board.placeMarker(p, colour);
	}

	/**
	 * 
	 * @param playerColour
	 *            - the colour of the player who wants to play next
	 * @return a placement of a legal tile, null if no available
	 */
	public Position possibleFirstMove() {
		List<Position> adj = board.getAdjacent();
		for (Position pos : adj) {
			if (board.isLegal(pos, colour, false)) {
				return pos;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param playerColour
	 *            - the colour of the player who wants to play next
	 * @return a list of legal placement of tiles, returns empty list if no
	 *         available
	 */
	public List<Position> possibleMoves() {
		List<Position> moves = new LinkedList<Position>();
		List<Position> adj = board.getAdjacent();
		for (Position pos : adj) {
			if (board.isLegal(pos, colour, false)) {
				moves.add(pos);
			}
		}
		return moves;
	}


	public boolean fullBoard() {
		return board.full();
	}

	public int nbrTiles() {
		return board.getNbrTiles(colour);
	}
	
	public String printBoard(){
		return board.toString();
	}

}
