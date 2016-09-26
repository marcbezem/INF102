import java.util.Iterator; import java.util.ArrayList; import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
public class ArrayListST<Key extends Comparable<Key>, Value> {
  
private int N = 0;     // number of key-value pairs         
private ArrayList<Key> keys = new ArrayList<Key>();
private ArrayList<Value> values = new ArrayList<Value>();

public boolean isEmpty() {return N == 0;}
public int     size()    {return N;}
public Iterator<Key> keysIterator() {return keys.iterator();}

public int rank(Key key){ // NB keys are sorted and unique
  int lo = 0, hi = N-1; // works correctly also if symbol table is empty
  while (lo <= hi) { // inv: all keys to the left of lo are smaller than key,
                      // and all keys to the right of hi are larger than key
    int mid = (lo+hi)/2;
    int cmp = key.compareTo(keys.get(mid));
    if (cmp == 0) return mid; // key found at position mid
    if (cmp < 0) {hi = mid-1;}
    else         {lo = mid+1;}
  }
  return lo; // hi+1 = lo <= N and key not present, but "should be" at lo
}
public void put(Key key, Value v) { 
  int maybe = rank(key); // key is or "should be" at position maybe
  if (maybe < N && key.equals(keys.get(maybe))) {values.set(maybe,v);}
  else {keys.add(maybe,key); values.add(maybe,v); N++;}
}
public Value get(Key key){// not to be confused with ArrayList.get 
  int maybe = rank(key); // key is or "should be" at position maybe
  if (maybe < N && key.equals(keys.get(maybe))) {return values.get(maybe);}
  else {return null;} // 
}
public Value del(Key key){// returns value and deletes key-value pair
  int maybe = rank(key); // key is or "should be" at position maybe
  if (maybe < N && key.equals(keys.get(maybe))) {
  Value val = values.get(maybe);
  keys.remove(maybe); values.remove(maybe); // eager delete
  return val;
  }
  else {return null;}
}
public static void main(String[] args)  { 
  ArrayListST<String,Integer> st = new ArrayListST<String,Integer>();
  In infile = new In(args[0]);
  while (!infile.isEmpty()) {
    String key = infile.readString(); 
    Integer i = st.get(key); if (i!=null){st.put(key,i+1);} else {st.put(key,1);}
    assert st.show();
  }
  Iterator<String> iter = st.keysIterator();
  while (iter.hasNext()) {
    String next = iter.next(); 
    StdOut.println(st.get(next) + "\t" + next);
  }
}//End of main

private boolean show(){// used with assert for simple one-line traces
  for (Key k : keys) StdOut.print(k+" "); StdOut.println(); 
  for (Value v : values) StdOut.print(v+" "); StdOut.println();
  return true;
  }

}//End of ArrayListST, based on Algorithms, 4th Edition, Alg. 3.2
