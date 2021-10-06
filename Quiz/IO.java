 
                import java.util.Scanner;
/**
 *
 * @author Mehul Ahal (1018562)
 * @author David Strik (1020037)
 */
class IO {
  
  private String response;
  
    void askForResponse () {
      Scanner sc = new Scanner (System.in);
      System.out.println ("\nplease enter your response here :-");
      response = sc.nextLine();
    }
  
    String getResponse () {
      return response;
    }
  
    void print_IntegerError_message () {
      System.out.println ("\nERROR --> please enter a letter only");
    }
  
    void print_score (int score) {
      System.out.println ("\nYour current score is : " + score + " Point(s)");
    }
  
    void print_correct_answer_message (int score) {
      System.out.println ("\nCorrect Answer : " + score + " Points!");
    }
  
    void print_wrong_answer_message () {
      System.out.println ("\nWrong Answer : 0 Points");
    }
    
    void print_second_chance_message () {
      System.out.println ("\nYou answered a few questions wrongly. So you are being given a second chance.");
    }
    
    void print_right_answer_message (String actualAnswer) {
      System.out.println ("Right answer was : " + actualAnswer);
    }
}
