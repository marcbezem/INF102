package fundamentals.bagsQueuesAndStacks;

import fundamentals.bagsQueuesAndStacks.ResizingArray_Stack;
import edu.princeton.cs.algs4.StdOut; import edu.princeton.cs.algs4.StdIn;

public class Parentheses{

  private static boolean matchingparentheses(char l, char r){
    return ((l=='(' && r==')') || (l=='[' && r==']') || (l=='{' && r=='}'));
  }
      
  public static void main(String[] args){
     ResizingArray_Stack<Character> stack;
     String string = "notnullnorempty";
     while (!string.isEmpty()){
       stack = new ResizingArray_Stack<>();
       StdOut.println("Enter string of parentheses (,),[,]{,}, empty to halt: ");
       string = StdIn.readLine(); 
       boolean balanced = true;
       for (char c : string.toCharArray()){
         if (c=='(' || c=='[' || c=='{') { stack.push(c); }
         else {
           if (stack.isEmpty()) {
               balanced = false;
               break; }
           else {
             char top = stack.pop();
             if (!matchingparentheses(top, c)) {
                 balanced=false;
                 break; }
           }
         }
       }
       if (balanced && stack.isEmpty()) {StdOut.println("is balanced");} 
       else                              StdOut.println("is unbalanced or illegal");
     }
     StdOut.println("but empty, so we are done");
  } 
}
