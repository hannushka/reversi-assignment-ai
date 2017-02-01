import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,1);
		Position p3 = new Position(2,3);
		
		List<Position> l = new LinkedList<Position>();
		l.add(p1);
		
		
		Board b = new Board();
		b.start();
		System.out.println(b.toString());
		/*
		System.out.println("BLACK, 3d: " + b.legal(p3, Board.BLACK));
		System.out.println("WHITE, 3d: " + b.legal(p3, Board.WHITE));
		System.out.println("BLACK, 3e: " + b.legal(p2, Board.BLACK));
		System.out.println("BLACK, 1a: " + b.legal(p1, Board.BLACK));
		*/
		System.out.println(b.getAdjacent());
		
		b.placeMarker(p3, Board.BLACK);
		
		System.out.println(b.toString());
		System.out.println(b.getAdjacent());
	}

}
