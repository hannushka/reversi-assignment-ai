

public class Main {

	public static void main(String[] args) {
		
		Position p1 = new Position(1,'a');
		Position p2 = new Position(3,'c');
		Position p3 = new Position(3,'d');
		
		
		Board b = new Board();
		
		b.start();/*
		System.out.println(b.toString());
		
		System.out.println("BLACK, 3d: " + b.legal(p3, Board.BLACK));
		System.out.println("WHITE, 3d: " + b.legal(p3, Board.WHITE));
		System.out.println("BLACK, 3e: " + b.legal(p2, Board.BLACK));
		System.out.println("BLACK, 1a: " + b.legal(p1, Board.BLACK));
		
		System.out.println(b.getAdjacent());
		
		b.placeMarker(p3, Board.BLACK);
		
		System.out.println(b.toString());
		System.out.println(b.getAdjacent());
		*/
		
		AiPlayer white = new AiPlayer(b, Board.WHITE);
		AiPlayer black = new AiPlayer(b, Board.BLACK);
		Game g = new Game(white, black);
		
		g.play();
		
	}

}
