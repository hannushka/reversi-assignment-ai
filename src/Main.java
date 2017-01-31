import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,1);
		Position p3 = new Position(0,0);
		
		List<Position> l = new LinkedList<Position>();
		l.add(p1);
		
		
		Board b = new Board();
		b.start();
		System.out.print(b.toString());
		
	}

}
