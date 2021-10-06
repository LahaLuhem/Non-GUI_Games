 

/**
 *
 * @author Mehul Ahal (1018562)
 * @author David Strik (1020037)
 */
abstract class Question {
  protected String question, answer;
  protected int score;
  
    Question () {
      question = "";
      score = 3;
      answer = "";
    }
  
    Question (String q, int score, String ans) {
      question = q;
      setScore (score);
      answer = ans;
    }
    
    protected void setScore (int s) {
      if (s < 1 || s > 5) score = 3;
      else score = s;
    }
    
  @Override
    public abstract String toString ();
    
    abstract boolean isCorrect (String answer);
    
    abstract String correctAnswer ();
    
    abstract Question duplicate ();
}