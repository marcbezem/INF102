import java.util.Iterator;

public class ResizingArray_Stack<Item> implements Iterable<Item> 
{  
  private Item[] a = (Item[]) new Object[1]; //cast to generic array in Java
  private int N = 0; //current number of items

  public boolean isEmpty(){return N == 0;}
  public int     size()   {return N;} 

  private void resize(int max) //unchecked precondition: N<=max
  {  Item[] temp = (Item[]) new Object[max]; //cast to generic array in Java
     for (int i=0; i<N; i++) temp[i] = a[i];
     a = temp;
  }

  public void push(Item item)
  {  if (N == a.length) resize(2*N);
     a[N++] = item;  
  }
  public Item pop() //unchecked precondition N>0
  {  Item top = a[--N];
     a[N] = null; //remove an obsolete pointer; why??
     if (N>0 && N == a.length/4) resize(2*N); //what if a.length is 10?
     return top;
  }

  public Iterator<Item> iterator() {return new MyIterator();}

  private class MyIterator implements Iterator<Item> 
  {  private int i = N; //starts at top (if any)
     public boolean hasNext() {return i>0;}
     public Item next()       {return a[--i];}
     public void remove()     {}
  }
  
  public static void main(String[] args)
  {  ResizingArray_Stack<String> s = new ResizingArray_Stack<String>();
     s.push("hello"); s.push("world"); s.push("goodbye"); 
     StdOut.println(s.pop()); for (String word : s) StdOut.println(word);
  } 
}//End of ResizingArrayStack based on Algorithms, 4th Edition, p. 141

