package searching.balancedSearchTree;

import edu.princeton.cs.algs4.StdOut;

public class TwoThreeTree<Key extends Comparable<Key>> {

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

public TwoThreeTree(Key k1, Key k2, Node l, Node m, Node r) {
  root = new Node(k1, k2, l, m, r); }

public Node search(Key k, Node r){
  assert k != null; // defensive, not required
  if (r == null) return null;
  int cmp = k.compareTo(r.key1);
  if (cmp == 0) return r;
  if (cmp < 0) return search(k, r.left);
  // now we know cmp>0
  if (r.key2 == null) return search(k, r.right);
  // now we know r.key2!=null
  cmp = k.compareTo(r.key2);
  if (cmp == 0) return r;
  if (cmp < 0) return search(k, r.mid);
  return search(k, r.right);
}

public boolean contains(Key k){
  return null != search(k, root); }

public Key max(Node r){
  if (r==null) return null;
  if (r.right == null)
    if (r.key2 == null) return r.key1;
  else return r.key2;
  return max(r.right);  
}

public static void main(String[] args)  {
  new TwoThreeTree(0,1,null,null,null);
  TwoThreeTree<Integer> child = new TwoThreeTree(0,null,null,null,null); 
  TwoThreeTree<Integer> tree = new TwoThreeTree(2,4,child.root,null,null);
  for (int i=0;i<6;i++) StdOut.println("" + i + tree.contains(i));
}//End of main

}//End of TwoThreeTree

