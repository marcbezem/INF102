package oddsAndEnds;

import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class MultiwayMerge {// uses MinPQ, remembers stream number, no IndexMinPQ
  
public static void merge(In[] streams) {
  int N = streams.length;
  MinPQ<PolyPair<String,Integer>> pq = new MinPQ<>(N);
  for (int i=0; i<N; i++)
    if (!streams[i].isEmpty()) pq.insert(new PolyPair(streams[i].readString(), i));
  while (!pq.isEmpty()){
    PolyPair<String,Integer> x = pq.delMin();
    String s = x.getFst();
    Integer i = x.getSnd();
    StdOut.print(s + " ");
    if (!streams[i].isEmpty()) pq.insert(new PolyPair(streams[i].readString(), i));
  }
  StdOut.println();
}

public static void main(String[] args){
  int N = args.length;
  In[] streams = new In[N];
  for (int i=0; i<N; i++) streams[i] = new In(args[i]);
  merge(streams);
}//End of main
}//End of MultiwayMerge, a variation of Multiway, Algorithms - 4th Edition, p. 322

