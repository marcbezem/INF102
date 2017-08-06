import java.util.Arrays; import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;

public class JavaArraySort {
  
public static void sort(Comparable[] a){ // Java's own sort
  java.util.Arrays.sort(a);
}

private static boolean less(Comparable v, Comparable w){
  return v.compareTo(w) < 0; }

private static void exch(Comparable[] a, int i, int j){
  Comparable t = a[i];
  a[i] = a[j];
  a[j] = t; }

private static void show(Comparable[] a){
  for (Comparable o : a) StdOut.print(o+" "); StdOut.println();}

public static boolean isSorted(Comparable[] a){
  for (int i=1; i<a.length; i++)
    if (less(a[i],a[i-1]))
      return false;
  return true;}

public static void main(String[] args){
  String[] a = In.readStrings();
  sort(a); assert isSorted(a); show(a);
}//End of main
}//End of JavaArraySort based on Algorithms, 4th Edition, p. 245

