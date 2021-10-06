                            import java.util.Scanner;
                            import java.awt.Point;
                    
/**
 * It provides a blueprint for the the basic and fundamental unit of the game - the Board object.
 * It contains methods co-operatively and implicitly with a Board object.
 * 
 * @author (LahaLuhem) 
 * @version ( 1.0 [24/12/15] )
 * @see class Ship
 */
class Board {
  char[][] board;                                       // the main graphical base of a Board is the char[10][10].
  Ship carrier;                                         // has a 'carrier'.
  Ship battleShip;                                      // has a 'battleShip'.
  Ship cruiser;                                         // has a 'cruiser'.
  Ship submarine;                                       // has a 'submarine'.
  Ship destroyer;                                       // has a 'destroyer'.

  /**
   * Board Constructor
   * creates a Board Object, the Board[] initialised with ' ' spaces, and with Ships constructed by the Ship constructor.
   */
  public Board () {
    board = new char[10][10];
    for (int y = 0; y < 10; y++) {
        for (int x = 0; x < 10; x++) {
          board[y][x] = ' ';
        }
    }
    carrier = new Ship (5);
    battleShip = new Ship (4);
    cruiser = new Ship (3);
    submarine = new Ship (3);
    destroyer = new Ship (2);
  }
  
  /**
   * Method display
   * prints a Board in a readable format.
   */
  public void display () {
    System.out.print (" ");
    for (int i = 1; i < 11; i++) System.out.print (" | " + i);
    System.out.println (" |");
    System.out.println ("--------------------------------------------");
    for (int y = 0, ascii = 65; y < 10; y++, ascii++) {
      System.out.print ((char)ascii + " | ");
        for (int x = 0; x < 10; x++) {
          System.out.print (board[y][x] + " | ");
        }
      System.out.println ('\n' + "-------------------------------------------");
    }
  }
    
  /**
   * Method validate
   * returns true if the input (as a String) is valid for a Board (also along with the Ships' placement), or false otherwise (displayingthe error messages).
   * It displays relevant input-error messages.
   * @param boards A parameter
   * @param inStr A parameter
   * @param toCheckIsTried A parameter
   * @return The return value
   */
  public static boolean validate (Board boards, String inStr, boolean toCheckIsTried, boolean isDirect) throws Exception {
    if ( (!isDirect) && (inStr.length() == 2 || inStr.length() ==  3) ) {                                                     // check input for coordinates
      boolean flag1 = false;
      boolean flag2 = false;
        for (int ascii = 97; ascii <= 106; ascii++) {
            if ((char)ascii == inStr.charAt (0)) {
              flag1 = true;
              break;
            }
        }
        for (int n = 1; n <= 10; n++) {
            if (n == Functions.toInt (inStr.substring (1, inStr.length()))) {
              flag2 = true;
              break;
            }
        }
        if ((flag1 && flag2) == false) {
          System.out.println ("\n\nError :- this input sequence doesn't exist - please enter again");
          Thread.sleep (1800);
          return false;
        }
        if (toCheckIsTried) {
          Point point = Functions.makePoint (inStr);
            if ((boards.board[point.y][point.x] == 'X') || (boards.board[point.y][point.x] == 'O')) {
              System.out.println ("\n\nthis has already been tried - please enter again");
              Thread.sleep (1800);
              return false;
            }
        }
      return true;  
    }
    else if (inStr.length() == 4) {                                                                                 // check inpuut for commands determining the directions of a Ship while placing it
        if ( (inStr.equals ("utod")) || (inStr.equals ("ltor")) ) return true;
      System.out.println ("\n\nError :- wrong input - kindly check again");
      Thread.sleep (1800);
      return false;
    }
    else if (inStr.length() == 5) {                                                                                 // check input for the special features.
        if (inStr.equals ("pause")) {
          Functions.pause ();
          return false;
        }
        if (inStr.equals ("stats")) {
          Functions.stats (boards);
          return false;
        }
    }
    System.out.println ("\n\ncommand not understood");
    Thread.sleep (1800);
    return false;
  }
  
