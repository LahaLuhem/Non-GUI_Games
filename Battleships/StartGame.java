                                    import java.util.Scanner;
                                    import java.awt.Point;
                                    
/**
 * It is the main class where the execution begins.
 * It binds the game together by channeling the flow of execution through multiple classes and data flows.
 * 
 * @author (LahaLuhem) 
 * @version ( 1.2 [26/12/15])
 */
class StartGame {
  
  /**
   * Method main
   * is where the execution begins from and it controls all the functions.
   * @param args
   */
  public static void main (String[] args) throws Exception {
    Scanner in = new Scanner (System.in);
    Board playerBoard = new Board ();
    Board guessBoard = new Board ();
    AI artInt = new AI ();
    System.out.println ("So, comrade, what's your name??");
    String name = in.next();
    System.out.print ('\f');
    welcome (name);
    printSleep ("So now, " + name + ", lets put your ships in place");
    playerBoard.makeShips ();
    AI.AImakeShips (guessBoard);
    printSleep ("The computer is done placing it's ships, so let's start the game!!");
    for  (int turnNo = 1; !isGameOver (guessBoard, playerBoard); turnNo++) {
      System.out.print('\f');
      display (guessBoard, playerBoard);
      System.out.println ("\nWhere would you like to fire at?\n");
      String inCord;
        while (true) {
          inCord = in.next();  
            if (Board.validate (guessBoard, inCord, true, false)) break;
          System.out.print ('\f');
          display (guessBoard, playerBoard);
          System.out.println ("\nWhere would you like to fire at?\n");
        }
        
      Point guess = Functions.makePoint (inCord);
        if (guessBoard.carrier.checkMarkHit (playerBoard, guess, "")) {
          plotCord (guessBoard, playerBoard, guess, true);
          display (guessBoard, playerBoard);
          System.out.println ('\n' + name + " has got a HIT on a CARRIER after firing at " + inCord.toUpperCase() + '!');
          Thread.sleep (3000);
            if (isSunk (guessBoard.carrier)) {
              System.out.println ('\n' + name + " SUNK a CARRIER after firing at " + inCord.toUpperCase() + "!!");
              Thread.sleep (3500);
            }
        }
        else if (guessBoard.battleShip.checkMarkHit (playerBoard, guess, "")) {
          plotCord (guessBoard, playerBoard, guess, true);
          display (guessBoard, playerBoard);
          System.out.println ('\n' + name + " has got a HIT on a BATTLESHIP after firing at " + inCord.toUpperCase() + '!');
          Thread.sleep (3000);
            if (isSunk (guessBoard.battleShip)) {
              System.out.println ('\n' + name + " SUNK a CARRIER after firing at " + inCord.toUpperCase() + "!!");
              Thread.sleep (3500);
            }
        }
        else if (guessBoard.cruiser.checkMarkHit (playerBoard, guess, "")) {
          plotCord (guessBoard, playerBoard, guess, true);
          display (guessBoard, playerBoard);
          System.out.println ('\n' + name + " has got a HIT on a CRUISER after firing at " + inCord.toUpperCase() + '!');
          Thread.sleep (3000);
            if (isSunk (guessBoard.cruiser)) {
              System.out.println ('\n' + name + " SUNK a CARRIER after firing at " + inCord.toUpperCase() + "!!");
              Thread.sleep (3500);
            }
        }
        else if (guessBoard.submarine.checkMarkHit (playerBoard, guess, "")) {
          plotCord (guessBoard, playerBoard, guess, true);
          display (guessBoard, playerBoard);
          System.out.println ('\n' + name + " has got a HIT on a SUBMARINE after firing at " + inCord.toUpperCase() + '!');
          Thread.sleep (3000);
            if (isSunk (guessBoard.submarine)) {
              System.out.println ('\n' + name + " SUNK a CARRIER after firing at " + inCord.toUpperCase() + "!!");
              Thread.sleep (3500);
            }
        }
        else if (guessBoard.destroyer.checkMarkHit (playerBoard, guess, "")) {
          plotCord (guessBoard, playerBoard, guess, true);
          display (guessBoard, playerBoard);
          System.out.println ('\n' + name + " has got a HIT on a DESTROYER after firing at " + inCord.toUpperCase() + '!');
          Thread.sleep (3000);
            if (isSunk (guessBoard.destroyer)) {
              System.out.println ('\n' + name + " SUNK a CARRIER after firing at " + inCord.toUpperCase() + "!!");
              Thread.sleep (3500);
            }
        }
        else {
          plotCord (guessBoard, playerBoard, guess, false);
          display (guessBoard, playerBoard);
          System.out.println ('\n' + name + "'s got a MISS after firing at " + inCord.toUpperCase());
          Thread.sleep (2700);
        }
      System.out.print('\f');
      display (guessBoard, playerBoard);
      System.out.println ();
      
      System.out.println ("waiting for the computer to do its turn...\n");
      Thread.sleep (800);
      
      AI.AIturn (playerBoard, guessBoard, artInt);
        if (playerBoard.carrier.isSunk) AI.hitting[0] = false;          // the following conditional swith 'AI.hitting' off, by making it "false" wehn the Ship is 'sunk'.
        if (playerBoard.battleShip.isSunk) AI.hitting[1] = false;
        if (playerBoard.cruiser.isSunk) AI.hitting[2] = false;
        if (playerBoard.submarine.isSunk) AI.hitting[3] = false;
        if (playerBoard.destroyer.isSunk) AI.hitting[4] = false;
        
      //  if (turnNo%4 == 0) Functions.monologue ();
    }
    in.close();
  }
  
