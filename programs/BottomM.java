public class BottomM{
//keeps the M smallest of random Doubles, prints the smallest and its number 

public static void main(String[] args){
  ArrayListPQ<Double> pq = new ArrayListPQ<Double>();
  int M = Integer.parseInt(args[0]);
  for(int i=0; true; i++){ // infinite loop with counter
    double r = StdRandom.uniform();
    if (pq.size() < M) {pq.insert(r); continue;}
    if (r >= pq.max()) continue;
    pq.delmax();
    pq.insert(r);
    StdOut.println("the minimum of the first " + i + " is " + r);
  }
}//End of main
}//End of BottomM, a variation on TopM from Algorithms, 4th Edition, p. 311

