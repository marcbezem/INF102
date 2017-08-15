package sorting.priorityQueues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListPQ<Key extends Comparable<Key>> { //implements Iterable<Key>{

  private int numberOfItems = 0;     // number of items
  private ArrayList<Key> pq = new ArrayList<>(); // contents pq[0..numberOfItems-1]

  private int parent(int key) { return (key-1)/2; }
  private int leftChild(int key) { return 2*key+1; }
  private int rightChild(int key) { return 2*key+2; }

  private boolean less(int i, int j) {
    return pq.get(i).compareTo(pq.get(j)) < 0;
  }
  private void exch(int i, int j) {
    Collections.swap(pq,i,j);
  }

  public void insert(Key key) {
    pq.add(key);
    numberOfItems++;
    swim(numberOfItems - 1);
  }

  public Key max() { return pq.get(0); } // unchecked precondition !isEmpty()

  public Key delMax() {
    Key max=max();
    exch(0, numberOfItems - 1);
    pq.remove(--numberOfItems);
    sink(0);
    return max;
  }

  private void swim(int key) {
    while (key>0 && less(parent(key), key)) {
      exch(parent(key),key);
      key = parent(key);
    }
  }

  private void sink(int key) {
    int max = key;
    while (true) {
      if (leftChild(key) < numberOfItems && less(max, leftChild(key))) max = leftChild(key);
      if (rightChild(key) < numberOfItems && less(max, rightChild(key))) max = rightChild(key);
      if (max != key) {
        exch(key,max);
        key = max;
      } else break;
    }
  }

  public boolean isEmpty() {return numberOfItems == 0;}
  public int     size()    {return numberOfItems;}

  public static void main(String[] args){
    ArrayListPQ<Double> pq = new ArrayListPQ<>();
    int M = Integer.parseInt(args[0]);
    for(long i=0; true; i++){ // infinite loop with counter
      double r = StdRandom.uniform();
      if (pq.size() < M) {
        pq.insert(r);
        continue; }
      if (r >= pq.max()) continue;
      double maxMsmallest = pq.delMax();
      pq.insert(r);
      StdOut.println(" the maximum of the " + M +
              " smallest of the first " + i +
              " random doubles is " + maxMsmallest);
    }
  }//End of main
}//End of ArrayListPQ, based on Algorithms, 4th Edition, Alg. 2.6
