                                 import java.awt.Point;
                                 import java.util.Scanner;
                                 
/**
 * It encodes for a single turn that is playable by the Computer (AI).
 * The method 'AIturn' is the actual flag-bearer for this class as it diverts the flow of conditioned execution.
 * 
 * @author (LahaLuhem) 
 * @version ( 1.0 [24/12/15] )
 */
public class AI {
  static boolean hitting[] = new boolean[5];        //keeps a record of the Ships that are being hit
  static Point lastHit = new Point ();              //keeps the track of the last Point of a 'hit' scored by the AI
  static Point[] adjSearch = new Point [4];         //contains Points adjecent for obtaining a 2nd 'hit' on a Ship, in effect, determining its direction

  /**
   * Method AImakeShips
   * constructs legal Ships for the COMPUTER (AI)'s Board.
   * @param commBoard A parameter
   */
  public static void AImakeShips (Board commBoard) {
    for (boolean isVal = false; isVal == false; ) {
      int x = Functions.randomInt (0, 9);
      int y = Functions.randomInt (0, 9);
      int randDirect = Functions.randomInt (0, 50);
      Point point = new Point (x, y);
        if (randDirect%2 == 0) {
          Functions.buildShip (commBoard, "carrier", point, 0);
          isVal = AIshipVal (commBoard, point, 0);
        }
        else {
          Functions.buildShip (commBoard, "carrier", point, 1);
          isVal = AIshipVal (commBoard, point, 1);
        } 
    }
    
    for (boolean isVal = false; isVal == false; ) {
      int x = Functions.randomInt (0, 9);
      int y = Functions.randomInt (0, 9);
      int randDirect = Functions.randomInt (0, 50);
      Point point = new Point (x, y);
        if (randDirect%2 == 0) {
          Functions.buildShip (commBoard, "battleShip", point, 0);
          isVal = AIshipVal (commBoard, point, 0);
        }
        else {
          Functions.buildShip (commBoard, "battleShip", point, 1);
          isVal = AIshipVal (commBoard, point, 1);
        } 
    }
    
    for (boolean isVal = false; isVal == false; ) {
      int x = Functions.randomInt (0, 9);
      int y = Functions.randomInt (0, 9);
      int randDirect = Functions.randomInt (0, 50);
      Point point = new Point (x, y);
        if (randDirect%2 == 0) {
          Functions.buildShip (commBoard, "cruiser", point, 0);
          isVal = AIshipVal (commBoard, point, 0);
        }
        else {
          Functions.buildShip (commBoard, "cruiser", point, 1);
          isVal = AIshipVal (commBoard, point, 1);
        } 
    }
    
    for (boolean isVal = false; isVal == false; ) {
      int x = Functions.randomInt (0, 9);
      int y = Functions.randomInt (0, 9);
      int randDirect = Functions.randomInt (0, 50);
      Point point = new Point (x, y);
        if (randDirect%2 == 0) {
          Functions.buildShip (commBoard, "submarine", point, 0);
          isVal = AIshipVal (commBoard, point, 0);
        }
        else {
          Functions.buildShip (commBoard, "submarine", point, 1);
          isVal = AIshipVal (commBoard, point, 1);
        } 
    }
    
    for (boolean isVal = false; isVal == false; ) {
      int x = Functions.randomInt (0, 9);
      int y = Functions.randomInt (0, 9);
      int randDirect = Functions.randomInt (0, 50);
      Point point = new Point (x, y);
        if (randDirect%2 == 0) {
          Functions.buildShip (commBoard, "destroyer", point, 0);
          isVal = AIshipVal (commBoard, point, 0);
        }
        else {
          Functions.buildShip (commBoard, "destroyer", point, 1);
          isVal = AIshipVal (commBoard, point, 1);
        } 
    }
  }
  
