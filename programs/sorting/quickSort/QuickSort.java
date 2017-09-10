package sorting.quickSort;

import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
public class QuickSort { // better generics than in the book
  
public static <Key extends Comparable<Key>> void sort(Key[] a) {
  // StdRandom.shuffle(a);  // bad for demo, not needed in randomized experiments
  quicksort(a,0,a.length-1);
}

public static <Key extends Comparable<Key>> void quicksort(Key[] a, int lo, int hi) {
  if (lo >= hi) return;
  int m = partition(a, lo, hi);
  assert show(m, a); // misuse of assert, good for demo
  quicksort(a, lo,m - 1);
  quicksort(a,m+1, hi);
}

public static <Key extends Comparable<Key>> int partition(Key[] a, int lo, int hi) {
  Key v = a[lo];
  int l=lo, h=hi;
  for(;;){ // inv: if lo<=i<=l, then !less(v,a[i]); if h<j<=hi, then less(v,a[j]) 
    while (less(v,a[h])) h--; // inv: l<=h
    while (l<h && !less(v, a[l+1])) l++; // inv: l<=h
 // postcondition: l<=h && !less(v,a[h]) && !(l<h && less(a[l+1],v))  
    if (l + 1 < h) {
      exch(a,l+1,h);
      l++; }
    else break;
  }
  exch(a, l, lo); // not always needed
  return l;  
}

private static <Key extends Comparable<Key>> boolean less(Key v, Key w){
  return v.compareTo(w) < 0; }

private static <Key extends Comparable<Key>> void exch(Key[] a, int i, int j){
  Key t = a[i];
  a[i] = a[j];
  a[j] = t; }

private static <Key extends Comparable<Key>> boolean show(int m, Key[] a){
  StdOut.print("pivot position " + m + " in \t");
  for (Key key : a) StdOut.print(key + " ");
  StdOut.println();
  return true;
}

public static <Key extends Comparable<Key>> boolean isSorted(Key[] a){
  for (int i=1; i<a.length; i++)
    if (less(a[i],a[i-1]))
      return false;
  return true;
}

public static void main(String[] args){
  String[] a = In.readStrings();
  sort(a);
  assert isSorted(a);
}//End of main
}//End of QuickSort based on Algorithms, 4th Edition, p. 278

