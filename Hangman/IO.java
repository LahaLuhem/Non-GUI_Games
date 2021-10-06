/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.InputMismatchException;
                        import java.util.Scanner;
/**
 *
 * @author ahalm
 */
class IO {
  private String choice;
  private char input;
  
    /**
     * 
     */
    IO () {
      choice = "";
      input = ' ';
    }
    
    /**
     * 
     * @return 
     */
    String getInputWord () {
      Scanner sc = new Scanner (System.in);
      System.out.print ("Please enter a word or press Enter to randomly pick one :-\n> ");
      choice = sc.nextLine();
      if (!choice.equals("")) {
        System.out.println ("\n<WORD TAKEN>");
        return choice;
      }
      WordReader wr = new WordReader ("words.txt");
      System.out.println ("\n<RANDOMLY PICKED A WORD>");
      return wr.getWord();
    }
    
    /**
     * 
     * @return 
     */
    String getWord () {
      return choice;
    }
    
    /**
     * 
     * @return 
     */
    char getNextGuess () {
      Scanner sc = new Scanner (System.in);
      System.out.print ("Enter your next guess :-\n> ");
      char nextGuess = ' ';
      while (true) {
        try {
          nextGuess = (sc.nextLine()).charAt(0);
          return nextGuess;
        }
        catch (Exception e) {
          System.out.println(">ERROR<");
        }
      }
    }
}