import edu.princeton.cs.algs4.StdIn; import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
import java.util.LinkedList; // supports efficient add(0,_) and toString()

public class LinkedListDiG {
  
private int V; public int V() {return V;} // number of vertices
private int E; public int E() {return E;} // number of edges
private LinkedList<Integer>[] adj;        // adjacency lists

public LinkedListDiG(In in) {
  V = in.readInt(); E = in.readInt(); 
  adj = (LinkedList<Integer>[]) new LinkedList[V];
  for (int v=0; v<V; v++) adj[v] = new LinkedList<Integer>();
  for (int e=0; e<E; e++) {
    int v = in.readInt();
    int w = in.readInt();
    addEdge(v,w);
    }
}

public void addEdge(Integer v, Integer w) {adj[v].add(0,w);}

public String toString(){
  String s = ""; // in the following line we use adj[v].toString()
  for (int v=0; v<V; v++) s += (v + " : " + adj[v] + "\n");
  return s;
}

public String dfs(Integer v, boolean[] marked) {// return type String !
  marked[v] = true; 
  String sv = v.toString(); String trace = sv; // all new
  for (Integer w : adj[v]) if (! marked[w]) trace += "->"+dfs(w,marked); // some new
  trace += "(" + sv + ")"; return trace; // all new
}

public void testDfs(){
  for(;;) { // infinite loop
    boolean[] marked = new boolean[V];
    StdOut.print("Enter node: "); Integer n = StdIn.readInt();
    if (n < 0 || n >= V) break;
    StdOut.println(dfs(n,marked));
    for(int v=0;v<V;v++) StdOut.print(marked[v]?v+" ":". "); 
    StdOut.println();
  }  
}
 
public boolean slowCyclist(Integer u, Integer v, Integer[] paths) {
  paths[v] = u;
  for (Integer w : adj[v]) 
    if (paths[w]==null) {if (slowCyclist(v,w,paths)) return true;}
    else {for (int x=v; x!=paths[x]; x=paths[x]) // Inefficient! Why?
           if (x==w) return true;}
  return false;
}

public void testSlow(){
  for(;;) { // infinite loop
  Integer[] paths = new Integer[V];
    StdOut.print("Enter node: "); Integer n = StdIn.readInt();
    if (n < 0 || n >= V) return;
    if (slowCyclist(n,n,paths)) StdOut.println("cycle in subtree below " + n);
    else StdOut.println("no cycle in subtree below " + n);
  }  
}

public boolean hasCycle(Integer[] paths){
  for(int v=0;v<V;v++) if (paths[v]==null && slowCyclist(v,v,paths)) return true;
  return false;
}

public void testCycle(){
  Integer[] paths = new Integer[V];
  if (hasCycle(paths)) StdOut.println("cycle!");
  else StdOut.println("no cycle"); 
}

public static void main(String[] args)  {
    LinkedListDiG dg = new LinkedListDiG(new In(args[0]));
    StdOut.print(dg.toString());
    StdOut.print("Enter test (testDfs/testSlow/testCycle): ");
    switch (StdIn.readString()) {
            case "testDfs"   :  dg.testDfs();
                     break;
            case "testSlow"  :  dg.testSlow();
                     break;
            case "testCycle"   :  dg.testCycle();
                     break;
           default           :   StdOut.println("test not known");
     }
    
  }//End of main

public LinkedListDiG(int V) { // for later use
  this.V = V; this.E = 0; 
  adj = (LinkedList<Integer>[]) new LinkedList[V];
  for (int v=0; v<V; v++) adj[v] = new LinkedList<Integer>();
}

}//End of LinkedListDiG, based on Algorithms, 4th Edition, Sec. 4.2
