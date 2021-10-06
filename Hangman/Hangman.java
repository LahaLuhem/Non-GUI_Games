/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

/**
 *
 * @author LahaLuhem
 */
public class Hangman {
  IO io;
  GuessWord guessWord;
  Gallows gallows;
  GuessedLetters gl;
  
    /**
     *
     */
    public Hangman () {
      io = new IO ();
      guessWord = new GuessWord ();
      gallows = new Gallows ();
      gl = new GuessedLetters ();
    }
  
    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) throws Exception {
      Hangman hangman = new Hangman ();
      welcome ();
      (hangman.guessWord).setWord((hangman.io).getInputWord());
      while (true) {
        System.out.println (hangman.gallows);
        System.out.println (hangman.gl);
        System.out.println (hangman.guessWord);
        
        hangman.processGuess ();
        //System.out.println ();
        Thread.sleep (1500);
        clearScreen ();          //doesn't work for some consoles
        
          if (hangman.gallows.getRemainingMistakes() == 0) {
            printLoser_Message (hangman.guessWord.getRealWord());
            break;
          }
          else if (hangman.guessWord.isFullyGuessed()) {
            printWinner_Message ();
            break;
          } 
      }
      printGame_Over_Message ();
    }
    
    private void processGuess () {
      char guess = io.getNextGuess ();
      boolean isContained = guessWord.checkAndSetSeq(guess);
      System.out.println ();
      if (isContained) System.out.println (guess + " IS in the word");
      else if (!isContained) {
        System.out.println (guess + " IS NOT in the word");
        gallows.subtractLife ();
      }
      gl.addGuess(guess);
    }
    
    /**
     * Displays a custom welcoming message
     */
    private static void welcome() {
      System.out.println ("\\          /   _____          __      .      |\\     /|   _____                 ________      .   \n" +
                          " \\        /   |       |     /      *     *   |  \\ /  |  |                          |      *     *\n" +
                          "  \\  /\\  /    |---    |     |      .     .   |   *   |  |---                       |      .     .\n" +
                          "   \\/  \\/     |_____  |___  \\ __    . . .    |       |  |_____                     |       . . .\n");
      
      
      String indentSpaces = "\t\t\t\t\t";
      System.out.println (indentSpaces+  "_____\n"+indentSpaces+
                          "  |\n"+indentSpaces+
                          "  |\n"+indentSpaces+
                          "_\\|/^\n"+indentSpaces+
                          " (_oo\n"+indentSpaces+
                          "  |\n"+indentSpaces+
                          " /|\\\n"+indentSpaces+
                          "  |\n"+indentSpaces+
                          "  LL\n\n\n"+
                          "\t\t\t\t\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\n");
    }
    
    /**
     * Displays a custom losing message
     */
    private static void printLoser_Message (String rightWord) {
      System.out.println ("You Lost.");  
        
      String indentSpaces = "\t\t\t\t\t";
      System.out.println (indentSpaces+  "_____\n"+indentSpaces+
                          "  |      \n"+indentSpaces+
                          "  |       \n"+indentSpaces+
                          "_\\|/^  \n"+indentSpaces+
                          " (_xx    \n"+indentSpaces+
                          "  |       (\\   @\n"+indentSpaces+
                          " /|\\        \\--#;\n"+indentSpaces+
                          "  |          \\ &\n"+indentSpaces+
                          "  LL          X \\\n\n\n"+
                          "\t\t\t\t\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\n");
      
      
      System.out.println ("The correct word was '" + rightWord + "'");
    }
    
    /**
     * Displays a custom winning message
     */
    private static void printWinner_Message() {
      System.out.println ("You WON. You got the word");
      
      System.out.println ("                      | |\n" +
                          "                      |/|\n" +
                          "                     (___)\n" +
                          "                     // \\\\\n" +
                          "   !!                || ||\n" +
                          " O_                  \\\\_//\n" +
                          " /X_,                \n" +
                          "  <                  \n" +
                          "\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\n");
    }
    
    /**
     * Displays a custom end-game message
     */
    private static void printGame_Over_Message() {
      System.out.println ("\nGAME OVER. HOPE YOU ENJOYED IT. SEE YOU NEXT TIME!!");
    }
    
    /**
     * Clears the console. Might not work for some IDEs
     * @throws Exception 
     */
    public static void clearScreen() throws Exception{
      if (System.getProperty("os.name").contains("Windows"))
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      else
        Runtime.getRuntime().exec("clear");
    }
}