  /**
   * Method makeShips
   * creates legal Ships for the PLAYER, for a given Board, taking inputs.
   * It displays input cues for the benefit of the user.
   */
  public void makeShips () throws Exception {
    Scanner in = new Scanner (System.in);
    String cords = "";
    String direction = "";
    for (boolean isValid = false; isValid == false; ) {                                                              // building and placing a legal CARRIER
        for (boolean val = false; val == false; ) {
          System.out.print ('\f');
          display ();
          System.out.println ("\nWould you like to place your CARRIER (length = 5) from Up to Down [UtoD], or from Left to Right [LtoR]?");
          direction = (in.next()).toLowerCase();
          val = validate (this, direction, false, true);
        }
        for (boolean val = false; val == false; ) {
          System.out.print ('\f');
          display ();
          System.out.println ("\nAt what coordinate would you like to place it from?");
          cords = (in.next()).toLowerCase();
          val = validate (this, cords, false, false);
        }
      Point point = Functions.makePoint (cords);
      int direct = -1;
      if (direction.equals ("utod")) direct = 0;
      else direct = 1;
      Functions.buildShip (this, "carrier", point, direct);
      isValid = shipVal (point, direct);                                    // actual validation takes place from here
      if (isValid) {
        Functions.placeShip (this, carrier);
      }
      else {
        System.out.println ("\nplease enter a valid coordinate");
        Thread.sleep (1500);
      }
    }
    
    for (boolean isValid = false; isValid == false; ) {                                                              // building and placing a legal BATTLESHIP
        for (boolean val = false; val == false; ) {
          System.out.print ('\f');
          display ();
          System.out.println ("\nWould you like to place your BATTLESHIP (length = 4) from Up to Down [UtoD], or Left to Right [LtoR]?");
          direction = (in.next()).toLowerCase();
          val = validate (this, direction, false, true);
        }
        for (boolean val = false; val == false; ) {
          System.out.print ('\f');
          display ();
          System.out.println ("\nAt what coordinate would you like to place it from?");
          cords = (in.next()).toLowerCase();
          val = validate (this, cords, false, false);
        }
      Point point = Functions.makePoint (cords);
      int direct = -1;
      if (direction.equals ("utod")) direct = 0;
      else direct = 1;
      Functions.buildShip (this, "battleShip", point, direct);
      isValid = shipVal (point, direct);                                    // actual validation takes place from here
      if (isValid) {
        Functions.placeShip (this, battleShip);
      }
      else {
        System.out.println ("\nplease enter a valid coordinate");
        Thread.sleep (1500);
      }
    }
    
    for (boolean isValid = false; isValid == false; ) {                                                              // building and placing a legal CRUISER
        for (boolean val = false; val == false; ) {
          System.out.print ('\f');
          display ();
          System.out.println ("\nWould you like to place your CRUISER (length = 3) from Up to Down [UtoD], or Left to Right [LtoR]?");
          direction = (in.next()).toLowerCase();
          val = validate (this, direction, false, true);
        }
        for (boolean val = false; val == false; ) {
          System.out.print ('\f');
          display ();
          System.out.println ("\nAt what coordinate would you like to place it from?");
          cords = (in.next()).toLowerCase();
          val = validate (this, cords, false, false);
        }
      Point point = Functions.makePoint (cords);
      int direct = -1;
      if (direction.equals ("utod")) direct = 0;
      else direct = 1;
      Functions.buildShip (this, "cruiser", point, direct);
      isValid = shipVal (point, direct);                                    // actual validation takes place from here
      if (isValid) {
        Functions.placeShip (this, cruiser);
      }
      else {
        System.out.println ("\nplease enter a valid coordinate");
        Thread.sleep (1500);
      }
      
    }
    
    for (boolean isValid = false; isValid == false; ) {                                                               // building and placing a legal SUBMARINE
        for (boolean val = false; val == false; ) {
          System.out.print ('\f');
          display ();
          System.out.println ("\nWould you like to place your SUBMARINE (length = 3) from Up to Down [UtoD], or Left to Right [LtoR]?");
          direction = (in.next()).toLowerCase();
          val = validate (this, direction, false, true);
        }
        for (boolean val = false; val == false; ) {
          System.out.print ('\f');
          display ();
          System.out.println ("\nAt what coordinate would you like to place it from?");
          cords = (in.next()).toLowerCase();
          val = validate (this, cords, false, false);
        }
      Point point = Functions.makePoint (cords);
      int direct = -1;
      if (direction.equals ("utod")) direct = 0;
      else direct = 1;
      Functions.buildShip (this, "submarine", point, direct);
      isValid = shipVal (point, direct);                                    // actual validation takes place from here
      if (isValid) {
        Functions.placeShip (this, submarine);
      }
      else {
        System.out.println ("\nplease enter a valid coordinate");
        Thread.sleep (1500);
      }
      
    }
    
    for (boolean isValid = false; isValid == false; ) {                                                               // building and placing a legal DESTROYER
        for (boolean val = false; val == false; ) {
          System.out.print ('\f');
          display ();
          System.out.println ("\nWould you like to place your DESTROYER (length = 2) from Up to Down [UtoD], or Left to Right [LtoR]?");
          direction = (in.next()).toLowerCase();
          val = validate (this, direction, false, true);
        }
        for (boolean val = false; val == false; ) {
          System.out.print ('\f');
          display ();
          System.out.println ("\nAt what coordinate would you like to place it from?");
          cords = (in.next()).toLowerCase();
          val = validate (this, cords, false, false);
        }
      Point point = Functions.makePoint (cords);
      int direct = -1;
      if (direction.equals ("utod")) direct = 0;
      else direct = 1;
      Functions.buildShip (this, "destroyer", point, direct);
      isValid = shipVal (point, direct);                                    // actual validation takes place from here
      if (isValid) {
        Functions.placeShip (this, destroyer);
        System.out.print ('\f');
        display ();
        Thread.sleep (1200);
      }
      else {
        System.out.println ("\nplease enter a valid coordinate");
        Thread.sleep (1500);
      }
      
    }
    in.close();
  }
  
