import java.util.Iterator; import java.util.ArrayList;
public class ArrayListHashST<Key extends Comparable<Key>, Value> {
  
private int M;     // size of hash table
private ArrayListST<Key,Value>[] st; // array of ArrrayListST objects

public ArrayListHashST(int M){
  this.M = M;
  st  = (ArrayListST<Key,Value>[]) new ArrayListST[M]; // array generics :-(
  for (int i=0; i<M; i++) st[i] = new ArrayListST<Key,Value>();
}

//public boolean isEmpty() {return N == 0;}
//public int     size()    {return N;}

private int hash(Key key){ return ( key.hashCode() & 0x7fffffff ) % M; }

public Value get(Key key){ return st[hash(key)].get(key); }

public void put(Key key, Value v) { st[hash(key)].put(key,v); }

public static void main(String[] args)  {
  int M = Integer.parseInt(args[0]);
  ArrayListHashST<String,Integer> st = new ArrayListHashST<String,Integer>(M);
  while (!StdIn.isEmpty()) {
    String key = StdIn.readString(); 
    Integer i = st.get(key); if (i!=null){st.put(key,i+1);} else {st.put(key,1);}
  }
  assert st.show();
}//End of main

public boolean show(){
  for (int i=0; i<M; i++) {
    Iterator<Key> iter = st[i].keysIterator();
    while (iter.hasNext()) {
      Key next = iter.next(); 
      StdOut.println(i + "\t" + st[i].get(next) + "\t" + next);
    }
  }
return true;
}
}//End of ArrayListHashST, based on Algorithms, 4th Edition, Alg. 3.6
