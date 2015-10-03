import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
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

public void show(){show(root); StdOut.println(root.N);}
private void show(Node r){// Depth-first left-to-right tree transversal
  if (r!=null) {
    show(r.left); StdOut.println(r.value + "\t" + r.key); show(r.right);
  }
}
public Value get(Key key){return get(key,root);}
private Value get(Key k, Node r){
  if (r==null) return null;
  int cmp = k.compareTo(r.key);
  if (cmp==0) return r.value;
  if (cmp<0) {return get(k,r.left);} else {return get(k,r.right);}
}
public void put(Key k, Value v) {root = put(k,v,root);}
private Node put(Key k, Value v, Node r) { 
  if (r==null) {return new Node(k,v,1);}
  int cmp = k.compareTo(r.key);
  if (cmp==0) {r.value = v; return r;}
  if (cmp<0) {r.left = put(k,v,r.left);} else {r.right = put(k,v,r.right);}
  r.N = size(r.left) + size(r.right) + 1; // recompute when unwinding recursion
  return r;
}

public static void main(String[] args)  { 
  UBST<String,Integer> st = new UBST<String,Integer>();
  In infile = new In(args[0]);
  while (!infile.isEmpty()) {
    String key = infile.readString(); 
    Integer i = st.get(key); if (i!=null){st.put(key,i+1);} else {st.put(key,1);}
  }
  st.show();
}//End of main
}//End of UBST, based on Algorithms, 4th Edition, Alg. 3.2
