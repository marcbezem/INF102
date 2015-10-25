import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.PrimMST; import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.Edge; import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.LazyPrimMST; import java.util.Iterator;
import edu.princeton.cs.algs4.PrimMST; import edu.princeton.cs.algs4.KruskalMST;

public class CompareMSTs {

public static void run(String[] args, String alg) {
  EdgeWeightedGraph G = new EdgeWeightedGraph(new In(args[0]));
  Iterator<Edge> edges;
  Stopwatch timer = new Stopwatch();
  switch   (alg) {
            case "LazyPrim"   :  LazyPrimMST mstLP = new LazyPrimMST(G); edges = mstLP.edges().iterator();
                     break;
            case "EagerPrim"  :  PrimMST mstEP = new PrimMST(G); edges = mstEP.edges().iterator();
                     break;
            case "Kruskal"    :  KruskalMST mstK = new KruskalMST(G); edges = mstK.edges().iterator();
                     break;
           default                 : StdOut.println(alg + " not known"); edges = null;
   }
  StdOut.println("time for " + alg + ": " + timer.elapsedTime());
  assert show(edges); // convenient misuse of assert
  }

public static boolean show(Iterator<Edge> edges){
  while (edges.hasNext()) StdOut.println(edges.next());
  return true;
}

public static void main(String[] args) {
// run with java -ea to see the edges
run(args, "LazyPrim"); run(args, "EagerPrim"); run(args, "Kruskal"); 
  }//End of main
}//End of CompareMSTs, based on Algorithms, 4th Edition, Sec. 4.3