  /**
   * Method display
   * prints both the Boards, of the PLAYER and of the COMPUTER (AI), together in a readable format.
   * @param guessBoard guessing Board
   * @param playerBoard player's Board
   */
  public static void display (Board guessBoard, Board playerBoard) {
    System.out.print (" ");
    for (int i = 1; i < 11; i++) System.out.print (" | " + i);
    System.out.print (" |");
    System.out.print ("\t\t\t::\t\t\t");
    System.out.print (" ");
    for (int i = 1; i < 11; i++) System.out.print (" | " + i);
    System.out.println (" |");
    System.out.print ("--------------------------------------------");
    System.out.print ("\t\t\t::\t\t\t");
    System.out.println ("--------------------------------------------");
    for (int y = 0, ascii = 65; y < 10; y++, ascii++) {
      System.out.print ((char)ascii + " | ");
        for (int x = 0; x < 10; x++) {
          System.out.print (guessBoard.board[y][x] + " | ");
        }
      System.out.print ("\t\t\t::\t\t\t");
      System.out.print ((char)ascii + " | ");
        for (int x = 0; x < 10; x++) {
          System.out.print (playerBoard.board[y][x] + " | ");
        }
      System.out.print ('\n' + "-------------------------------------------");
      System.out.print ("\t\t\t::\t\t\t");
      System.out.println ("-------------------------------------------");
    }    
    System.out.println ("             GUESSBOARD\t\t\t                        ::\t\t\t\t\tPLAYERBOARD");
  }
  
  /**
   * Method hitHist
   * returns a histogram of hits on each Ship in the form of an int[5].
   * @param boards Any of the Boards
   * @return the histogram
   */
  public static int[] hitHist (Board boards) {
    int[] noHits = new int[5];
    for (int i = 0; i < boards.carrier.hits.length; i++) {
        if (boards.carrier.hits[i]) noHits[0]++;
    }
    for (int i = 0; i < boards.battleShip.hits.length; i++) {
        if (boards.battleShip.hits[i]) noHits[1]++;
    }
    for (int i = 0; i < boards.cruiser.hits.length; i++) {
        if (boards.cruiser.hits[i]) noHits[2]++;
    }
    for (int i = 0; i < boards.submarine.hits.length; i++) {
        if (boards.submarine.hits[i]) noHits[3]++;
    }
    for (int i = 0; i < boards.destroyer.hits.length; i++) {
        if (boards.destroyer.hits[i]) noHits[4]++;
    }
    return noHits;
  }
  
