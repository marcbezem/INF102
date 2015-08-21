public class ArrayListHeapSort<Key extends Comparable<Key>>{ // UGLY generics
  
public void sort(Key[] a) {
  ArrayListPQ<Key> pq = new ArrayListPQ<Key>();
  for (int i=0; i<a.length; i++) pq.insert(a[i]);
  for (int i=a.length-1; i>=0; i--) a[i] = pq.delMax();
}

private boolean less(Key v, Key w){
  return v.compareTo(w) < 0; }

private void show(Key[] a){
  for (Key o : a) StdOut.print(o+" "); StdOut.println();}

public boolean isSorted(Key[] a){
  for (int i=1; i<a.length; i++) if (less(a[i],a[i-1])) return false;
  return true;}

public static void main(String[] args){
  ArrayListHeapSort<String> heapsorter = new ArrayListHeapSort<String>();
  String[] a = In.readStrings();
  heapsorter.sort(a); heapsorter.show(a); assert heapsorter.isSorted(a); 
}//End of main
}//End of ArrayListHeapSort based on Algorithms, 4th Edition

