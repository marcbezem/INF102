import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList; import java.util.Iterator;

public class StringSTG {
  
private int V;   public int V() {return V;} // number of vertices
private int E;   public int E() {return E;} // number of edges
private ArrayListST<String,Integer> strnr;  // key -> index
private String[]                    nrstr;  // index -> key
private LinkedListG                     G;  // isomorphic graph on indices

public StringSTG(In in) {
// Read and save input and build symbol table
  V = 0; // as yet unknown, we need V to construct nrstr and G
  ArrayList<String> a = new ArrayList(); // to save the input temporarily 
  strnr = new ArrayListST<String,Integer>();
  while (!in.isEmpty()) {
    String v = in.readString(); 
    a.add(v); // v saved as part of edge
    if (strnr.get(v) == null) strnr.put(v,V++); // build symbol table
  }
// Print symbol table
  StdOut.println("Symbol table:"); 
  Iterator<String> iter = strnr.keysIterator();
  while (iter.hasNext()) {
    String next = iter.next(); 
    StdOut.println(next + "\t" + strnr.get(next));
  }
// Build index and isomorphic graph on indices (using saved input)
  E = a.size()/2;
  nrstr = new String[V];
  G = new LinkedListG(V);
  for (int i=0; i<a.size(); i+=2){
    String ai = a.get(i), aj = a.get(i+1);
    Integer nrai = strnr.get(ai); Integer nraj = strnr.get(aj);
    nrstr[nrai] = ai; nrstr[nraj] = aj; // build the index
    G.addEdge(nrai,nraj); // add the edge to the graph on indices
  }
// Print index
  StdOut.println("Index:"); 
  for (int v=0; v<V; v++) StdOut.println(v + "\t" + nrstr[v]);
}

public void stringpathdfs(String s){
  Integer[] paths = new Integer[V];
  LinkedList_Queue<Integer> q = new LinkedList_Queue();
  Integer n = strnr.get(s); q.enqueue(n); paths[n] = n; 
  G.pathbfs(q,paths);
  StdOut.println("Possible travels to " + s);
  for(int v=0;v<V;v++) if (paths[v]!=null) {
    int i = v;
    while (i!=paths[i]) { StdOut.print(nrstr[i]+"-"); i = paths[i]; }
    StdOut.println(nrstr[n]);
  }  
}

public static void main(String[] args)  {
  StringSTG g = new StringSTG(new In(args[0]));
  g.stringpathdfs("ORD");
  g.stringpathdfs("JFK");
  }//End of main
}//End of StringSTG, based on Algorithms, 4th Edition, Sec. 4.1
