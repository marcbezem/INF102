import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut; import edu.princeton.cs.algs4.Stopwatch; 
import java.util.Arrays;

public class ThreeSumOptimized{

public static int countZeroSumTriples(int[] a) {
  int N = a.length;
  Arrays.sort(a); 
  int counter = 0;
  for (int i = 0; i < N; i++)
    for (int j = i+1; j < N; j++){
      int left = myBinarySearch("leftmost", -(a[i] + a[j]), a, j+1, N-1);
      if (left == -1) continue;
      int right = myBinarySearch("rightmost", -(a[i] + a[j]), a, j+1, N-1);
      counter += right - left + 1;
    }
  return counter;
}

public static int myBinarySearch(String s, int key, int[] a, int lo, int hi){
// binary search for leftmost or rightmost position of key in a[lo..hi]
  while (lo <= hi) {
    int mid = (s=="leftmost") ? (lo+hi)/2 : (lo+hi+1)/2 ;
    if (key == a[lo] && s == "leftmost") return lo;
    if (key == a[hi] && s == "rightmost") return hi;
    if (key == a[mid] && s == "leftmost") return myBinarySearch(s, key, a, lo, mid);
    if (key == a[mid] && s == "rightmost") return myBinarySearch(s, key, a, mid, hi);
    if (key < a[mid]) return myBinarySearch(s, key, a, lo, mid-1);
    if (key > a[mid]) return myBinarySearch(s, key, a, mid+1, hi);
  }
  return -1;
} 

public static void main(String[] args)  { 
  In infile = new In(args[0]);
  int[] a = infile.readAllInts();
  Stopwatch timer = new Stopwatch();
  int numberOfZeroSumTriples = countZeroSumTriples(a);
  StdOut.println("elapsed time including sorting = " + timer.elapsedTime());
  StdOut.println(numberOfZeroSumTriples);
}//End of main
}//End of ThreeSumOptimized, based on Algorithms, 4th Edition, ThreeSum.java
