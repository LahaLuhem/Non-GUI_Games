                            import java.util.Scanner;
                            import java.awt.Point;
                            
/**
 * It contains all the static methods to be used commonly by the Board, AI, and StartGame classes.
 * It also has add-on features of the game.
 * 
 * @author (LahaLuhem) 
 * @version ( 1.0 [24/12/15] )
 */
class Functions {
  
  /**
   * Method buildShip
   * builds Ships according to the given Point and direction.
   * makeShip lets only the possible and legal from buildShip exist.
   * @param boards Any of the Boards
   * @param ship The Ship's name
   * @param point the Point from where the Ship will be constructed
   * @param direct The direction of the buliding Point of the Ship form the given 'point' : direct=0 for vertical, and direct=1 for vertical
   */
  public static void buildShip (Board boards, String ship, Point point, int direct) {
    if (direct == 0) {
        if (ship.equals ("carrier")) {                                                      // build Ships as per vertical direction
            for (int i = 0; i <= 4; i++) {
              boards.carrier.location[i] = new Point (point.x, point.y + i);
            }
        }
        else if (ship.equals ("battleShip")) {
            for (int i = 0; i <= 3; i++) {
              boards.battleShip.location[i] = new Point (point.x, point.y + i);
            }
        }
        else if (ship.equals ("cruiser")) {
            for (int i = 0; i <= 2; i++) {
              boards.cruiser.location[i] = new Point (point.x, point.y + i);
            }
        }
        else if (ship.equals ("submarine")) {
            for (int i = 0; i <= 2; i++) {
              boards.submarine.location[i] = new Point (point.x, point.y + i);
            } 
        }
        else if (ship.equals ("destroyer")) {
            for (int i = 0; i <= 1; i++) {
              boards.destroyer.location[i] = new Point (point.x, point.y + i);
            } 
        }
    }
    else {                                                                                  // builds Ship as per horizontal direction.
        if (ship.equals ("carrier")) {
            for (int i = 0; i <= 4; i++) {
              boards.carrier.location[i] = new Point (point.x + i, point.y);
            }
        }
        else if (ship.equals ("battleShip")) {
            for (int i = 0; i <= 3; i++) {
              boards.battleShip.location[i] = new Point (point.x + i, point.y);
            }
        }
        else if (ship.equals ("cruiser")) {
            for (int i = 0; i <= 2; i++) {
              boards.cruiser.location[i] = new Point (point.x + i, point.y);
            }
        }
        else if (ship.equals ("submarine")) {
            for (int i = 0; i <= 2; i++) {
              boards.submarine.location[i] = new Point (point.x + i, point.y);
            } 
        }
        else if (ship.equals ("destroyer")) {
            for (int i = 0; i <= 1; i++) {
              boards.destroyer.location[i] = new Point (point.x + i, point.y);
            }
        }
    }
  }
  
  /**
   * Method placeShip
   * places a made virtual Ship physically on the given Board.
   * @param boards Any of the Boards
   * @param ship The Ship that needs the physical interpretation
   */
  public static void placeShip (Board boards, Ship ship) {
    for (int i = 0; i < ship.location.length; i++) {
      boards.board[ship.location[i].y][ship.location[i].x] = '#';
    }
  }
  
  /**
   * Method toInt
   * converts a given String (only if it is between a range of 1 and 10) to an int, and returns it, or a negative value otherwise.
   * It is an adaptation of Integer.parseInt (), but to only accomodate the range of a Board (1-10).
   * @param strChar The number in the form of a digit, that is to be converted
   * @return The converted 'int' form
   */
  public static int toInt (String strNum) {
    int i = 49;
    int ascii = -1;
    for (; i <= 57; i++) {
        if (("" + (char)i).equals (strNum)) break;
        else if (strNum.equals ("10")) return 10;
    }    
    if (i > 57) return 12;
    return i-48;
  }
  
  /**
   * Method makePoint
   * converts a coordinate in the form af a String (as in an input) to a Point, and returns it. It is a mirror-method of 'toCord'
   * @param cord The input form to be converted
   * @return The converted Point
   */
  public static Point makePoint (String cord) {
    Point point = new Point ((toInt(cord.substring (1, cord.length())))-1, cord.charAt (0)-97);
    return point;
  }
  
  /**
   * Method toCord
   * converts a coordinate in the form of a Point to a String, and returns it. It is a mirror-method of 'makePoint'
   * @param point The Point that needs to be converted
   * @return The String interpretation of it (Eg:- (0,0) -> A1)
   */
  public static String toCord (Point point) {
    String cord = "";
    cord += (char)(point.y + 65);
    cord += ++point.x;
    return cord;
  }
  