  /**
   * Method shipVal
   * returns true if a Ship created, can be legally placed on a given Board, or false otherwise (while displaying the error message).
   * It displys the relevant input-error messages for the user.
   * @param point A parameter
   * @param direct A parameter
   * @return The return value
   */
  public boolean shipVal (Point point, int direct) throws Exception {
    int isVal = 0;
    if ( (direct == 0) && ((carrier.location[1] == null) || (battleShip.location[1] == null) || (cruiser.location[1] == null) || (submarine.location[1] == null) || (destroyer.location[1] == null)) ) {        // checks if the Ship can be fit
        if (battleShip.location[1] == null) {                                                                                                                                                                   // vertically and double-checks it.
            if ( (point.y < 0) || (point.y + 4 > 9) ) isVal = -1;
        }
        else if (cruiser.location[1] == null) {
            if ( (point.y < 0) || (point.y + 3 > 9) ) isVal = -1;
        }
        else if (submarine.location[1] == null) {
            if ( (point.y < 0) || (point.y + 2 > 9) ) isVal = -1;
        }
        else if (destroyer.location[1] == null) {
            if ( (point.y < 0) || (point.y + 2 > 9) ) isVal = -1;
        }
        else {
            if ( (point.y < 0) || (point.y + 1 > 9) ) isVal = -1;
        }
    }
    else if ( (direct == 1) && ((carrier.location[1] == null) || (battleShip.location[1] == null) || (cruiser.location[1] == null) || (submarine.location[1] == null) || (destroyer.location[1] == null)) ) {   // checks if the Ship can be fit
        if (battleShip.location[1] == null) {                                                                                                                                                                   // horizontaly and double-checks it.
            if ( (point.x < 0) || (point.x + 4 > 9) ) isVal = -1;
        }
        else if (cruiser.location[1] == null) {
            if ( (point.x < 0) || (point.x + 3 > 9) ) isVal = -1;
        }
        else if (submarine.location[1] == null) {
            if ( (point.x < 0) || (point.x + 2 > 9) ) isVal = -1;
        }
        else if (destroyer.location[1] == null) {
            if ( (point.x < 0) || (point.x + 2 > 9) ) isVal = -1;
        }
        else {
            if ( (point.x < 0) || (point.x + 1 > 9) ) isVal = -1;
        }
    }
    if (isVal != 0) {
        if (isVal == -1) {
          System.out.println ("\nError :- the ship cannot fit in the board by your coordinates - kindly check again");                    // invalid Ship error displayed
          Thread.sleep (3500);
          return false;
        }
        return true;
    }
    
    boolean flag = true;
    if ( (carrier.location[2] != null) && (battleShip.location[2] != null) && (flag) ) {     // checks whether two Ships are intersecting each other, only if one Ship has been built and placed, and the other one (the current one) is only built.
        if (cruiser.location[2] == null) {                                                           // check if Carrier AND BattleShip are built
             for (int index = 0; index < 5; index++) {                               // check for their intersection
                 for (int i = 0; i < 4; i++) {
                     if (carrier.location[index].equals (battleShip.location[i])) flag = false;
                 }
             }
        }
        else if ((submarine.location[2] == null) && (flag)) {                                       // check if Carrier AND BattleShip AND Cruiser AND are built
             for (int index = 0; index < 5; index++) {                                                       // checks for their intersection
                 for (int i = 0; i < 3; i++) {
                     if (carrier.location[index].equals (cruiser.location[i])) flag = false;
                 }
             }
             for (int index = 0; index < 4; index++) {                                                      //
                 for (int i = 0; i < 3; i++) {
                     if (battleShip.location[index].equals (cruiser.location[i])) flag = false;
                 }
             }
        }
        else if ((destroyer.location[1] == null) && (flag)) {                                       // check if Carrier AND BattleShip AND Cruiser AND Submarine are built
             for (int index = 0; index < 5; index++) {
                 for (int i = 0; i < 3; i++) {
                     if (carrier.location[index].equals (submarine.location[i])) flag = false;               //
                 }
             }
             for (int index = 0; index < 4; index++) {
                 for (int i = 0; i < 3; i++) {
                     if (battleShip.location[index].equals (submarine.location[i])) flag = false;           // checks for their intersection
                 }
             }
             for (int index = 0; index < 3; index++) {
                 for (int i = 0; i < 3; i++) {
                     if (cruiser.location[index].equals (submarine.location[i])) flag = false;              //
                 }
             }
        }
        else {                                                                                  // when Carrier AND BattleShip AND Cruiser AND Submarine AND Destroyer are built
             for (int index = 0; index < 5; index++) {                                                      //
                 for (int i = 0; i < 2; i++) {
                     if (carrier.location[index].equals (destroyer.location[i])) flag = false;
                 }
             }
             for (int index = 0; index < 4; index++) {                                                      //
                 for (int i = 0; i < 2; i++) {
                     if (battleShip.location[index].equals (destroyer.location[i])) flag = false;           
                 }
             }
             for (int index = 0; index < 3; index++) {                                                      // checks for their intersection
                 for (int i = 0; i < 2; i++) {
                     if (cruiser.location[index].equals (destroyer.location[i])) flag = false;
                 }
             }
             for (int index = 0; index < 3; index++) {                                                      //
                 for (int i = 0; i < 2; i++) {
                     if (submarine.location[index].equals (destroyer.location[i])) flag = false;
                 }
             }
        }
      
        if (!flag) {
          System.out.println ("\nError :- the ship intersects with another ship - kindly check again");                       // invalid Ship error displayed
          Thread.sleep (2500);
          return false;
        }
    }
    return flag;
  }
}