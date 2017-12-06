package searching.hashTable;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

//simple ST based on hashing with linear probing
public class LinearProbingHashST<Key extends Comparable<Key>, Value> {
  
private int M;     // length of hash table, NOT number of keys
private Key[] keys;
private Value[] values;
private int size;  // number of keys, INVARIANT: size < M-1 (at least one empty place)

public LinearProbingHashST() {
    M = 31; // typical values of M are 31, 997, 65521, 1048573
    keys  = (Key[]) new Comparable[M];
    values  = (Value[]) new Comparable[M];
    size = 0;
}

private int hash(Key key){
    return ( key.hashCode() & 0x7fffffff ) % M;
}

private int getpos(Key key){ // returns position where key should be
    int pos = hash(key);
    while (keys[pos]!=  null && keys[pos].compareTo(key)!=0) {
       pos = (pos+1)%M; 
    }
return pos; // correct since there is always one empty position
}

public Value get(Key key) {
    return values[getpos(key)]; 
}

public void put(Key key, Value v) { // updates value of key if key is present,
// adds (key,v) pair if key is not present and size < M-1,
// prints message "hashtable capacity exceeded" otherwise.
     int pos = getpos(key);
     if ( keys[pos]!=null ) { values[pos] = v;}
     else if (size < M-1) { keys[pos] = key; values[pos] = v; size++; }
          else {StdOut.println("hashtable capacity exceeded");}
}

public static void main(String[] args)  {
    In infile = new In(args[0]);
    LinearProbingHashST<String,Integer> st = new LinearProbingHashST<>();
    while (!infile.isEmpty()) {
        String key = infile.readString();
        Integer i = st.get(key);
        if (i != null)
           st.put(key,i+1);
        else
           st.put(key,1);}
    assert st.show(); // convenient misuse of assertion, not part of the exam
}//End of main

// Not relevant for the exam, but for testing the program
public boolean show(){
    StdOut.println("hashvalue#length: keys (only if non-empty)");
    for (int i=0; i<M; i++) {                   
       if (this.keys[i] != null) StdOut.println(i + " " + this.keys[i] + " " + this.values[i]);
    }
    return true;
}
} // End of class 