  /**
   * Method AIshipVal
   * returns true if a given Ship is leagally placeable on a given Board.
   * @param commBoard A parameter
   * @param point A parameter
   * @param direct A parameter
   * @return The return value
   */
  public static boolean AIshipVal (Board commBoard, Point point, int direct) {
    int isVal = 0;
    if ( (direct == 0) && ((commBoard.carrier.location[1] == null) || (commBoard.battleShip.location[1] == null) || (commBoard.cruiser.location[1] == null) || (commBoard.submarine.location[1] == null) || (commBoard.destroyer.location[1] == null)) ) {
        if (commBoard.battleShip.location[1] == null) {
            if ( (point.y < 0) || (point.y + 4 > 9) ) isVal = -1;
        }
        else if (commBoard.cruiser.location[1] == null) {
            if ( (point.y < 0) || (point.y + 3 > 9) ) isVal = -1;
        }
        else if (commBoard.submarine.location[1] == null) {
            if ( (point.y < 0) || (point.y + 2 > 9) ) isVal = -1;
        }
        else if (commBoard.destroyer.location[1] == null) {
            if ( (point.y < 0) || (point.y + 2 > 9) ) isVal = -1;
        }
        else {
            if ( (point.y < 0) || (point.y + 1 > 9) ) isVal = -1;
        }
    }
    else if ( (direct == 1) || ((commBoard.carrier.location[1] == null) || (commBoard.battleShip.location[1] == null) || (commBoard.cruiser.location[1] == null) || (commBoard.submarine.location[1] == null) || (commBoard.destroyer.location[1] == null)) ) {
        if (commBoard.battleShip.location[1] == null) {
            if ( (point.x < 0) || (point.x + 4 > 9) ) isVal = -1;
        }
        else if (commBoard.cruiser.location[1] == null) {
            if ( (point.x < 0) || (point.x + 3 > 9) ) isVal = -1;
        }
        else if (commBoard.submarine.location[1] == null) {
            if ( (point.x < 0) || (point.x + 2 > 9) ) isVal = -1;
        }
        else if (commBoard.destroyer.location[1] == null) {
            if ( (point.x < 0) || (point.x + 2 > 9) ) isVal = -1;
        }
        else {
            if ( (point.x < 0) || (point.x + 1 > 9) ) isVal = -1;
        }
    }
    if (isVal == -1) return false;
    
    boolean flag = true;
    if ( (commBoard.carrier.location[2] != null) && (commBoard.battleShip.location[2] != null) ) {
        if (commBoard.cruiser.location[2] == null) {
             for (int index = 0; index < 5; index++) {
                 for (int i = 0; i < 4; i++) {
                     if (commBoard.carrier.location[index].equals (commBoard.battleShip.location[i])) flag = false;
                 }
             }
        }
        else if ((commBoard.submarine.location[2] == null) && (flag)) {
             for (int index = 0; index < 5; index++) {
                 for (int i = 0; i < 3; i++) {
                     if (commBoard.carrier.location[index].equals (commBoard.cruiser.location[i])) flag = false;
                 }
             }
             for (int index = 0; index < 4; index++) {
                 for (int i = 0; i < 3; i++) {
                     if (commBoard.battleShip.location[index].equals (commBoard.cruiser.location[i])) flag = false;
                 }
             }
        }
        else if ((commBoard.destroyer.location[1] == null) && (flag)) {
             for (int index = 0; index < 5; index++) {
                 for (int i = 0; i < 3; i++) {
                     if (commBoard.carrier.location[index].equals (commBoard.submarine.location[i])) flag = false;
                 }
             }
             for (int index = 0; index < 4; index++) {
                 for (int i = 0; i < 3; i++) {
                     if (commBoard.battleShip.location[index].equals (commBoard.submarine.location[i])) flag = false;
                 }
             }
             for (int index = 0; index < 3; index++) {
                 for (int i = 0; i < 3; i++) {
                     if (commBoard.cruiser.location[index].equals (commBoard.submarine.location[i])) flag = false;
                 }
             }
        }
        else {
             for (int index = 0; index < 5; index++) {
                 for (int i = 0; i < 2; i++) {
                     if (commBoard.carrier.location[index].equals (commBoard.destroyer.location[i])) flag = false;
                 }
             }
             for (int index = 0; index < 4; index++) {
                 for (int i = 0; i < 2; i++) {
                     if (commBoard.battleShip.location[index].equals (commBoard.destroyer.location[i])) flag = false;
                 }
             }
             for (int index = 0; index < 3; index++) {
                 for (int i = 0; i < 2; i++) {
                     if (commBoard.cruiser.location[index].equals (commBoard.destroyer.location[i])) flag = false;
                 }
             }
             for (int index = 0; index < 3; index++) {
                 for (int i = 0; i < 2; i++) {
                     if (commBoard.submarine.location[index].equals (commBoard.destroyer.location[i])) flag = false;
                 }
             }
        }
    }
    return flag;
  }
  
  /**
   * Method AIvalidate
   * returns true if a guess is legal for a Board, or false otherwise.
   * @param playerBoard A parameter
   * @param guess A parameter
   * @return The return value
   */
  public static boolean AIvalidate (Board playerBoard, Point guess) {
    if ( (guess.x > 9) || (guess.x < 0) || (guess.y > 9) || (guess.y < 0) ) return false;
    if ( (playerBoard.board[guess.y][guess.x] == 'X') || (playerBoard.board[guess.y][guess.x] == 'O') ) return false;
    return true;
  }
  
