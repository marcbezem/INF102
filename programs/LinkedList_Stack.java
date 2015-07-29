import java.util.Iterator;

public class LinkedList_Stack<Item> implements Iterable<Item> 
{  
  private Node first; // last in, first out
  private int N = 0; //current number of items

  private class Node 
  {  private Item item;
     private Node next;
  }

  public boolean isEmpty(){return first == null;}
  public int     size()   {return N;} 

  public void push(Item item)
  {  Node newfirst = new Node(); newfirst.item = item; newfirst.next = first;
     first = newfirst; N++; 
  }
  public Item pop() //unchecked precondition first != null, N>0
  {  Item top = first.item;
     first = first.next; 
     N--; return top;
  }

@Override public Iterator<Item> iterator() {return new MyIterator();}

  private class MyIterator implements Iterator<Item> 
  {  private Node p = first; //starts at top (if any)
     public boolean hasNext() {return p!=null;}
     public Item next() {Item top = first.item; p=p.next; return top;}
     public void remove()     {}
  }
  
  public static void main(String[] args)
  {  ResizingArray_Stack<String> s = new ResizingArray_Stack<String>();
     s.push("hello"); s.push("world"); s.push("goodbye"); 
     StdOut.println(s.pop()); for (String word : s) StdOut.println(word);
  } 
}//End of LinkedList_Stack based on Algorithms, 4th Edition, p. 149

