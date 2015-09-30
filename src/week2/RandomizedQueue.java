package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;


/**
 * Compilation: javac RandomizedQueue.java 
 * Dependencies: algs4.jar, java.util.Iterator,java.util.NoSuchElementException 
 * This program is an API to used to create a randomized queue is similar to a
 * stack or queue, except that the item removed is chosen uniformly at random 
 * from items in the data structure. 
 * Author : Shalini
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] a; // array of items
  private int N; 
  private int size = 0;// number of elements in the queue

  // construct an empty randomized queue
  public RandomizedQueue() {
    a = (Item[]) new Object[2];
  }

  // is the queue empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the queue
  public int size() {
    return size;
  }

  // resize the underlying array holding the elements
  private void resize(int capacity) {
    Item[] temp = (Item[]) new Object[capacity];
    int j = 0;
    for (int i = 0; i < N; i++) {
      if (a[i] != null) {
        temp[j++] = a[i];
      }
    }
    N = j;
    a = temp;

  }


  public void enqueue(Item item) {
    if (item == null) {
      throw new NullPointerException("Item Null");
    } else {
      if (N == a.length - 1) {
        resize(2 * a.length); // double size of array if necessary
      }
      a[N++] = item; 
      size++;// add item
    }
  }

  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue underflow");
    } else {
      int random = StdRandom.uniform(N);
      while (this.a[random] == null) {
        random = StdRandom.uniform(N);
      }
      Item item = a[random];
      a[random] = null; // to avoid loitering
      size--;
      // shrink size of array if necessary
      if (size > 0 && size == a.length / 4) {
        resize(a.length / 2);
      }
      return item;
    }
  }

  public Item sample() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack underflow");
    } else {
      int random = StdRandom.uniform(N);
      while (this.a[random] == null) {
        random = StdRandom.uniform(N);
      }
      return a[random];

    }
  }


  // return an iterator over items
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item> {

    private Item[] b = (Item[]) new Object[size];
    private int current = 0;
    
    public RandomizedQueueIterator() {
        for (int i = 0; i < N; i++) {
           if (a[i] != null) {
            b[current++] = a[i];
           }
        }
        StdRandom.shuffle(b);
        current = 0;
    }

    public boolean hasNext() {
      return current < size;
    }

    public void remove() {
      throw new UnsupportedOperationException("UnsupportedOperationException");
    }

    public Item next() {
      if (!this.hasNext())    {   throw new NoSuchElementException();         }
      else {
          return b[current++];
      }
      
  }
  }
  
  /*public String toString() {
    StringBuilder s = new StringBuilder();
    for (Item item : this)
      s.append(item + " ");
    return s.toString();
  }*/
 
  public static void main(String[] args) {
    RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

    rq.enqueue(0);
    rq.enqueue(1);
    rq.enqueue(2);
    rq.enqueue(3);
    rq.enqueue(4);
    rq.enqueue(5);
    rq.enqueue(6);
    rq.enqueue(7);
    rq.enqueue(8);
    rq.enqueue(9);



    System.out.println("items: " + rq.size());

  //  System.out.println(rq.toString());

    System.out.println(rq.dequeue());
    System.out.println(rq.dequeue());
    System.out.println(rq.dequeue());

    //System.out.println(rq.to`String());
    System.out.println("items: " + rq.size());

    Iterator it1 = rq.iterator();
    Iterator it2 = rq.iterator();

    while (it1.hasNext()) {
        System.out.print(it1.next());
    }
    System.out.println("\n");
    while (it2.hasNext()) {
        System.out.print(it2.next());
    }
}
}
