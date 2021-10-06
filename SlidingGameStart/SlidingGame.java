import java.util.ArrayList;
import java.util.Collection;
import java.awt.Point;

/**
 * 
 * @author LahaLuhem (s1018562)
 */
public class SlidingGame implements Configuration {

    public static final int N = 3, SIZE = N * N, HOLE = SIZE;
    /**
     * The board is represented by a 2-dimensional array; the position of the
     * hole is kept in 2 variables holeX and holeY
     */
    private int[][] board;
    private int holeX, holeY;
    private SlidingGame predecessor;
    private int manDist;
    
    /**
     * Default constructor specifically for making the base initializations
     */
    SlidingGame () {
      int[] startSeq = new int[SIZE];
      for (int i = 1; i <= startSeq.length; i++) startSeq[i-1] = i;
      SlidingGame startConfig = new SlidingGame (startSeq);
      board = startConfig.board;
      holeX = startConfig.holeX;
      holeY = startConfig.holeY;
      predecessor = null;
    }
    
    /**
     * A constructor that initializes the board with the specified array
     *
     * @param index: a one dimensional array containing the initial board. The
     * elements of index are stored row-wise.
     */
    public SlidingGame(int[] index) {
        board = new int[N][N];

        assert index.length == N * N : "Length of specified board incorrect";

        for (int p = 0; p < index.length; p++) {
            board[p % N][p / N] = index[p];
            if (index[p] == HOLE) {
                holeX = p % N;
                holeY = p / N;
            }
        }
        manDist = -1;        //manDist = calcManhattanDist(); is not possible as the object isn't fully made
        predecessor = null;  //therefore the only option is setting it later
    }

    /**
     * Converts a board into a printable representation. The hole is displayed
     * as a space
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int puzzle = board[col][row];
                buf.append(puzzle == HOLE ? "▢ " : puzzle + " ");
            }
            buf.append("\n");
        }
        return buf.toString();
    }
    
    /**
     * To obtain the successors for a specific configuration
     *
     * @return a collection of configurations containing the successors
     */
    @Override
    public Collection<Configuration> successors () {
      if (manDist == -1) setManDist ();
      ArrayList<Configuration> list = new ArrayList<>();
      Direction[] compass = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
      for (Direction dir : compass) {
        int newHoleX = holeX, newHoleY = holeY;
        newHoleX += dir.getDX();
        newHoleY += dir.getDY();
        
          if (newHoleX < 0 || newHoleX > N-1 || newHoleY < 0 || newHoleY > N-1) continue;
        
        SlidingGame successor = cloneUnaliased();
        int exchangeTile = successor.board[newHoleX][newHoleY];
        successor.board[newHoleX][newHoleY] = successor.board[holeX][holeY]; //swapping
        successor.board[holeX][holeY] = exchangeTile; //swapping
        
        successor.holeX = newHoleX;
        successor.holeY = newHoleY;     //realigning hole positions
        successor.setManDist ();
        successor.predecessor = this;
        list.add (successor);
      } 
      return list;
    }

    /**
     *
     * @param configObj
     * @return
     */
    @Override
    public boolean equals (Object configObj) {
      SlidingGame sg2 = (SlidingGame) configObj;
      boolean flag = true;
      for (int i = 0; flag && i < N; i++)
          for (int j = 0; j < N; j++)
              if (board[j][i] != sg2.board[j][i]) flag = false;
      return flag && holeX == sg2.holeX && holeY == sg2.holeY;
    }
    
    /**
     * For marking final / solution configurations.
     * 
     * @return true if a this is a solution, false otherwise
     */
    @Override
    public boolean isSolution() {
      SlidingGame goal = new SlidingGame();
      return equals (goal);
    }

    @Override
     public int compareTo (Configuration config) {
      SlidingGame sg2 = (SlidingGame) config;
      return manDist-sg2.manDist;
    }
    
    /**
     * To obtain the parent of the current configuration, i.e.
     * the configuration which had this as one of its successors
     *
     * @return a reference to the parent
     */
    @Override
    public Configuration parent() {
      return predecessor;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int hashCode () {
      int hash = 0;
      for (int x = N-1; x >= 0; x--) {
          for (int y = N-1; y >= 0; y--) {
            hash = 31*hash + board[y][x];
          }
      }
      return hash;
    }
    
    /**
     * Makes an unaliased copy of the object
     * @return the reference to the copy
     */
    private SlidingGame cloneUnaliased () {
      int[] newSeq = new int[SIZE];
      for (int i = 0, index = 0; i < N; i++)
          for (int j = 0; j < N; j++, index++) newSeq[index] = board[j][i];
      return new SlidingGame (newSeq);
    }
    
    private void setManDist () {
      manDist = calcManhattanDist ();
    }
    
    private int calcManhattanDist () {
      int totDist = 0;
      for (Point point = new Point(); point.y < N; point.x++) {
        if (board[point.x][point.y] != SIZE) {
          totDist += singleElemDist (point);
        }
        if (N-1-point.x == 0) {
          point.y++;
          point.x = -1;
        }
      }
      return totDist;
    }
    
    private int singleElemDist (Point tilePos) {
      Point homePos = (new SlidingGame ()).getRowCol(board[tilePos.x][tilePos.y]);
      return Math.abs(tilePos.x-homePos.x) + Math.abs(tilePos.y-homePos.y);
    }
      
    private Point getRowCol (int tileNo) {
      for (int x = 0, y = 0; y < N; x++) {
          if (board[y][x] == tileNo) return new Point (y, x);
          if (N-1-x == 0) {
            y++;
            x = -1;
          }
      }
      return null;
    }
}