import java.util.LinkedList; import java.util.Iterator;

public class StringSTG {
  
private int V; public int V() {return V;} // number of vertices
private int E; public int E() {return E;} // number of edges
private ArrayListST<String,LinkedList<String>> adj; // adjacency lists

public StringSTG(In in) {
  V = 0; E = 0; 
  adj = new ArrayListST<String,LinkedList<String>>();
  while (!in.isEmpty()) {
    String v = in.readString();
    if (adj.get(v) == null) {adj.put(v,new LinkedList<String>()); V++;}
    String w = in.readString();
    if (adj.get(w) == null) {adj.put(w,new LinkedList<String>()); V++;}
    addEdge(v,w); E++;
    }
}

public void addEdge(String v, String w) {adj.get(v).add(0,w); adj.get(w).add(0,v);}

//public Iterator<String> nodeIterator() {return adj.keysIterator();}

public String toString(){
  Iterator<String> iter = adj.keysIterator();
  String s = "";
  while (iter.hasNext()) { 
    String next = iter.next(); 
    s += (next + " : " + adj.get(next) + "\n");
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
