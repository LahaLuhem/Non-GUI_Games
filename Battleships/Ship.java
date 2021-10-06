                        import java.awt.Point;
                        
/**
 * It represents a Ship object to be implemented for Board class.
 * It also contains all methods related to specific manipulation of Ships in a Board
 * 
 * @author (LahLuhem) 
 * @version ( 1.0 [24/12/15] )
 */
class Ship {
  Point[] location;                                 // contains a locus of Points (points in a straight line) of the position of the Ship on a Board.
  boolean[] hits;                                   // contains a parallel to location[], but storing whether a Point has been 'hit' upon or not, in form of a "boolean" value.
  boolean isSunk;                                   // describes whether a Ship has been 'sunk' (hits[length] at each of its indices == true).
  
  /**
   * Ship Constructor
   * constructs a Ship Object with its location[] initialised to 'null' Points, hits[] to false and isSunk to false.
   * @param n A parameter
   */
  public Ship (int n) {
    location = new Point[n];
    this.hits = new boolean[n];
    isSunk = false;
  }
  
  /**
   * Method checkMarkHit
   * returns a boolean value true if a given Point ('point') equals another Point on a given Ship's location[] indicating a 'hit' and marks the 'hit' on the Ship, or false otherwise.
   * @param playerBoard A parameter
   * @param point A parameter
   * @param shipName A parameter
   * @return The return value
   */
  public boolean checkMarkHit (Board playerBoard, Point point, String shipName) throws Exception {
    for (int i = 0; i < location.length; i++) {
        if (location[i].equals (point)) {                           // indicating a 'hit'
          hits[i] = true;                                           // marks the 'hit' on the Ship
          boolean hit = true;
            for (int j = 0; j < hits.length && hit; j++) {
                if (hits[j] == false) hit = false;                  // checks whether all of the Points of the Ship have been 'hit' upon, indicating a 'sunk' Ship
            }
            if (hit) {                      
              isSunk = true;                                        // changes the boolean value of 'isSunk' from previous "false" to "true"
              tellSunk (playerBoard, shipName);
            }
          return true;
        }
    }
    return false;
  }
  
  /**
   * Method tellSunk
   * displays a message when a Ship is sunk (Ship.isSunk == true).
   * @param playerBoard A parameter
   * @param shipName A parameter
   */
  public void tellSunk (Board board, String shipName) throws Exception {
    System.out.print ('\f');
    if (location.length == 5) StartGame.printSleep ("\nThe CARRIER has been SUNK!!\n");
    else if (location.length == 4) StartGame.printSleep ("\nThe BATTLESHIP has been SUNK!!\n");
    else if (location.length == 3) {
        if (shipName.equals("sub")) StartGame.printSleep ("\nThe SUBMARINE has been SUNK!!\n");
        else StartGame.printSleep ("\nThe CRUISER has been SUNK!!\n");
    }
    else StartGame.printSleep ("\nThe DESTROYER has been SUNK!!\n");
    board.display ();
    Thread.sleep (3500);
    //Functions.monologue ();
  }
}