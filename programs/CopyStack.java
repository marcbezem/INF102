import edu.princeton.cs.algs4.StdOut; import edu.princeton.cs.algs4.StdIn;

// pop is a destructive read of the top element of a non-empty stack
// push(top) would restore the stack, but this is cumbersome
// an iterator makes non-destructive read of the whole stack easier
// we use the standard top-down iterator for copying a stack of strings
// by making a reverse copy of a reverse copy
// strings are immutable objects and need no deep copy to get independence
// mutable stack elements would need a deep copy for independence 


public class CopyStack{

  public static ResizingArray_Stack<String> reverse_copy(ResizingArray_Stack<String> s){
    ResizingArray_Stack<String> reverse_copy = new ResizingArray_Stack<>();
    for (String str : s ) reverse_copy.push(str); 
    return reverse_copy;
  }

  public static ResizingArray_Stack<String> copy(ResizingArray_Stack<String> s){
  return reverse_copy(reverse_copy(s));
  }
      
  public static void main(String[] args){
     ResizingArray_Stack<String> s = new ResizingArray_Stack<>();
     s.push(" hello");
     s.push(" world");
     s.push("goodbye");
     ResizingArray_Stack<String> t = copy(s);

     // we now test independence of s and t
     for (String word : t)
         StdOut.print(word);
     StdOut.println(" : t has same content as s");

     s.push("s_top ");
     for (String word : t)
         StdOut.print(word);
     StdOut.println(" : t is still the same");

     t.push("t_top ");
     for (String word : s)
         StdOut.print(word);
     StdOut.println(" : s is independent from t");

     s.pop();
     s.pop(); // this does not change t
     for (String word : t)
         StdOut.print(word);
     StdOut.println(" : t is independent from s");
  } 
}