  /**
   * Method checkRepeated
   * returns true if the given Point[] doesn't contain two or more equal Points, and false otherwise.
   * @param locus A parameter
   * @return The return value
   */
  public static boolean checkRepeated (Point[] locus) {
    for (int index = 0; index < locus.length; index++) {
        for (int i = index + 1; i < locus.length; i++) {
            if ( (locus[i] != null) && (locus[index] != null) ) {
                if (locus[i].equals (locus[index])) {
                  return false;
                }
            }
        }
    }
    return true;
  }
  
  /**
   * Method checkAndMark
   * determines if a given Point is a 'hit' or a 'miss' on a given 'playerBoard', and also marks it on the Board accordingly. 
   * @param playerBoard A parameter
   * @param guessBoard A parameter
   * @param guess A parameter
   */
  public static void checkAndMark (Board playerBoard, Board guessBoard, Point guess, AI artInt) throws Exception {
    if (playerBoard.carrier.checkMarkHit (playerBoard, guess, "")) {
      StartGame.plotCord (playerBoard, guessBoard, guess, true);
      StartGame.display (guessBoard, playerBoard);
      artInt.hitting[0] = true;
      artInt.lastHit.x = guess.x;
      artInt.lastHit.y = guess.y;
      System.out.println ("\nThe Computer has got a HIT on your CARRIER after firing at " + Functions.toCord (guess) + "!");
      Thread.sleep (3000);
    }
    else if (playerBoard.battleShip.checkMarkHit (playerBoard, guess, "")) {
      StartGame.plotCord (playerBoard, guessBoard, guess, true);
      StartGame.display (guessBoard, playerBoard);
      artInt.hitting[1] = true;
      artInt.lastHit.x = guess.x;
      artInt.lastHit.y = guess.y;
      System.out.println ("\nThe Computer has got a HIT on your CARRIER after firing at " + Functions.toCord (guess) + '!');
      Thread.sleep (3000);
    }
    else if (playerBoard.cruiser.checkMarkHit (playerBoard, guess, "")) {
      StartGame.plotCord (playerBoard, guessBoard, guess, true);
      StartGame.display (guessBoard, playerBoard);
      artInt.hitting[2] = true;
      artInt.lastHit.x = guess.x;
      artInt.lastHit.y = guess.y;
      System.out.println ("\nThe Computer has got a HIT on your CRUISER after firing at " + Functions.toCord (guess) + '!');
      Thread.sleep (3000);
    }
    else if (playerBoard.submarine.checkMarkHit (playerBoard, guess, "sub")) {
      StartGame.plotCord (playerBoard, guessBoard, guess, true);
      StartGame.display (guessBoard, playerBoard);
      artInt.hitting[3] = true;
      artInt.lastHit.x = guess.x;
      artInt.lastHit.y = guess.y;
      System.out.println ("\nThe Computer has got a HIT on your CARRIER after firing at " + Functions.toCord (guess) + '!');
      Thread.sleep (3000);
    }
    else if (playerBoard.destroyer.checkMarkHit (playerBoard, guess, "")) {
      StartGame.plotCord (playerBoard, guessBoard, guess, true);
      StartGame.display (guessBoard, playerBoard);
      artInt.hitting[4] = true;
      artInt.lastHit.x = guess.x;
      artInt.lastHit.y = guess.y;
      System.out.println ("\nThe Computer has got a HIT on your CARRIER after firing at " + Functions.toCord (guess) + '!');
      Thread.sleep (3000);
    }
    else {
      StartGame.plotCord (playerBoard, guessBoard, guess, false);
      StartGame.display (guessBoard, playerBoard);
      System.out.println ("\nThe Computer has MISSED after firing at " + Functions.toCord (guess));
      Thread.sleep (2700);
    }
  }
  
