import java.util.Iterator;
public class UBST<Key extends Comparable<Key>, Value> {

private Node root;

private class Node{
  private Key key; private Value value; private int N;
  private Node left, right;
  public Node(Key k, Value v, int n){this.key = k; this.value = v; this.N = n;}
}  

public boolean isEmpty() {return root==null;}
public int size() {return size(root);}
private int size(Node r) {if (r==null){return 0;} else {return r.N;}}
//public Iterator<Key> keysIterator() {return keys.iterator();}

public Value get(Key key){return get(key,root);}
private Value get(Key k, Node r){
  if (r==null) return null;
  int cmp = k.compareTo(r.key);
  if (cmp==0) return r.value;
  if (cmp<0) {return get(k,r.left);} else {return get(k,r.right);}
}

public void put(Key k, Value v) {put(k,v,root);}
private void put(Key k, Value v, Node r) { 
  if (r==null) {r = new Node(k,v,1); return;}
  int cmp = k.compareTo(r.key);
  if (cmp==0) {r.value = v; return;}
  if (cmp<0) {put(k,v,r.left);} else {put(k,v,r.left);}
  r.N = size(r.left) + size(r.right) + 1; // recompute when unwinding recursion
}

public static void main(String[] args)  { 
  UBST<String,Integer> st = new UBST<String,Integer>();
  while (!StdIn.isEmpty()) {
    String key = StdIn.readString(); 
    Integer i = st.get(key); if (i!=null){st.put(key,i+1);} else {st.put(key,1);}
  }
 // Iterator<String> iter = st.keysIterator();
 // while (iter.hasNext()) {
 //   String next = iter.next(); 
 //   StdOut.println(st.get(next) + "\t" + next);
 // }
}//End of main
}//End of UBST, based on Algorithms, 4th Edition, Alg. 3.2
