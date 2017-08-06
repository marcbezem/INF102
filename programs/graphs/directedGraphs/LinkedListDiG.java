package graphs.directedGraphs;

import fundamentals.bagsQueuesAndStacks.LinkedList_Queue;
import edu.princeton.cs.algs4.StdIn; import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut; import java.util.Arrays;
import java.util.LinkedList; // supports efficient add(0,_) and toString()

public class LinkedListDiG {
  
private int V; public int V() { return V; } // number of vertices
private int E; public int E() { return E; } // number of edges
private LinkedList<Integer>[] adj;        // adjacency lists

public LinkedListDiG(In in) {
  V = in.readInt();
  E = in.readInt();
  adj = (LinkedList<Integer>[]) new LinkedList[V];
  for (int v=0; v<V; v++) adj[v] = new LinkedList<>();
  for (int e=0; e<E; e++) {
    int v = in.readInt();
    int w = in.readInt();
    addEdge(v,w);
  }
}

public void addEdge(Integer v, Integer w) { adj[v].add(0, w); }

public String toString(){
  String s = ""; // in the following line we use adj[v].toString()
  for (int v=0; v<V; v++)
    s += (v + " : " + adj[v] + "\n");
  return s;
}

public String dfs(Integer v, boolean[] marked) {// return type String !
  marked[v] = true; 
  String sv = v.toString();
  String trace = sv; // only for nice output (ofno)
  for (Integer w : adj[v])
    if ( !marked[w])
      trace += "->" + dfs(w,marked);
  trace += "(" + sv + ")";
  return trace; // ofno: backtrack from "(node)"
}

public void testDfs(){
  for(;;) { // infinite loop
    boolean[] marked = new boolean[V];
    StdOut.print("Enter node: "); Integer n = StdIn.readInt();
    if (n < 0 || n >= V) break;
    StdOut.println(dfs(n, marked));
    for(int v=0; v<V; v++) StdOut.print(marked[v] ? v + " " : ". ");
    StdOut.println();
  }  
}
 
public boolean slowCyclist(Integer u, Integer v, Integer[] paths) {
// call: slowCyclist(s,s,paths) detects a cycle reachable from s
  paths[v] = u;
  for (Integer w : adj[v])
    if (paths[w] == null) {
      if (slowCyclist(v, w, paths))
        return true;
    } else {
      for (int x = v; x != paths[x]; x = paths[x]) // Inefficient! Why?
        // Premature optimization is the root of all evil (D.E. Knuth).
        if (x == w) return true;
    }
  return false;
}

public boolean fastCyclist(Integer u, Integer v, Integer[] paths, boolean[] op) {
  // Efficient! But op ("on path") uses extra space.
  // call: fastCyclist(s, s, paths, op) detects a cycle reachable from s
  op[v] = true;
  paths[v] = u;
  for (Integer w : adj[v]) 
    if (paths[w] == null)
      if (fastCyclist(v, w, paths, op))
        return true;
    else if (op[w]) {
      StdOut.print("at " + w + " ");
      return true; }
  op[v] = false;
  return false;
}

public void testFast(){
  for(;;) { // infinite loop
    boolean[] op = new boolean[V];
    Integer[] paths = new Integer[V];
    StdOut.print("Enter node: ");
    Integer n = StdIn.readInt();
    if (n < 0 || n >= V) return;
    if (fastCyclist(n, n, paths, op)) {
      StdOut.println("cycle in subgraph below " + n);
      StdOut.println(Arrays.toString(paths));      }
    else StdOut.println("no cycle in subgraph below " + n);
  }  
}

public boolean hasCycle(Integer[] paths){
  for(int v=0; v<V; v++)
    if (paths[v]==null && slowCyclist(v, v, paths))
      return true;
  return false;
}

public void testCycle(){
  Integer[] paths = new Integer[V];
  if (hasCycle(paths)) StdOut.println("cycle!");
  else StdOut.println("no cycle"); 
}

public void preDfs(int v, boolean[] marked, LinkedList_Queue<Integer> q) {
  marked[v] = true;
  q.enqueue(v);
  for (int w : adj[v])
    if ( !marked[w]) preDfs(w, marked, q);
}

public void testPre(){
  boolean[] marked = new boolean[V]; 
  LinkedList_Queue<Integer> q = new LinkedList_Queue<>();
  for(int v=0; v<V; v++)
    if (!marked[v])
      preDfs(v, marked, q);
  for (Integer v : q) StdOut.print(v + " ");
  StdOut.println("is the preorder"); 
}

public void postDfs(int v, boolean[] marked, LinkedList_Queue<Integer> q) {
  marked[v] = true;
  for (int w : adj[v])
    if (!marked[w])
      postDfs(w,marked,q);
  q.enqueue(v);
}

public void testPost(){
  boolean[] marked = new boolean[V]; 
  LinkedList_Queue<Integer> q = new LinkedList_Queue<>();
  for(int v=0; v<V; v++)
    if (!marked[v])
      postDfs(v, marked, q);
  for (Integer v: q) StdOut.print(v + " ");
  StdOut.println("is the postorder (topological if digraph acyclic)"); 
}

public boolean[][] transitiveClosure() { // returns reflexive-transitive closure
  boolean[][] adjacencyMatrix = new boolean[V][V];
  for (int v=0; v<V; v++) dfs(v, adjacencyMatrix[v]); // "row of v" as array "marked"
  for (int v=0; v<V; v++) {
    for (int w=0; w<V; w++)
      StdOut.print(adjacencyMatrix[v][w] ? 1 : 0);
      StdOut.println(); }
  return adjacencyMatrix; // V^2 space, reachability test in constant time
}

public static void main(String[] args)  {
  LinkedListDiG dg = new LinkedListDiG(new In(args[0]));
  StdOut.print(dg.toString());
  StdOut.print("Enter testDfs/testFast/testCycle/testPre/testPost/testTC/testExam: ");
  switch (StdIn.readString()) {
            case "testDfs"   :  dg.testDfs();
                     break;
            case "testFast"  :  dg.testFast();
                     break;
            case "testCycle" :  dg.testCycle();
                     break;
            case "testPre"   :  dg.testPre();
                     break;
            case "testPost"  :  dg.testPost();
                     break;
            case "testTC"    :  dg.transitiveClosure();
                     break;
            case "testExam"    :  dg.testExam();
                     break;
           default           :  StdOut.println("test not known");
     }
    
  }//End of main

public boolean examCyclist(Integer v, boolean[] marked, boolean[] op) {
// tests the existence of a cycle reachable from v
  marked[v] = true;
  op[v] = true;
  for (Integer w : adj[v]) {
    if (!marked[w]) {
      if (examCyclist(w, marked, op))
        return true;
    } else {
      if (op[w])
        return true;
    }
  }
  op[v] = false;
  return false;
}

public void testExam(){
  for(;;) { // infinite loop
  boolean[] marked = new boolean[V];
  boolean[] op = new boolean[V];
    StdOut.print("Enter node: ");
    Integer n = StdIn.readInt();
    if (n < 0 || n >= V) return;
    if (examCyclist(n,marked,op)) StdOut.println("cycle in subtree below " + n);
    else StdOut.println("no cycle in subtree below " + n);
  }  
}

}//End of LinkedListDiG, based on Algorithms, 4th Edition, Sec. 4.2
