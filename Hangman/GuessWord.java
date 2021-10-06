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
class GuessWord {
  private StringBuilder hiddenSeq;
  private String realWord;
  
    /**
     * 
     */
    GuessWord () {
      hiddenSeq = new StringBuilder ("");
      realWord = "";
    }
    
    /**
     * 
     * @param realWord 
     */
    GuessWord (String realWord) {
      hiddenSeq = new StringBuilder ("");
      for (int i = 0; i < realWord.length(); i++) hiddenSeq.append('⏺');
      this.realWord = realWord;
    }
    
  @Override
    public String toString () {
      String output = "";
      for (int i = 0; i < hiddenSeq.length(); i++) output += hiddenSeq.charAt(i) + " ";
      return "Word: " + output;
    }
    
    /**
     * 
     * @param word 
     */
    void setWord (String word) {
      hiddenSeq = new StringBuilder ("");
      for (int i = 0; i < word.length(); i++) hiddenSeq.append('⏺');
      realWord = word;
    }
    
    /**
     * 
     * @return 
     */
    String getRealWord () {
      return realWord;
    }
    
    /**
     * 
     * @return 
     */
    boolean isFullyGuessed () {
      for (int i = 0; i < realWord.length(); i++) 
          if (hiddenSeq.charAt (i) != realWord.charAt (i)) return false;
      return true;
    }
    
    /**
     * If 'guess' is a part of this.realWord, this.hiddenSeq is changed accordingly, and true is returned.
     * Otherwise false is returned
     * @param guess
     * @return 
     */
    boolean checkAndSetSeq (char guess) {
      ArrayList<Integer> occurIndices = getOccurIndices (guess);
      if (!occurIndices.isEmpty()) {
          for (Integer occurIndex : occurIndices) {
              hiddenSeq.setCharAt(occurIndex, guess);
          }
        return true;
      }
      return false;
    }

    /**
     * Returns the indices of the occurences of 'guess' in this.realWord
     * @param guess
     * @return 
     */
    private ArrayList<Integer> getOccurIndices (char guess) {
      ArrayList<Integer> occurIndices = new ArrayList<>();
      for (int i = 0; i < realWord.length(); ) {
        int currInd = realWord.indexOf(guess, i);
          if  (currInd == -1) break;
          else {
            occurIndices.add (currInd);
            i = currInd+1;
          }
      }
      return occurIndices;
    }
}