package sorting;

import edu.princeton.cs.algs4.StdOut;

public class MySort{

public static void insertionSort(int[] a){
  for(int i=1; i<a.length; ++i){
    int j = i;
    while (j > 0 && a[j] < a[j-1]) {
      int k=a[j-1];
      a[j-1]= a[j];
      a[j--]=k;
    }
  }
}

public static void main(String[] args)  {
  int N=1000000;
  int[] a = new int[N];
  StdOut.println("start linear");
  for (int i=0; i<N; i++) a[i] = i;
  insertionSort(a);
  StdOut.println("end linear");
  StdOut.println("start quadratic");
  for (int i=0; i<N; i++) a[i] = N - i;
  insertionSort(a);
  StdOut.println("end quadratic");
}//End of main

}//End of MySort
