 
/**
 *
 * @author Mehul Ahal (1018562)
 * @author David Strik (1020037)
 */
class MultipleChoiceQuestion extends Question {
  
  private String[] answers;
  private int correctAnswer;
  
  
    MultipleChoiceQuestion (String question , String[] answers , int correctAnswer , int score) {
      this.question = question;
      this.answers = answers;
      this.correctAnswer = correctAnswer;
      setScore (score);
    }

    MultipleChoiceQuestion (String question , String [] answers , int correctAnswer) {
      this.question = question;
      this.answers = answers;
      this.correctAnswer = correctAnswer;
    }
    
    @Override
    public String toString() {
      String returnSeq = "\nMCQ :- " + question + "\t[" + score + "]\n";
      for (int i = 0; i < answers.length; i++) returnSeq += (char)(i+65) + ") " + answers[i] + "\n";
      return returnSeq;
    }

    @Override
    boolean isCorrect (String answer) throws NumberFormatException {
      if (answer.length() > 1 || !Character.isLetter(answer.charAt (0))) throw new NumberFormatException ();
      return answer.equalsIgnoreCase("" + (char)(correctAnswer + 65));
    }

    @Override
    String correctAnswer() {
      return "" + (correctAnswer + 65);
    }

    @Override
    Question duplicate() {
      return new MultipleChoiceQuestion (question, answers, correctAnswer, score);
    }
}