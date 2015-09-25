import java.util.ArrayList; import java.util.Iterator;

public class StringSTG {
  
private int V;   public int V() {return V;} // number of vertices
private int E;   public int E() {return E;} // number of edges
private ArrayListST<String,Integer> strnr;  // key -> index
private String[]                    nrstr;  // index -> key
private LinkedListG                     G;  // isomorphic graph on indices

public StringSTG(In in) {
  V = 0; // as yet unknown, we need V to construct nrstr and G
  ArrayList<String> a = new ArrayList(); // temporarily save the input
  strnr = new ArrayListST<String,Integer>();
  while (!in.isEmpty()) {
    String v = in.readString(); 
    a.add(v); // v saved as part of edge
    if (strnr.get(v) == null) strnr.put(v,V++); // build symbol table
  }
  E = a.size()/2;
  nrstr = new String[V];
  G = new LinkedListG(V);
  for (int i=0; i<a.size(); i+=2){
    String ai = a.get(i), aj = a.get(i+1);
    nrstr[strnr.get(ai)] = ai; nrstr[strnr.get(aj)] = aj; // build the index
    G.addEdge(strnr.get(ai),strnr.get(aj)); // add the edge to the graph on indices
  }
}
public String toString(){
  Iterator<String> iter = strnr.keysIterator();
  String s = "";
  while (iter.hasNext()) { 
    String next = iter.next(); 
    s += (next + " : " + strnr.get(next) + "\n");
  }
  return s;
}

public static void main(String[] args)  {
    StringSTG g = new StringSTG(new In(args[0]));
    StdOut.print(g.toString());
 //   g.testdfs();
 //   g.testpathdfs();
  
}//End of main
}//End of LinkedListG, based on Algorithms, 4th Edition, Sec. 4.1
