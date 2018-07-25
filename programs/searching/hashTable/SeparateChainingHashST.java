package searching.hashTable;

import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

//simple ST based on hashing with separate chaining
public class SeparateChainingHashST<Key, Value> {

private ArrayList<KeyValueNext> table; // the actual hash table
private int M;     // length of the hash table (not the number of keys)

private class KeyValueNext {
  private Key key;
  private Value value;
  private KeyValueNext next;
  public KeyValueNext(Key key, Value val, KeyValueNext next){
    this.key = key; this.value = val; this.next = next;
  }
}

public SeparateChainingHashST() {
    M = 31; // typical values of M are 31, 997, 65521, 1048573
    table  = new ArrayList<KeyValueNext>(M);
    for(int i=0; i<M; i++){ table.add(null); }
}

private int hash(Key key){
    return ( key.hashCode() & 0x7fffffff ) % M;
}

public Value get(Key key) { // returns value if key is present, null otherwise
    KeyValueNext p = table.get(hash(key));
    while (p !=  null && !p.key.equals(key)) { p = p.next; }
    if (p == null) { return null; } 
    return p.value;
}

public void put(Key key, Value val) { // updates value of key if key is present,
                                   // adds (key,val) pair if key is not present
    int hash = hash(key);
    KeyValueNext p = table.get(hash);
    while (p !=  null && !p.key.equals(key)) {
       p = p.next;
    }
    if (p !=  null ){ p.value = val; }
    else { table.set(hash, new KeyValueNext(key,val,table.get(hash))); } 
}

public static void main(String[] args)  {
    In infile = new In(args[0]);
    SeparateChainingHashST<String,Integer> st = new SeparateChainingHashST<>();
    while (!infile.isEmpty()) {
        String key = infile.readString();
        Integer freq = st.get(key);
        if (freq != null)
           st.put(key,freq+1);
        else
           st.put(key,1);}
    assert st.show(); // convenient misuse of assertion, not part of the exam
}//End of main

// Not relevant for the exam, but for testing the program
public boolean show(){
    StdOut.println("hashvalue#key:value|...|key:value|");
    for (int i=0; i<M; i++) {
       KeyValueNext nextp = table.get(i);
       StdOut.print(i+"#");                 
       while (nextp != null) {
         StdOut.print(nextp.key+":");
         StdOut.print(nextp.value+"|");
         nextp = nextp.next;
       }
       StdOut.println();
    }
    return true;
}
} // End of class 
