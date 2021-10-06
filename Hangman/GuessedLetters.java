/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
                    import java.util.ArrayList;
/**
 *
 * @author ahalm
 */
class GuessedLetters {
  private ArrayList<Character> guesses;
  
    /**
     * 
     */
    GuessedLetters () {
      guesses = new ArrayList();
    }
  
  @Override
    public String toString () {
      return "Guessed letters: " + guesses;
    }
  
    /**
     * 
     * @param guess 
     */
    void addGuess (char guess) {
      if (!hasGuess (guess)) guesses.add (guess);
      else System.out.println ("\n<You had already guessed " + guess + ". But you STILL MAY BE penalized!!!>");
    }
    
    /**
     * Returns whether the given ArrayList contains the 'guess'
     * @param guess
     * @return 
     */
    private boolean hasGuess (char guess) {
      for (int i = 0; i < guesses.size(); i++)
          if (guesses != null && guesses.get(i) == guess) return true;
      return false;
    }
}