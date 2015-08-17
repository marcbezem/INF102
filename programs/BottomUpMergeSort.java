public class BottomUpMergeSort {
  
public static void merge(Comparable[] a,  int lo, int m, int hi, Comparable[] aux) {
  for (int k=lo; k<=hi; k++) aux[k] = a[k];
  int l = lo, r = m;
  for (int k=lo; k<=hi; k++){ // assert (l+r-k == m);
    if (l==m) {assert a[k] == aux[r++]; continue;} // assert!!!
    if (r==hi+1) {a[k] = aux[l++]; continue;}
    if (less(aux[l],aux[r])) {a[k] = aux[l++];} else {a[k] = aux[r++];} // max N compares
  } 
}

public  static void sort(Comparable[] a) {
  int N = a.length;
  Comparable[] aux = new Comparable[N];
  for (int i=1; i<N; i*=2)
  // inv: a[0..i-1],a[i..2i-1],...,a[ni..N-1] all sorted
    for (int j=0; i*(j+1) < N; j+=2) 
      merge(a,i*j,i*(j+1),Math.min(N-1,i*(j+2)-1),aux);
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
}//End of BottomUpMergeSort based on Algorithms, 4th Edition, p. 278

