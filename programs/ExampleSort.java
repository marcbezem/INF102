import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
public class ExampleSort {
  
public static void sort(Comparable[] a){ // good old bubblesort
  for (int i=a.length-1; i>0; i--)
     for (int j=1; j<=i; j++)
        if (less(a[j], a[j-1]))
            exch(a,j-1, j);
}

private static boolean less(Comparable v, Comparable w){
  return v.compareTo(w) < 0; }

private static void exch(Comparable[] a, int i, int j){
  Comparable t = a[i]; // pointer assignments!
  a[i] = a[j];
  a[j] = t;
}

private static void show(Comparable[] a){
  for (Comparable o : a) StdOut.print(o+" "); StdOut.println();}

public static boolean isSorted(Comparable[] a){
  for (int i=1; i<a.length; i++)
      if (less(a[i],a[i-1]))
          return false;
  return true; }

public static void main(String[] args){
  String[] a = In.readStrings();
  sort(a);
  assert isSorted(a);
  show(a);
}//End of main
}//End of ExampleSort based on Algorithms, 4th Edition, p. 245

