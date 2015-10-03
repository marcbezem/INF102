import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
public class TopDownMergeSort {
  
public  static void mergesort(Comparable[] a, int lo, int hi, Comparable[] aux) {
  if (hi==lo) return; // length one array is sorted
  int mid = (lo + hi)/2; // in the "middle"
  mergesort(a,lo,mid,aux); // left "half"
  mergesort(a,mid+1,hi,aux); // right "half"
  assert show(a); // misuse of assert, don't do this
  merge(a,lo,mid+1,hi,aux); // merge the two sorted parts
}

public static void merge(Comparable[] a,  int lo, int m, int hi, Comparable[] aux) {
  for (int k=lo; k<=hi; k++) aux[k] = a[k]; // copy a[lo..hi] to aux[lo..hi]
  int l = lo, r = m; // running markers, inv: done with a[lo..l) and a[m..r)
  for (int k=lo; k<=hi; k++){ // inv: k == l+(r-m), k is sum of progress of l and r
    if (l==m) {a[k] = aux[r++]; continue;} // done with a[lo..m-1]
    if (r==hi+1) {a[k] = aux[l++]; continue;} // done with a[m..hi]
    if (less(aux[l],aux[r])) {a[k] = aux[l++];} else {a[k] = aux[r++];} // max N compares
  } 
}

public  static void sort(Comparable[] a) {
  int N = a.length;
  Comparable[] aux = new Comparable[N];
  if (N >1) mergesort(a,0,N-1,aux);
}  

private static boolean less(Comparable v, Comparable w){
  return v.compareTo(w) < 0; }

private static void exch(Comparable[] a, int i, int j){
  Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

private static boolean show(Comparable[] a){// can be used with assert
  for (Comparable o : a) StdOut.print(o+" "); StdOut.println(); return true;}

public static boolean isSorted(Comparable[] a){
  for (int i=1; i<a.length; i++) if (less(a[i],a[i-1])) return false;
  return true;}

public static void main(String[] args){
  String[] a = In.readStrings();
  sort(a); assert isSorted(a); show(a);
}//End of main
}//End of TopDownMergeSort based on Algorithms, 4th Edition, p. 273

