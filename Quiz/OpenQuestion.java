 

/**
 *
 * @author Mehul Ahal (1018562)
 * @author David Strik (1020037)
 */
class OpenQuestion extends Question {

    OpenQuestion (String question , String answer , int score) {
      this.question = question;
      this.answer = answer;
      setScore (score);
    }
    
    OpenQuestion (String question , String answer) {
      this.question = question;
      this.answer = answer;
    }
    
    @Override
    public String toString() {
      return "\nOpen Question :- " + question + "\t[" + score + "]";
    }

    @Override
    boolean isCorrect(String answer) {
      return (this.answer).equalsIgnoreCase(answer);
    }

    @Override
    String correctAnswer() {
      return answer;
    }

    @Override
    Question duplicate() {
      return new OpenQuestion (question, answer, score);
    }
}