package oddsAndEnds;// Problem 1 of exam 27.11.2015
// standard solution with a triple nested loop is like ThreeSum.java
// bonus solution (first two methods suffice) is as follows

import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut; import java.util.Arrays;

public class ThreeSumPos{

public static int countPosSumTriples(int[] a) {
  int N = a.length;
  Arrays.sort(a); 
  int counter = 0;
  for (int i = 0; i < N-2; i++) {
    for (int j = i + 1; j < N - 1; j++) {
      int maybe = myBinarySearch(-(a[i] + a[j]), a, j + 1, N - 1);
      if (j <= maybe && maybe < N - 1) counter += N - 1 - maybe; // this one was difficult!
      StdOut.println(a[i] + " + " + a[j] + "\t right of pos " + maybe + "\t yields " + counter);
    }
  }
  return counter;
}

public static int myBinarySearch(int key, int[] a, int lo, int hi){
// pre: 0 <= lo <= hi < N 
// binary search for position where key should occur in a[lo..hi] (if at all)
  int mid = -1; 
  while (lo <= hi) {
    mid = (lo + hi) / 2;
    if (key == a[mid]) return mid;
    if (key < a[mid]) hi = mid - 1;
    if (key > a[mid]) lo = mid + 1;
  }
  return (key > a[mid]) ? lo : hi ; // this one was difficult!
} 

public static void main(String[] args)  { 
  In infile = new In(args[0]);
  int[] a = infile.readAllInts();
  int count = countPosSumTriples(a);
  StdOut.println(count);
}//End of main
}//End of ThreeSumPos, based on Algorithms, 4th Edition, ThreeSum.java
