 

/**
 *
 * @author ahalm
 */
class Gallows {
  private int remainingMistakes;
  
    /**
     * 
     */
    Gallows () {
      remainingMistakes = 10;
    }
    
  @Override
    public String toString () {
      String output = "";
      for (int i = 0; i < remainingMistakes; i++) output += "❤     ";
      return "Remaining mistakes: " + output + "\b\b\b(" + remainingMistakes + ")";
    }
    
    /**
     * 
     * @return 
     */
    int getRemainingMistakes () {
      return remainingMistakes;
    }
    
    /**
     * 
     */
    void subtractLife () {
      remainingMistakes--;
    }
}