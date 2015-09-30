package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Compilation: javac Deque.java Dependencies: algs4.jar, java.util.Iterator,
 * java.util.NoSuchElementException This program is an API to used to create a double-ended queue or
 * deque (pronounced "deck") which is a generalization of a stack and a queue that supports adding
 * and removing items from either the front or the back of the data structure. Author : Shalini
 */

public class Deque<Item> implements Iterable<Item> {

  private int N; // number of elements on queue
  private Node<Item> first; // beginning of queue
  private Node<Item> last; // end of queue

  private static class Node<Item> {
    private Item item;
    private Node<Item> next;
    private Node<Item> prev;

  }

  /**
   * Initializes an empty queue.
   */
  public Deque() {
    first = null;
    last = null;
    N = 0;
  }

  // is the deque empty?
  public boolean isEmpty() {
    return N == 0;
  }

  // return the number of items on the deque
  public int size() {
    return N;

  }

  // add the item to the last
  public void addLast(Item item) {

    if (item == null) {
      throw new NullPointerException("Item Null");
    } else {

      if (isEmpty()) {
        last = new Node<Item>();
        first = last;
      } else {
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.prev = oldLast;
        oldLast.next = last;

      }
      N++;
    }
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new NullPointerException("Item Null");
    } else {

      if (isEmpty()) {
        first = new Node<Item>();
        first.item = item;
        last = first;
      } else {
        Node<Item> oldFrist = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFrist;
        oldFrist.prev = first;
      }
      N++;
    }
  }


  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue underflow");
    } else {
      Item item = first.item;
      if (size() == 1) {
        first = last;
      } else {
        first = first.next;
        first.prev = null;
      }
      N--;
      if (isEmpty()) {
        first = null;
        last = null; // to avoid loitering
      }

      return item;
    }

  }

  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue underflow");
    } else {
      Item item = last.item;
      if (size() == 1) {
        last = first;
      } else {
        last = last.prev;
        last.next = null;
      }
      N--;
      if (isEmpty()) {
        first = null;
        last = null; // to avoid loitering
      }

      return item;
    }

  }

  // return an iterator over items
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<Item> {
    private Node<Item> current;

    public DequeIterator() {
      current = first;
    }

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException("UnsupportedOperationException");
    }

    public Item next() {
      if (!hasNext())
        throw new NoSuchElementException("Queue underflow");
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

  /*
   * private String toString() { StringBuilder s = new StringBuilder(); for (Item item : this)
   * s.append(item + " "); return s.toString(); }
   */

  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<Integer>();
    // System.out.println("size: " + deque.size());

    deque.addFirst(1);
    deque.addFirst(2);
    deque.addFirst(3);
    deque.addFirst(4);
    deque.addFirst(5);


    // System.out.println("deque: " + deque.toString());

    deque.removeLast();
    // System.out.println("deque: " + deque.toString());

    deque.removeFirst();
    deque.removeFirst();
    // System.out.println("deque: " + deque.toString());
    // System.out.println("size: " + deque.size());

    deque.removeLast();
    deque.removeLast();
    // System.out.println("deque: " + deque.toString());

    deque.addFirst(1);
    deque.addLast(2);
    // System.out.println("deque: " + deque.toString());

    deque.addFirst(3);
    deque.addLast(4);
    // System.out.println("deque: " + deque.toString());

    System.out.println("size: " + deque.size());
    Iterator itr = deque.iterator();
    System.out.println(itr.next());
    System.out.println(itr.next());
    System.out.println(itr.next());
    System.out.println(itr.next());
  }
}
