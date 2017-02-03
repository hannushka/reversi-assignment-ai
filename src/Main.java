

public class Main {

	public static void main(String[] args) {
		
		Position p1 = new Position(1,'a');
		Position p2 = new Position(3,'c');
		Position p3 = new Position(3,'d');
		/*
		System.out.println(p1);
		
		Board b = new Board();
		b.start();
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
		Game g = new Game();
		//System.out.println(g.possibleMoves(Board.BLACK));
		g.placeTile(p3, Board.BLACK);
		System.out.println(g.possibleMoves(Board.WHITE));
		System.out.println(g);
		g.placeTile(p2, Board.WHITE);
		//g.placeTile(p3, Board.BLACK);
		System.out.println(g);
	}

}
