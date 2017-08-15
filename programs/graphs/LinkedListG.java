package graphs;

import fundamentals.bagsQueuesAndStacks.LinkedList_Queue;
import edu.princeton.cs.algs4.StdIn; import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
import java.util.LinkedList; // supports efficient add(0,_) and toString()

public class LinkedListG {
  
private int V; public int V() {return V;} // number of vertices
private int E; public int E() {return E;} // number of edges
private LinkedList<Integer>[] adj;        // adjacency lists

public LinkedListG(In in) {
  V = in.readInt();
  E = in.readInt();
  adj = (LinkedList<Integer>[]) new LinkedList[V];
  for (int v=0; v<V; v++) adj[v] = new LinkedList<Integer>();
  for (int e=0; e<E; e++) {
    int v = in.readInt();
    int w = in.readInt();
    addEdge(v, w);
  }
}

public void addEdge(Integer v, Integer w) {
    adj[v].add(0, w);
    adj[w].add(0,v);}

public String toString(){
  String s = ""; // in the following line we use adj[v].toString()
  for (int v=0; v<V; v++)
      s += (v + " : " + adj[v] + "\n");
  return s;
}

public void dfs(Integer v, boolean[] marked) {
  marked[v] = true;
  for (Integer w : adj[v])
      if (!marked[w])
          dfs(w,marked);
}

public void bfs(LinkedList_Queue<Integer> q, boolean[] marked) {
  while (!q.isEmpty()) {
     Integer v = q.dequeue();
     for (Integer w : adj[v])
         if (!marked[w]) {
         marked[w]=true;
         q.enqueue(w); }
  }
}

public void testdfs(){
  for(;;) { // infinite loop
    boolean[] marked = new boolean[V];
    StdOut.print("Enter node: "); Integer n = StdIn.readInt();
    if (n < 0 || n >= V) break;
    dfs(n, marked);
    for(int v=0; v<V; v++) StdOut.print(marked[v] ? v + " " : ". ");
    StdOut.println();
  }  
}

public void testbfs(){
  for(;;) { // infinite loop
    boolean[] marked = new boolean[V];
    StdOut.print("Enter node: ");
    Integer n = StdIn.readInt();
    if (n < 0 || n >= V) break;
    LinkedList_Queue<Integer> q = new LinkedList_Queue();
    marked[n] = true;
    q.enqueue(n);
    bfs(q, marked);
    for(int v=0; v<V; v++) StdOut.print(marked[v] ? v + " " : ". ");
    StdOut.println();
  }  
}

// meaning of paths: edge v-paths[v] has been used, self loop = source 
public void pathdfs(Integer u, Integer v, Integer[] paths) {
  paths[v] = u; // paths[v] == null means not marked
  for (Integer w : adj[v])
      if (paths[w]==null)
          pathdfs(v, w, paths);
}

public void testpathdfs(){
  for(;;) { // infinite loop
  Integer[] paths = new Integer[V];
    StdOut.print("Enter node: "); Integer n = StdIn.readInt();
    if (n < 0 || n >= V) break;
    pathdfs(n, n, paths); // self loop = source
    for(int v=0; v<V; v++) StdOut.print(paths[v] != null ? paths[v]+" " : ". ");
    StdOut.println();
    for(int v=0; v<V; v++) {
        if (paths[v] != null) {
            int i = v;
            while (i != paths[i]) {
                StdOut.print(i + "-");
                i = paths[i]; }
            StdOut.println(n);
        }
    }
  }  
}

// meaning of paths: edge v-paths[v] has been used, self loop = source 
public void pathbfs(LinkedList_Queue<Integer> q, Integer[] paths) {
  while (!q.isEmpty()) {
     Integer v = q.dequeue();
     for (Integer w : adj[v]) {
         if (paths[w]==null) {
             paths[w] = v;
             q.enqueue(w); }
     }
     pathbfs(q, paths);
  }
}

public void testpathbfs(){
  for(;;) { // infinite loop
  Integer[] paths = new Integer[V];
    StdOut.print("Enter node: ");
    Integer n = StdIn.readInt();
    if (n < 0 || n >= V) break;
    LinkedList_Queue<Integer> q = new LinkedList_Queue();
    q.enqueue(n);
    paths[n] = n; // self loop = source, null means not marked
    pathbfs(q, paths);
    for(int v=0; v<V; v++) StdOut.print(paths[v] != null ? paths[v]+" " : ". ");
    StdOut.println();
    for(int v=0; v<V; v++) {
      if (paths[v] != null) {
        int i = v;
        while (i != paths[i]) {
            StdOut.print(i + "-");
            i = paths[i]; }
      }
    StdOut.println(n);
    }
  }  
}

public int countcc(){
  boolean[] marked = new boolean[V];
  int count = 0;
  for(int v=0; v<V; v++) {
    if (marked[v]) continue;
    count++;
    dfs(v, marked);
  }
  return count;
}

public static void main(String[] args)  {
    LinkedListG g = new LinkedListG(new In(args[0]));
    StdOut.print(g.toString());
    while (true){
    StdOut.print("Enter test (stop/dfs/bfs/pathdfs/pathbfs/countcc): ");
      switch (StdIn.readString()) {
            case "stop"     :  return;

            case "dfs"      :  g.testdfs();
                     break;
            case "bfs"      :  g.testbfs();
                     break;
            case "pathdfs"  :  g.testpathdfs();
                     break;
            case "pathbfs"  :  g.testpathbfs();
                     break;
            case "countcc"  :  StdOut.println(g.countcc() + 
                                        " connected components");
                    break;
           default          :   StdOut.println("test not known");
       }
     }
  }//End of main

public LinkedListG(int V) { // for later use in StringSTG.java
  this.V = V;
  this.E = 0;
  adj = (LinkedList<Integer>[]) new LinkedList[V];
  for (int v=0; v<V; v++) adj[v] = new LinkedList<>();
}

}//End of LinkedListG, based on Algorithms, 4th Edition, Sec. 4.1
