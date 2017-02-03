
public class AiPlayer {
	private Game game;
	private int colour;
	
	public AiPlayer(Game game, int colour){
		this.game = game;
		this.colour = colour;
	}
	
	
	public void doTurn(){
		game.placeTile(game.possibleFirstMove(colour), colour);
	}
	
}
