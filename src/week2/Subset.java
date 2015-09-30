/**
 * 
 */
package week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author Shalini
 *
 */
public class Subset {

  /**
   * @param args
   */
  public static void main(String[] args) {

    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    int k = Integer.parseInt(args[0]);
    String[] input = StdIn.readStrings();

    for (int i = 0; i < input.length; i++) {
      queue.enqueue(input[i]);
    }

    while (k > 0) {
      StdOut.println(queue.dequeue());
      k--;
    }
  }

}
