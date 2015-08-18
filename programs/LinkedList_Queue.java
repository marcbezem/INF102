import java.util.Iterator;

public class LinkedList_Queue<Item> implements Iterable<Item> 
{  
  private Node first; // oldest, first out
  private Node last; // newest, last out
  private int N = 0;//current number of items

  private class Node 
  {  private Item item;
     private Node next;
  }

  public boolean isEmpty(){return first == null;}
  public int     size()   {return N;} 

  public void enqueue(Item item)
  {  Node newlast = new Node(); newlast.item = item; // newlast.next = null;
     if (N == 0) {first = newlast; last = newlast;}
     else {last.next = newlast; last = newlast;}
     N++;
  }

  public Item dequeue() //unchecked precondition first != null, N>0
  {  Item item = first.item;
     if (N == 1) {first = null; last = null;}
     else first = first.next; 
     N--; return item;
  }

  public Iterator<Item> iterator() {return new MyIterator();}

  private class MyIterator implements Iterator<Item> 
  {  private Node p = first; //starts at first (if any)
     public boolean hasNext() {return p!=null;}
     public Item next() {Item it = p.item; p=p.next; return it;}
     public void remove()     {}
  }
  
  public static void main(String[] args)
  {  LinkedList_Queue<String> s = new LinkedList_Queue<String>();
     s.enqueue("hello"); s.enqueue("world"); s.enqueue("goodbye!"); 
     StdOut.println(s.dequeue()); for (String word : s) StdOut.println(word);
  } 
}//End of LinkedList_Queue based on Algorithms, 4th Edition, p. 151

