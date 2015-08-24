public class NaiveHeapSort{ // better generics with <Key extends ... >
  
private NaiveHeapSort(){} // keep your hands off my class!

public static <Key extends Comparable<Key>> void sort(Key[] a) {
  ArrayListPQ<Key> pq = new ArrayListPQ<Key>();
  for (int i=0; i<a.length; i++) pq.insert(a[i]);
  for (int i=a.length-1; i>=0; i--) a[i] = pq.delMax();
}

private static <Key extends Comparable<Key>> boolean less(Key v, Key w){
  return v.compareTo(w) < 0; }

private static <Key extends Comparable<Key>> void show(Key[] a){
  for (Key o : a) StdOut.print(o+" "); StdOut.println();}

public static <Key extends Comparable<Key>> boolean isSorted(Key[] a){
  for (int i=1; i<a.length; i++) if (less(a[i],a[i-1])) return false;
  return true;}

public static void main(String[] args){
  String[] a = In.readStrings();
  sort(a); show(a); assert isSorted(a); 
}//End of main
}//End of NaiveHeapSort based on Algorithms, 4th Edition

