import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
public class BottomUpMergeSort {
  
// public static void merge(): similar to merge() in TopDownMergeSort,
// one subtlety (assert) to be explained after sort()

public  static void sort(Comparable[] a) {
  int N = a.length;
  Comparable[] aux = new Comparable[N];
  for (int i=1; i<N; i*=2){
  // inv: a[0..i-1],a[i..2i-1],...,a[ni..N-1] all sorted
    assert show(a); // misuse of assert, don't do this
    for (int j=0; i*(j+1) < N; j+=2) // i*j = 0,2i,4i,6i,... with i,3i,5i,7i,... < N
      merge(a,i*j,i*(j+1),Math.min(N-1,i*(j+2)-1),aux);
  }
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
  sort(a); show(a); assert isSorted(a); 
}//End of main

private static void merge(Comparable[] a,  int lo, int m, int hi, Comparable[] aux) {
  for (int k=lo; k<=hi; k++) aux[k] = a[k];
  int l = lo, r = m;
  for (int k=lo; k<=hi; k++){
    if (l==m) {assert a[k] == aux[r++]; continue;} // assert!!!
    if (r==hi+1) {a[k] = aux[l++]; continue;}
    if (less(aux[l],aux[r])) {a[k] = aux[l++];} else {a[k] = aux[r++];}
  } 
}
}//End of BottomUpMergeSort based on Algorithms, 4th Edition, p. 278

