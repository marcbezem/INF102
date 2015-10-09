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
  String sv = v.toString(); String s = sv; // all new
  for (Integer w : adj[v]) if (! marked[w]) s += "->"+dfs(w,marked); // some new
  s += "(" + sv + ")"; return s; // all new
}


public void testdfs(){
  for(;;) { // infinite loop
    boolean[] marked = new boolean[V];
    StdOut.print("Enter node: "); Integer n = StdIn.readInt();
    if (n < 0 || n >= V) break;
    dfs(n,marked);
    for(int v=0;v<V;v++) StdOut.print(marked[v]?v+" ":". "); 
    StdOut.println();
  }  
}
 
public Integer slowCyclist(Integer u, Integer v, Integer[] paths) {
  paths[v] = u;
  for (Integer w : adj[v]) 
    if (paths[w]==null) {Integer c = slowCyclist(v,w,paths); if (c!=null) return c;} 
    else {int x=v; while (x!=w && x!=paths[x]) x=paths[x]; // inefficient!
          if (x==w) {paths[w]=v; return w;}} // paths[w] updated to v
  return null;
}

public Integer hasCycle(Integer[] paths){//returns node on cycle in paths, null if acyclic
  Integer cycle = null;
  for(int v=0;v<V;v++){
    if (paths[v]==null) { cycle = slowCyclist(v,v,paths); if (cycle != null) break; }
  }
  return cycle;
}

public void testslow(){
  for(;;) { // infinite loop
  Integer[] paths = new Integer[V];
    StdOut.print("Enter node: "); Integer n = StdIn.readInt();
    if (n < 0 || n >= V) return;
    Integer c = slowCyclist(n,n,paths);
    if (c!=null) {
      int x=c; do {StdOut.print(x+"<-"); x=paths[x];} while (x!=c); StdOut.println(c);}
    else StdOut.println("no cycle");
  }  
}

public static void main(String[] args)  {
    LinkedListDiG dg = new LinkedListDiG(new In(args[0]));
    StdOut.print(dg.toString());
    StdOut.print("Enter test (dfs/testslow): ");
    switch (StdIn.readString()) {
            case "dfs"      :  dg.testdfs();
                     break;
            case "testslow"  :  dg.testslow();
                     break;
           default          :   StdOut.println("test not known");
     }
    
  }//End of main

public LinkedListDiG(int V) { // for later use
  this.V = V; this.E = 0; 
  adj = (LinkedList<Integer>[]) new LinkedList[V];
  for (int v=0; v<V; v++) adj[v] = new LinkedList<Integer>();
}

}//End of LinkedListDiG, based on Algorithms, 4th Edition, Sec. 4.2
