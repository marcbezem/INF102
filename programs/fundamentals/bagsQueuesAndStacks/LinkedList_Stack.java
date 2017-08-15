package fundamentals.bagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut; import java.util.Iterator;

public class LinkedList_Stack<Item> implements Iterable<Item> 
{  
  private Node first; // last in, first out
  private int N = 0; //current number of items

  private class Node 
  {  private Item item;
     private Node next;
  }

  public boolean isEmpty() { return first == null; }
  public int     size()    { return N; }

  public void push(Item item) {
    Node newfirst = new Node();
    newfirst.item = item;
    newfirst.next = first;
    first = newfirst;
    N++;
  }

  public Item pop() //unchecked precondition first != null, N>0
  {  Item top = first.item;
     first = first.next; 
     N--;
     return top;
  }

  public Iterator<Item> iterator() { return new MyIterator(); }

  private class MyIterator implements Iterator<Item> {
     private Node p = first; //starts at top (if any)
     public boolean hasNext() { return p != null; }
     public void remove()     {}
     public Item next() {
         Item it = p.item;
         p = p.next;
         return it; }
  }
  
  public static void main(String[] args)
  {  LinkedList_Stack<String> s = new LinkedList_Stack<>();
     s.push("hello");
     s.push("world");
     s.push("goodbye");
     StdOut.println(s.pop());
     for (String word : s) StdOut.println(word);
  } 
}//End of LinkedList_Stack based on Algorithms, 4th Edition, p. 149