  /**
   * Method welcome
   * gives the introduction of the game to a PLAYER.
   * @param name The Player's name
   */
  public static void welcome (String name) throws Exception {
    String gaps = " ";
    String lineBreaks = "";
    for (int i = 0; i < 100; i++) {
      System.out.println ('\f' + lineBreaks);
      System.out.println (gaps + "                        __________________________\n"+ gaps + 
                           "                        ||||||||||||||||||||||||||\n"+ gaps + 
                           "                        ||||||||||||||||||||||||||======================= " + gaps + " *"+gaps+ '*'+gaps+ '*'+gaps+ '*'+gaps+ "*\n" + gaps + 
                           "                        ||||||||||||||||||||||||||\n"+ gaps + 
                           "                     ___||||||||||||||||||||||||||____\n"+ gaps + 
                           "_____________________|||||||||||||||||||||||||||||||||_______________________\n"+ gaps + 
                           "\\||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||//\n"+ gaps + 
                           " \\||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||//\n"+ gaps + 
                           "  \\||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||//\n"+ gaps + 
                           "   \\|||||||||||||||||||||||||||||MEHUL AHAL|||||||||||||||||||||||||||||//\n"+ gaps + 
                           "    \\||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||//\n"+ gaps + 
                           "     \\||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||//\n"+ gaps + 
                           "      \\||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||//\n"+ gaps + 
                           "       \\||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||//\n"+ gaps + 
                           "        \\||||||||||||||||||||||||||||||||||||||||||||||||||||||||||//\n"+ gaps + 
                           "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+ gaps + 
                           "                                   BATTLESHIPS");
      gaps += " ";
      lineBreaks += "\n";
      Thread.sleep (80);
    }
    System.out.print ('\f');    
    Scanner input = new Scanner (System.in);
    printSleep (name + ", so, do you know the rules of the game?? (YES [Y] or NO [N])\n{press either 'Y' or 'N' and then press ENTER}");
    String yOrN = input.next().toLowerCase();
    while ( (!yOrN.equals ("y")) && (!yOrN.equals ("n")) ) {
      System.out.println ("\fplease enter either a yes [Y] or a no [N]");
      Thread.sleep(200);
      yOrN = input.next().toLowerCase();
    }
    if ( (yOrN.equals ("n")) || (yOrN.equals ("N")) ) printRules ();
    System.out.print ('\f');
  }
  
  /**
   * Method printRules
   * prints the ruls of the game for the information of the PLAYER.
   */
  public static void printRules () throws Exception {
    System.out.print ('\f');
    Board sampleBoard = new Board ();
    sampleBoard.display ();
    System.out.println ("\t# :- indicates a part of a ship ");
    System.out.println ("\tO :- indicates a miss");
    System.out.println ("\tX :- indicates a hit on a part of a ship.\n");
    printSleep ("There are two boards - one for the player to guess on (GUESSBOARD), and one for the computer to guess on (PLAYERBOARD).\n" + 
                "Each of the boards have a set of 5 ships each : a CARRIER, a BATTLESHIP, a CRUISER, a SUBMARINE, a DESTROYER.\n" + 
                "The lengths of each ship would be known to you when you will be asked to place your ships on the board.\n" + 
                "The main objective of the game is to score repeated hits on your enemy (Computer's) ships and sink its ships faster than the Computer can sink yours, by guessing at the points where the ship may be hidden.\n\n" + 
                "Each of the points of a ship correspondingly lie on the spaces of a board.\n" + 
                "The player sets his/her ships for the computer to guess on and vica-versa.\n" + 
                "The player starts the game, followed by the computer.\n" + 
                "The two boards are arranged in grids comprising of rows (labelled A, B, C...) and columns (labelled as 1, 2, 3...)\n" + 
                "In order to guess, type in the row and then the column {eg:- C4, G6}\n" + 
                "Only ONE guess is allowed per turn.\n\n" + 
                "The game is case insensitive. That means you can type-in, in any case.\n\n" + 
                "If there is something wrong in your input, the error will be displayed on the screen and you'll be asked to type in the correct format numerous times.\n" + 
                "The '[]' brackets tell you what the required format for the input is.\n\n" + 
                "Once a ship is sunk, always the state of the PLAYERBOARD will be shown so as to direct the attention of the Player to his/her own board, as it has been found that majority of the focus is always on the GUESSBOARD.\n" + 
                "A sequence of guesses can be entered with a [SPACE] for sequetial evaluation. The Computer plays its turns continuosly in that case, but still alternately. Eg: [a1 b3 f10 g6] | [ENTER] can be entered, but after that, can't be changed.\n" + 
                "There are a few features which can be made use of anytime during the player's turn.\n\n" + 
                "For doing that, just type-in the correct input in your turn. They are :\n" + 
                "[pause] :- lets the player pause the game for a certain time period. Remember, the game cannot be resumed before the timer runs out. It can also be used as a Timer.\n" + 
                "[stats] :- displays your no. of hits, misses, miss %, hit %, miss chances, hit chances.\n\n");
                
    Scanner sc = new Scanner (System.in);
    System.out.println ("You savvy? [Y]");
    sc.next();
    sc.close();
    System.out.print ('\f');
  }
  
