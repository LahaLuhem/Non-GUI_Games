import java.util.*;

/**
 * A class that implements a breadth-first search algorithm
 * for finding the Configurations for which the isSolution predicate holds
 * @author Pieter Koopman, Sjaak Smetsers
 * @version 1.5
 * @date 25-02-2017
 */
public class Solver {
  // A queue for maintaining graphs that are not visited yet.
  PriorityQueue<Configuration> toExamine;
  ArrayList<Configuration> solution;
  Hashtable<Integer, Configuration> hashTable;

    public Solver( Configuration g ) {
      toExamine = new PriorityQueue<>();
      toExamine.add(g);
      solution = new ArrayList<>();
      hashTable = new Hashtable<>();
    }

    /**
     * A skeleton implementation of the solver
     *
     * @return a string representation of the solution
     */
    public String solve() {
      while ( ! toExamine.isEmpty() ) {
        Configuration curr = toExamine.remove();
          if ( curr.isSolution() ) {
            solution = (ArrayList<Configuration>) curr.pathFromRoot();
            printSol();
            return "Success!";
          }
          else {
            hashTable.put(curr.hashCode(), curr);
              for (Configuration succ : curr.successors())
                  if (!hashTable.containsKey (succ.hashCode())) toExamine.add(succ);
          }
      }
        return "No Solution Found! :(";
    }

    private void printSol() {
      for (Configuration config : solution) {
        System.out.println (config);
      }
    }   
}