  /**
   * Method hitShips
   * gets activated after the COMPUTER (AI) scores a 'hit' on a Ship, and is used to continue 'hitting' on that Ship.
   * @param playerBoard A parameter
   * @param guessBoard A parameter
   * @param lastHit A parameter
   */
  public static void hitShips (Board playerBoard, Board guessBoard, Point lastHit, AI artInt) throws Exception {
    int[] noOfHits = StartGame.hitHist (playerBoard);
    boolean isSearchNeed = true;
    if ( (noOfHits[4] == 1) && (artInt.hitting[4]) ) {
      hitAdj (playerBoard, guessBoard, playerBoard.destroyer, artInt);
      isSearchNeed = false;
    }
    else if ( (noOfHits[3] == 2) && (artInt.hitting[3]) ) {
      hitAdj (playerBoard, guessBoard, playerBoard.submarine, artInt);
      isSearchNeed = false;
    }
    else if ( (noOfHits[2] == 2) && (artInt.hitting[2]) ) {
      hitAdj (playerBoard, guessBoard, playerBoard.cruiser, artInt);
      isSearchNeed = false;
    }
    else if ( (noOfHits[1] >= 2) && (artInt.hitting[1]) ) {
      hitAdj (playerBoard, guessBoard, playerBoard.battleShip, artInt);
      isSearchNeed = false;
    }
    else if ( (noOfHits[0] >= 2) && (artInt.hitting[0]) ) {
      hitAdj (playerBoard, guessBoard, playerBoard.carrier, artInt);
      isSearchNeed = false;
    }
    else {
      Functions.nullify (artInt.adjSearch);
    }
    
    boolean flag = true;
    for (int j = 0; j < artInt.adjSearch.length && flag; j++) {
        if (artInt.adjSearch[j] != null) flag = false;
    }
    if (isSearchNeed) {
        if (flag) {                                                                                 // populates 'artInt.adjSearch at different indices and nulls them at an index where a possible guess is illegal
          artInt.adjSearch[0] = new Point (lastHit.x + 1, lastHit.y);
            if (!AIvalidate (playerBoard, artInt.adjSearch[0])) artInt.adjSearch[0] = null;
          artInt.adjSearch[1] = new Point (lastHit.x, lastHit.y + 1);
            if (!AIvalidate (playerBoard, artInt.adjSearch[1])) artInt.adjSearch[1] = null;
          artInt.adjSearch[2] = new Point (lastHit.x-1, lastHit.y);
            if (!AIvalidate (playerBoard, artInt.adjSearch[2])) artInt.adjSearch[2] = null;
          artInt.adjSearch[3] = new Point (lastHit.x, lastHit.y-1);
            if (!AIvalidate (playerBoard, artInt.adjSearch[3])) artInt.adjSearch[3] = null;
        }
        
        for (int index = 0; index < 4; index++) {
            if (artInt.adjSearch[index] != null) {
              checkAndMark (playerBoard, guessBoard, artInt.adjSearch[index], artInt);
              artInt.adjSearch[index] = null;
              break;
            }
        }
    }
  }
  
  /**
   * Method hitAdj
   * used to 'hit' a Ship along its length, while 'hitting' is active, and stops only when the Ship is 'sunk' (Ship.isSunk == true)
   * @param playerBoard A parameter
   * @param guessBoard A parameter
   * @param ship A parameter
   */
  public static void hitAdj (Board playerBoard, Board guessBoard, Ship ship, AI artInt) throws Exception {
    Point nextGuess = new Point ();
    for (int i = 0; i < ship.location.length; i++) {
        if (ship.location[i].equals (artInt.lastHit)) {                 // jumps the search to the point where the Ship was hit last
          int index1 = i + 1;
          int index2 = -1;
            for (int k = 1; true; k++) {                // provides index to where a Point after or before the 'hit' Point is legal.
                if (i-k < 0) break;
              index2 = i-k;
                if (AIvalidate (playerBoard, new Point (ship.location[index2].x, ship.location[index2].y) )) break;
            }
            if ( (index1 <= ship.location.length-1) && (AIvalidate (playerBoard, new Point (ship.location[index1].x, ship.location[index1].y))) ) {         // assigns the new guess if it is legal at 'index1'
              nextGuess.x = ship.location[index1].x;
              nextGuess.y = ship.location[index1].y;
              break;
            }
            else if (index2 >= 0) {                                                                                                                         // or else assign it by 'index2', which has to be legal anyway.
              nextGuess.x = ship.location[index2].x;
              nextGuess.y = ship.location[index2].y;
              break;
            }
        }
    }
    checkAndMark (playerBoard, guessBoard, nextGuess, artInt);
  }
  
  /**
   * Method AIturn
   * contitutes a single turn for the COMPUTER (AI), by coordinating the seach for Ships and 'hitting' on them.
   * @param playerBoard A parameter
   * @param guessBoard A parameter
   */
  public static void AIturn (Board playerBoard, Board guessBoard, AI artInt) throws Exception {
    Point guess = new Point ();
    boolean flag = false;
    for (int j = 0; j < artInt.hitting.length; j++) {
        if (artInt.hitting[j] == true) {
          flag = true;
          break;
        }
    }
    if (flag) {
      hitShips (playerBoard, guessBoard, artInt.lastHit, artInt);
    }
    else {
        while (true) {
          guess.x = Functions.randomInt(0, 9);
          guess.y = Functions.randomInt(0, 9);
            if (AIvalidate (playerBoard, guess)) {
              checkAndMark (playerBoard, guessBoard, guess, artInt);
              break;
            }
        }
    }
  }
}