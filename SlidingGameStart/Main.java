import java.util.List;
/**
 *
 * @author Sjaak Smetsers
 */
public class Main {
    public static void main(String[] args) {
        int [] game = {1,2,3, 9,5,6, 4,7,8};

        SlidingGame s = new SlidingGame (game);
        System.out.println(s);
        Solver solver = new Solver(s);
        System.out.println(solver.solve());
    }
}