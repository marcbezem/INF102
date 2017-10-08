package searching.elementarySymbolTables;

import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

// an incomplete implementation of unbalanced ternary trees,
// to be used (and modified) in a compulsory exercise 
// no values, no delete, all internal nodes are 3-nodes

public class UTST<Key extends Comparable<Key>> {

private Node root;

private class Node{
  private Key key1; 
  private Key key2;
  private Node left, mid, right;
  public Node(Key k1, Key k2, Node l, Node m, Node r){
    key1 = k1;
    key2 = k2;
    assert key1 != null && (key2 == null || key1.compareTo(key2) < 0);
    left = l;
    mid = m;
    right = r;
    assert key2 != null || mid==null ;
  }
}

public void show(){ show(root); }
private void show(Node r){// depth-first left-to-right tree transversal
  if (r != null) {
    show(r.left);
    StdOut.println(r.key1);
    show(r.mid);
    StdOut.println(r.key2);
    show(r.right); }
}

public Node get(Key k){ return get(k,root); }
public Node get(Key k, Node r){
  assert k != null; // defensive, not required
  if (r == null) return null;
  int cmp = k.compareTo(r.key1);
  if (cmp == 0) return r;
  if (cmp < 0) return get(k, r.left);
  // now we know cmp>0
  if (r.key2 == null) return get(k, r.right);
  // now we know r.key2!=null
  cmp = k.compareTo(r.key2);
  if (cmp == 0) return r;
  if (cmp < 0) return get(k, r.mid);
  return get(k, r.right);
}


public void put(Key k) { root = put(k, root); }
private Node put(Key k, Node r) { 
  if (r == null) { return new Node(k, null, null, null, null); }
  int cmp = k.compareTo(r.key1);
  if (cmp == 0) { return r; }
  if (cmp < 0) { 
    if (r.key2 != null) { r.left = put(k, r.left); }
    else { r.key2 = r.key1; r.key1 = k ; }
  }
  else {
    if (r.key2 != null) { 
       cmp = k.compareTo(r.key2);
       if (cmp == 0) { return r; }
       if (cmp < 0) { r.mid = put(k, r.mid); }
       else { r.right = put(k, r.right); }
    }
    else { r.key2 = k; }
  }
  return r;
}

public static void main(String[] args)  { 
  UTST<String> st = new UTST<String>();
  In infile = new In(args[0]);
  while (!infile.isEmpty()) {
    String key = infile.readString(); 
    st.put(key);
  }
  st.show();
}//End of main

}//End of UTST