  /**
   * Method printSleep
   * prints a given String slowly, character-by-character, giving an impression of it being typed on-spot.
   * @param words The text to be printed slowly
   */
  public static void printSleep (String words) throws Exception {
    for (int index = 0; index < words.length(); index++) {
      System.out.print (words.charAt (index));
      Thread.sleep (60);
    }
    System.out.println ();
  }
  
  /**
   * Method isSunk
   * returns true if a Ship of a Board has been sunk (any Ship.isSunk == true), or false otherwise.
   * @param boards any of the Boards
   * @return confirmation
   */
  public static boolean isSunk (Board boards) {
    if ( (isSunk (boards.carrier)) && (boards.carrier.isSunk == false) ) return true;
    else if ( (isSunk (boards.battleShip)) && (boards.battleShip.isSunk == false) ) return true;
    else if ( (isSunk (boards.cruiser)) && (boards.cruiser.isSunk == false) ) return true;
    else if ( (isSunk (boards.submarine)) && (boards.submarine.isSunk == false) ) return true;
    else if ( (isSunk (boards.destroyer)) && (boards.destroyer.isSunk == false) ) return true;
    return false;
  }
  
  /**
   * Method isSunk
   * returns true if a specific Ship has been sunk (before the actual modification of 'Ship.isSunk = true' takes place).
   * @param ship any Ship
   * @return true=sunk/false=otherwise
   */
  public static boolean isSunk (Ship ship) {
    if (ship.isSunk == true) return false;
    
    if (ship.location.length == 5) {
        for (int i = 0; i < 5; i++) {
            if (ship.hits[i] == false) return false;
        }
    }
    else if (ship.location.length == 4) {
        for (int i = 0; i < 4; i++) {
            if (ship.hits[i] == false) return false;
        }
    }
    else if (ship.location.length == 3) {
        for (int i = 0; i < 3; i++) {
            if (ship.hits[i] == false) return false;
        }
    }
    else {
        for (int i = 0; i < 2; i++) {
            if (ship.hits[i] == false) return false;
        }
    }
    return true;
  }
  
  /**
   * Method isGameOver
   * returns true if all the Ships of any of the two Boards are sunk (Board.all Ships.isSunk == true), or false otherwise.
   * @param commBoard the guessing Board
   * @param playerBoard the Player's Board
   * @return win=true/false otherwise
   */
  public static boolean isGameOver (Board commBoard, Board playerBoard) throws Exception {
    boolean playerFlag =  playerBoard.carrier.isSunk && playerBoard.battleShip.isSunk && playerBoard.cruiser.isSunk && playerBoard.submarine.isSunk && playerBoard.destroyer.isSunk;
    boolean commFlag = commBoard.carrier.isSunk && commBoard.battleShip.isSunk && commBoard.cruiser.isSunk && commBoard.submarine.isSunk && commBoard.destroyer.isSunk;
    if (commFlag) {
      System.out.print ('\f');
      display (commBoard, playerBoard);
      System.out.println ("\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\tTHE PLAYER HAS WON!!! CONGRATULATIONS PLAYER");
      Thread.sleep (6000);
      return true;
    }
    else if (playerFlag) {
      System.out.print ('\f');
      display (commBoard, playerBoard);
      System.out.println ("\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\tTHE COMPUTER HAS WON!!! CONGRATULATIONS COMPUTER");
      Thread.sleep (6000);
      return true;
    }
    return false;
  }
  
  /**
   * Method plotCord
   * plots the character of on a Board, respective of if it is a 'hit' or a 'miss' (≹ or ◌).
   * @param guessBoard the guessing Board
   * @param playerBoard the Player's Boaard
   * @param point Point of guessing at the coordinate
   * @param isHit true=had been hit/false=missed
   */
  public static void plotCord (Board workBoard, Board accompanyBoard, Point point, boolean isHit) throws Exception {
    System.out.print ('\f');
    if (isHit) {
      workBoard.board[point.y][point.x] = '#'; //9609 ▆=ship
      workBoard.display ();
      Thread.sleep (600);
      System.out.print ('\f');
      workBoard.board[point.y][point.x] = 'X'; //9760 ≹=hit
    }
    else workBoard.board[point.y][point.x] = 'O'; //9678 ◌=miss
  }
}