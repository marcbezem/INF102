package searching.hashTable;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import searching.elementarySymbolTables.ArrayListST;
import java.util.Iterator;

public class ArrayListHashST<Key extends Comparable<Key>, Value> {
  
private int M;     // size of hash table, NOT number of keys in ST (Symbol Table)
private ArrayListST<Key,Value>[] st; // array of ArrrayListST objects

    public ArrayListHashST(int M) {
    this.M = M; // typical values of M are 31, 997, 65521, 1048573
    st  = (ArrayListST<Key,Value>[]) new ArrayListST[M]; // array generics :-(
    for (int i=0; i<M; i++)
        st[i] = new ArrayListST<>();
    }

    private int hash(Key key){
        return ( key.hashCode() & 0x7fffffff ) % M;
    }

    public Value get(Key key){
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value v) {
        st[hash(key)].put(key,v);
    }

    public static void main(String[] args)  {
      int M, f;
      if (args.length < 2) { M = 65521; f=0; }
      else { M = Integer.parseInt(args[0]); f=1; }
      In infile = new In(args[f]);
        ArrayListHashST<String,Integer> st = new ArrayListHashST<>(M);
        while (!infile.isEmpty()) {
            String key = infile.readString();
            Integer i = st.get(key);
            if (i != null)
                st.put(key,i+1);
            else
                st.put(key,1);}
        assert st.show(); // convenient misuse of assertion
    }//End of main

    public boolean show(){
        StdOut.println("hashvalue#length: keys (only if non-empty)");
        for (int i=0; i<M; i++) {
            int n = st[i].size();
            if (n>0) {
                StdOut.print(i+"#"+n+":\t");
                Iterator<Key> iter = st[i].keysIterator();
                while (iter.hasNext()) {
                    Key next = iter.next();
                    StdOut.print(next+" "); }
                StdOut.println();
            }
        }
        return true;
    }
}//End of ArrayListHashST, based on Algorithms, 4th Edition, Alg. 3.6
