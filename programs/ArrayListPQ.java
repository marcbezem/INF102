import java.util.ArrayList; import java.util.Collections;

public class ArrayListPQ<Key extends Comparable<Key>> { //implements Iterable<Key>{
  
private int N = 0;     // number of items           
private ArrayList<Key> pq = new ArrayList<Key>(); // contents pq[0..N-1]

private int parent(int k) {return (k-1)/2;}
private int leftchild(int k) {return 2*k+1;}
private int rightchild(int k) {return 2*k+2;}
private boolean less(int i, int j){return pq.get(i).compareTo(pq.get(j)) < 0;}
private void exch(int i, int j) {Collections.swap(pq,i,j);}

public void insert(Key v) { pq.add(v); N++; swim(N-1);}

public Key max() {return pq.get(0);} // unchecked precondition !isEmpty()

public Key delMax() {Key max=max(); exch(0,N-1); pq.remove(--N); sink(0); return max;}

private void swim(int k) {
  while (k>0 && less(parent(k),k)) {exch(parent(k),k); k=parent(k);}
}

private void sink(int k) {
  int max = k; 
  for(;;){ 
    if (leftchild(k)<N && less(max,leftchild(k))) max = leftchild(k);
    if (rightchild(k)<N && less(max,rightchild(k))) max = rightchild(k);
    if (max != k) { exch(k,max); k = max;} else break;
  }
}

public boolean isEmpty() {return N == 0;}
public int     size()    {return N;}

public static void main(String[] args){
  ArrayListPQ<Double> pq = new ArrayListPQ<Double>();
  int M = Integer.parseInt(args[0]);
  for(long i=0; true; i++){ // infinite loop with counter
    double r = StdRandom.uniform();
    if (pq.size() < M) {pq.insert(r); continue;}
    if (r >= pq.max()) continue;
    double maxMsmallest = pq.delMax();
    pq.insert(r);
    StdOut.println(" the maximum of the " + M + 
                   " smallest of the first " + i + 
                   " random doubles is " + maxMsmallest);
  }
}//End of main
}//End of ArrayListPQ, based on Algorithms, 4th Edition, Alg. 2.6
