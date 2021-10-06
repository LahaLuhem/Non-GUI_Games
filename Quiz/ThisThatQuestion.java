 

/**
 *
 * @author Mehul Ahal (1018562)
 * @author David Strik (1020037)
 */
class ThisThatQuestion extends Question {
    
  private String answer1, answer2;
  private int correctAnswer;
    
    ThisThatQuestion (String question , String answer1 , String answer2 , int correctAnswer , int score) {
      this.question = question;
      this.answer1 = answer1;
      this.answer2 = answer2;
      this.correctAnswer = correctAnswer;
      this.score = score;
    }
    
    ThisThatQuestion (String question , String answer1 , String answer2 , int correctAnswer) {
      this.question = question;
      this.answer1 = answer1;
      this.answer2 = answer2;
      this.correctAnswer = correctAnswer;
    }
    
    @Override
    public String toString() {
      return "\nThis-Or-That Questionc :- " + question + "\t[" + score + "]";
    }

    @Override
    boolean isCorrect(String answer) {
      if (correctAnswer == 0) return answer1.equalsIgnoreCase(answer);
      return answer2.equalsIgnoreCase(answer);
    }

    @Override
    String correctAnswer() {
      if (correctAnswer == 0) return answer1;
      return answer2;
    }

    @Override
    Question duplicate() {
      return new ThisThatQuestion (question, answer1, answer2, correctAnswer, score);
    }
}
