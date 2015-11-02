import edu.princeton.cs.algs4.StdIn; import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.DirectedEdge; import java.util.LinkedList;

public class LinkedListEWD {
  
private int V; public int V() {return V;} // number of vertices
private int E; public int E() {return E;} // number of edges
private LinkedList<DirectedEdge>[] adj;   // adjacency lists with weighted out-edges
private double[] distToSource;            // weight of path to source
private DirectedEdge[] pathToSource;      // path to source

public LinkedListEWD(In in) {
  V = in.readInt(); E = in.readInt(); 
  adj = (LinkedList<DirectedEdge>[]) new LinkedList[V];
  distToSource = new double[V];
  pathToSource = new DirectedEdge[V];
  for (int v=0; v<V; v++) {
    adj[v] = new LinkedList<DirectedEdge>();
    distToSource[v] = Double.POSITIVE_INFINITY;
  } 
  for (int e=0; e<E; e++) { addEdge(new DirectedEdge(in.readInt(),in.readInt(),in.readDouble())); }
}

private void addEdge(DirectedEdge e) {adj[e.from()].add(0,e);}

public String toString(){
  String s = ""; // in the following line we use adj[v].toString()
  for (int v=0; v<V; v++) s += (v + " : " + adj[v] + "\n");
  return s;
}

public void slowSP (int s) {
  // Premature optimization is the root of all evil (D.E. Knuth)
  boolean[] marked = new boolean[V];
  distToSource[s] = 0.0;
  for(;;) { // infinite loop, will be left when all reachable nodes are marked
    double minDistance = Double.POSITIVE_INFINITY; 
    int minUnmarked = 0; // next loop: find unmarked node with minimum distance, slow
    for(int v=0; v<V; v++) {
       double dTSv = distToSource[v];
       if (!marked[v] && dTSv < minDistance) { minUnmarked = v; minDistance = dTSv; }
    }
    if  (minDistance == Double.POSITIVE_INFINITY) break; // no reachable unmarked nodes left
    for(DirectedEdge e: adj[minUnmarked]) { // update distance and path for all neighbours
      int w = e.to(); double ew = e.weight();
      if (distToSource[w] > minDistance + ew) { distToSource[w] = minDistance + ew; pathToSource[w] = e; }
    }
    marked[minUnmarked] = true;
  }
}

public void testSlow(){
  for(;;) {
    StdOut.print("Enter source: "); Integer s = StdIn.readInt();
    if (s < 0 || s >= V) break; else slowSP(s);
    for(;;) {
      StdOut.print("Enter target: "); Integer t = StdIn.readInt();
      if (t < 0 || t >= V) break; else {
        DirectedEdge e = pathToSource[t];
        while (e != null) { StdOut.println(e); t = e.from(); e = pathToSource[t];}
      }
    }
  }  
}

public static void main(String[] args)  {
  LinkedListEWD ewd = new LinkedListEWD(new In(args[0]));
  StdOut.print(ewd.toString());
  StdOut.print("Enter test: (slowSP) ");
  switch (StdIn.readString()) {
            case "slowSP"   :  ewd.testSlow();
                     break;
            
           default           :  StdOut.println("test not known");
     }
    
  }//End of main
}//End of LinkedListEWD, based on Algorithms, 4th Edition, Sec. 4.4
