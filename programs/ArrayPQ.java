public class ArrayPQ<Key extends Comparable<Key>> implements Iterable<Key>{
  private int N;     // number of items           
  private Key[] pq;  // pq[0] auxiliary, pq[1..N] contents
}
public class ArrayPQ(int cap) {N=0; pq=(Key[]) new Object[cap+1];}


public void swim(int k) { // parent of k>1 is k/2
  while (k>1 && less(pq[k/2],pq[k]) {exch(pq[k/2],pq[k]); k=k/2;}

private void sink(int k) { // children of k are 2k,2k+1 if <= N
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }

private static boolean less(Key v, Key w){return v.compareTo(w) < 0; }
private static void exch(Key[] a, int i, int j){Key t = a[i]; a[i] = a[j]; a[j] = t; }


public static void main(String[] args)  { 
  In infile = new In(args[0]);
  int[] a = infile.readAllInts();
  Stopwatch timer = new Stopwatch();
  int numberOfZeroSumTriples = countZeroSumTriples(a);
  StdOut.println("elapsed time including sorting = " + timer.elapsedTime());
  StdOut.println(numberOfZeroSumTriples);
}//End of main
}//End of ArrayPQ, based on Algorithms, 4th Edition, 2.4
