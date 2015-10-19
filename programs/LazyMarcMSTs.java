import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.PrimMST; import edu.princeton.cs.algs4.StopWatch;
import edu.princeton.cs.algs4.LazyPrimMST; import edu.princeton.cs.algs4.EdgeWeightedGraph; 


public class CompareMSTs {

public static void main(String[] args) {
  EdgeWeightedGraph G = new EdgeWeightedGraph(new In(args[0]));
  LazyPrimMST mst = new LazyPrimMST(G);
  StdOut.println(mst.weight());
   
  }//End of main
}//End of CompareMSTs, based on Algorithms, 4th Edition, Sec. 4.3
