 
                import java.util.ArrayList;
/**
 *
 * @author Mehul Ahal (1018562)
 * @author David Strik (1020037)
 */
class Run {
  
  IO io;
  ArrayList<Question> questions;
  ArrayList<Question> wrongAs;
  int scoreStore;
  
    Run () {
      io = new IO ();
      questions = new ArrayList<>();
      wrongAs = new ArrayList<>();
    }
  
    void run () throws Exception {
      /*questions.add (new OpenQuestion ("What is the complexity of binary search?", "O(log N)", 2));
      questions.add(new OpenQuestion ("What is the minimal amount of constructors for a Java class?", "0"));
      questions.add (new OpenQuestion ("What is the big O complexity of binary search?", "O(log N)"));
      questions.add (new OpenQuestion ("Is there a difference between an interface and an abstract class?", "Yes", 5));
      questions.add (new OpenQuestion ("How would you read an integer i from scanner s in Java ?", "i = s.nextInt ();", 2));
      questions.add (new OpenQuestion ("What is the minimum amount of constructors you have to define for a class in Java?", "0", 2));
      questions.add (new OpenQuestion ("Is there a maximum to the amount of constructors a class can have in Java?", "No"));*/
      
      questions.add (new MultipleChoiceQuestion ("What is the best achievable complexity of in situ sorting?", new String[] {"O(N^2)",
                                                                                                                             "O(N log N)",
                                                                                                                             "O(N)",
                                                                                                                             "O(log N)"}, 1, 4));
      questions.add (new MultipleChoiceQuestion ("How do you print \"Hello world\" on a line in Java?", new String [] {"System.out.print (\"Hello world\");", 
                                                                                                                       "System.out.println (\"Hello world\");", 
                                                                                                                       "count << \"Hello world\";"}, 1));
      questions.add (new MultipleChoiceQuestion ("How do you read a non - empty word using scanner s?", new String [] {"\"s. nextline ()", 
                                                                                                                       "s.next (\"\\S+\")", 
                                                                                                                       "s.next (\"\\a*\")", 
                                                                                                                       "s.next (\"\\ S *\")",
                                                                                                                       "s.next (\"\\\\ s +\")",
                                                                                                                       "s.next (\"\\ s +\")",
                                                                                                                       "s.nextString (\"\\ s *\")",
                                                                                                                       "s.next (\"\\\\ S +\") ",
                                                                                                                       "s.nextString ()"}, 7, 1));
      
      /*questions.add (new ThisThatQuestion ("Yes or No: Is there a difference between abstract classes and interfaces in Java?", "yes", "no", 0));
      questions.add (new ThisThatQuestion ("Right or wrong: Each class definition must have a constructor?", "right", "wrong", 1));*/
      
      for (Question q : questions) {
        System.out.println (q);
        io.askForResponse();
        processResponse (q);
        io.print_score(scoreStore);
        Thread.sleep(1000);
      }
      
      if (!wrongAs.isEmpty()) {
        io.print_second_chance_message ();
        Thread.sleep(2000);
      }
      for (Question wrongQs : wrongAs) {
        System.out.println (wrongQs);
        io.askForResponse();
        processWrongAsResponse (wrongQs);
        io.print_score(scoreStore);
        Thread.sleep(1000);
      }
    }
    
    private void processResponse (Question q) throws Exception {
      while (true) {
        try {
          if (q.isCorrect (io.getResponse())) {
            io.print_correct_answer_message(scoreStore);
            Thread.sleep(1000);
            scoreStore += q.score;
          }
          else {
            io.print_wrong_answer_message();
            Thread.sleep(1000);
            wrongAs.add(q.duplicate());
          }
          return;       //exit only when this line is reachable without causing exceptions
        }
        catch (NumberFormatException e) {
          io.print_IntegerError_message();
          io.askForResponse();
        }
      }
    }

    private void processWrongAsResponse (Question wrongQ) throws Exception {
      while (true) {
        try {
          if (wrongQ.isCorrect (io.getResponse())) {
            io.print_correct_answer_message(wrongQ.score);
            Thread.sleep(1000);
            scoreStore += wrongQ.score;
          }
          else {
            io.print_wrong_answer_message();
            Thread.sleep(1000);
            io.print_right_answer_message (wrongQ.correctAnswer());
          }
          return;       //exit only when this line is reachable without causing exceptions
        }
        catch (NumberFormatException e) {
          io.print_IntegerError_message();
          io.askForResponse();
        }
      }
    }
}