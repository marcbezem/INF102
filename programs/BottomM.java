import edu.princeton.cs.algs4.StdOut; import edu.princeton.cs.algs4.StdRandom;
public class BottomM{
//keeps the M smallest of random Doubles, prints the largest of these M smallest

public static void main(String[] args){
  ArrayListPQ<Double> pq = new ArrayListPQ<>();
  int M = Integer.parseInt(args[0]);
  for(long i=0; true; i++){ // infinite loop with counter
    double r = StdRandom.uniform();
    if (pq.size() < M) {
      pq.insert(r);
      continue;
    }
    if (r >= pq.max()) continue;
    double maxMsmallest = pq.delMax();
    pq.insert(r);
    StdOut.println(" the maximum of the " + M + 
                   " smallest of the first " + i + 
                   " random doubles is " + maxMsmallest);
  }
}//End of main
}//End of BottomM, a variation on TopM from Algorithms, 4th Edition, p. 311

