import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.PrimMST; import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.Edge; import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.LazyPrimMST; 
import edu.princeton.cs.algs4.PrimMST; import edu.princeton.cs.algs4.KruskalMST;

public class CompareMSTs {

public static void run(String[] args, String alg) {
  EdgeWeightedGraph G = new EdgeWeightedGraph(new In(args[0]));
  Stopwatch timer = new Stopwatch();
  switch   (alg) {
            case "LazyPrim"   :  LazyPrimMST mstLP = new LazyPrimMST(G);
                     break;
            case "EagerPrim"  :  PrimMST mstEP = new PrimMST(G);
                     break;
            case "Kruskal"    :  KruskalMST mstK = new KruskalMST(G);
                     break;
           default                 : StdOut.println(alg + " not known");
   }
  StdOut.println("time for " + alg + ": " + timer.elapsedTime());
  }

public static void main(String[] args) {
  run(args, "LazyPrim"); run(args, "EagerPrim"); run(args, "Kruskal"); 
  }//End of main
}//End of CompareMSTs, based on Algorithms, 4th Edition, Sec. 4.3
//Timings for largeEWG.txt: Lazy 9.322, Eager 2.155, Kruskal 11.729
