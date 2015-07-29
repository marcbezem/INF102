import java.util.Iterator;

public class LinkedList_Queue<Item> implements Iterable<Item> 
{  
  private Node first; // first in, first out
  private Node last; // last in, last out
  private int N = 0;//current number of items

  private class Node 
  {  private Item item;
     private Node next;
  }

  public boolean isEmpty(){return first == null;}
  public int     size()   {return N;} 

  public void enqueue(Item item)
  {  Node newlast = new Node(); newlast.item = item; newlast.next = last;
     last = newlast; N++; 
  }

  public Item dequeue() //unchecked precondition first != null, N>0
  {  Item item = first.item;
     first = first.next; 
     N--; return item;
  }

  public Iterator<Item> iterator() {return new MyIterator();}

  private class MyIterator implements Iterator<Item> 
  {  private Node p = last; //starts at last (if any)
     public boolean hasNext() {return p!=null;}
     public Item next() {Item top = first.item; p=p.next; return top;}
     public void remove()     {}
  }
  
  public static void main(String[] args)
  {  ResizingArray_Stack<String> s = new ResizingArray_Stack<String>();
     s.push("hello"); s.push("world"); s.push("goodbye"); 
     StdOut.println(s.pop()); for (String word : s) StdOut.println(word);
  } 
}//End of LinkedList_Queue based on Algorithms, 4th Edition, p. 151

