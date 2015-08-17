public class QuickSort {
  
public static void sort(Comparable[] a) {
  StdRandom.shuffle(a); quicksort(a,0,a.length-1);
}

public static void quicksort(Comparable[] a, int lo, int hi) {
  if (lo>=hi) return;
  int m = partition(a,lo,hi);
  quicksort(a,lo,m-1);
  quicksort(a,m+1,hi);
}

public static int partition(Comparable[] a, int lo, int hi) {
  Comparable v = a[lo];
  int l=lo, h=hi;
  for(;;){ // inv: if lo<=i<=l, then leq(a[i],v); if h<j<=hi, then less(v,a[j]) 
    while (less(v,a[h])) h--; // inv: l<=h
    while (l<h && less(a[l+1],v)) l++; // inv: l<=h
 // postcondition: l<=h && !less(v,a[h]) && !(l<h && less(a[l+1],v))  
    if (l+1<h) {exch(a,l+1,h); l++;} else break;
  }
  exch(a,l,lo); // not always needed
  return l;  
}

private static boolean less(Comparable v, Comparable w){
  return v.compareTo(w) < 0; }

private static void exch(Comparable[] a, int i, int j){
  Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

private static void show(Comparable[] a){
  for (Comparable o : a) StdOut.print(o+" "); StdOut.println();}

public static boolean isSorted(Comparable[] a){
  for (int i=1; i<a.length; i++) if (less(a[i],a[i-1])) return false;
  return true;}

public static void main(String[] args){
  String[] a = In.readStrings();
  sort(a); show(a); assert isSorted(a); 
}//End of main
}//End of QuickSort based on Algorithms, 4th Edition, p. 278