  /**
   * Method nullify
   * makes all the elements of a given Point[] as 'null'.
   * @param guesses A Point[] to be nullified
   */
  public static void nullify (Point[] guesses) {
    for (int i = 0; i < guesses.length; i++) guesses[i] = null;
  }
  
  /**
   * Method randomInt
   * generates a random int between a given range, and returns it.
   * @param low The lower limit of the required 'int'
   * @param high The upper limit of the required 'int'
   * @return The pseudo-random int between low and high
   */
  public static int randomInt (int low, int high) {
    double rand = low-1;
    while (rand < (double) low) rand = (double) high*Math.random();
    return (int) rand;
  }
  
  /**
   * Method pause
   * pauses/counts down from a given int every second (1000 milliseconds).
   */
  public static void pause () throws Exception {
    Scanner in = new Scanner (System.in);
    System.out.println ("\fplease enter the no. of seconds (as a number only) you want to pause the game for :-");
    int strTime = in.nextInt();
    System.out.print ('\f');
    for (; strTime > 0; strTime--) {
      System.out.print ("\fTime remainig for resuming :- " + strTime + " secs.");
      Thread.sleep (1000);
    }
    System.out.print ("\f\n\nRESUMING GAME...");
    Thread.sleep (1000);
  }
  
  /**
   * Method stats
   displays the statistics in-between a running game.
   * @param playerBoard The Player's Board
   */
  public static void stats (Board playerBoard) {
    int misses = 0, hits = 0;
    for (int y = 0; y < 10; y++) {
        for (int x = 0; x < 10; x++) {
            if (playerBoard.board[y][x] == 'O') misses++;
            if (playerBoard.board[y][x] == 'X') hits++;
        }
    }
    System.out.print ('\f');
    playerBoard.display ();
    System.out.println ("\n\n\n\n\n\n\n\n\nYou've got " + misses + " misses.\n");
    System.out.println ("You've got " + hits + " hits.\n\n");                                                  
    if ( (misses != 0) && (hits != 0) ) {                                                                                   //(ship points) + (rest of board points) = (all the board points)
      System.out.println ("Your miss percentage is :- " + (float)misses/(float)(misses + hits)*100.0f + " %.\n");           // =>  17       +           83           =            100
      System.out.println ("Your hits percentage is :- " + (float)hits/(float)(misses + hits)*100.0f + " %.\n\n");
      System.out.println ("Your missing chances are :- " + (100.0f - (float)misses/83.0f*100.0f) + " %.\n");
      System.out.println ("Your hitting chances are :- " + ((float)hits/17.0f*100.0f) + " %.\n");
    }
    else {
        if ( (misses == 0) && (hits == 0) ) {
          System.out.println ("Your miss percentage or hit percentage isn't applicable as yet.\n\n");
          System.out.println ("Your missing chances or hit chances isn't applicable as yet.\n");
        }
        else if (misses == 0) {
          System.out.println ("Your miss percentage isn't applicable as yet.\n");
          System.out.println ("Your hits percentage is :- " + (float)hits/(float)(misses + hits)*100.0f + " %.\n\n");
          System.out.println ("Your missing chances are isn't applicable as yet.\n");
          System.out.println ("Your hitting chances are :- " + ((float)hits/17.0f*100.0f) + " %.\n");
        }
        else {
          System.out.println ("Your miss percentage is :- " + (float)misses/(float)(misses + hits)*100.0f + " %.\n");
          System.out.println ("Your hits percentage isn't applicable as yet.\n\n");
          System.out.println ("Your missing chances are :- " + (100.0f - (float)misses/83.0f*100.0f) + " %.\n");
          System.out.println ("Your hitting chances isn't applicable as yet.\n");
        }
    }
    
    Scanner sc = new Scanner (System.in);
    System.out.println ("\n\nEXIT? [Y]");
    sc.next();
    sc.close();
  }
  
  /**
   * Method monologue
   *displays demoralising messages from the Computer when damage is dealt to the Player.
   */
  public static void monologue () throws Exception {
    int messNo = randomInt (0, 9);
    String[] database = new String[10];
    database[0] = "COMPUTER : So, you actually think that you can beat me?";
    database[1] = "COMPUTER : You don't even stand a chance.";
    database[2] = "COMPUTER : Better resign whilst you still have the chane, you won't have to face a shameful defeat.";
    database[3] = "COMPUTER : Gunners, all guns booming!";
    database[4] = "COMPUTER : Did you think of beating me. Now better wake up and apologise.";
    database[5] = "COMPUTER : If you see death, I taste it.";
    database[6] = "COMPUTER : Better go back and start doing atleast something that you are capable of.";
    database[7] = "COMPUTER : Are you gonna do something or what";
    database[8] = "COMPUTER : Tche, tche";
    database[9] = "COMPUTER : Why did you even try?";
    
    StartGame.printSleep (database[messNo]);
    Thread.sleep (400);
  }
}