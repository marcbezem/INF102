import edu.princeton.cs.algs4.StdIn; import edu.princeton.cs.algs4.StdOut;

public class WeightedUF {
  
private int[] id; // id[p] is the identifier of p
private int count; // count is the number of components
private long aacost; // aacost counts the number of array accesses
private int[] sz; // sz[p] is the size of the tree if p is its root

public WeightedUF(int N){
  id = new int[N];
  for (int i=0; i<N; i++) id[i]=i;
  sz = new int[N];
  for (int i=0; i<N; i++) sz[i]=1;
  aacost = 2 * N; // cost of intializing the arrays
  count = N; // initially, each element is only connected to itself
}

public void show() { for (int n : id) StdOut.print(n); }
public int count() { return count; }
public long cost() { return aacost; }
public int find(int p){ // same as in FastUF
  while (p != id[p]) {
    p=id[p];
    aacost+=2; }
  aacost++;
  return p;
}
public boolean connected(int p, int q) { return find(p) == find(q); }

public void union(int p, int q) { // NEW: uses array sz
  int idp = find(p); 
  int idq = find(q); 
  if (idp != idq){
    int szp = sz[idp];
    int szq = sz[idq];
    if (szp < szq) {
      id[idp] = idq;
      sz[idq] = szp + szq; }
    else {
      id[idq] = idp;
      sz[idp] = szp + szq; }
    aacost += 4; // one id and three sz (why?)
    count--; // one component less
  }
}

public static void main(String[] args){
  int N = StdIn.readInt();
  WeightedUF uf = new WeightedUF(N);
  while (!StdIn.isEmpty()){
    int p = StdIn.readInt(); 
    int q = StdIn.readInt();
    if (!uf.connected(p, q)) uf.union(p, q);
//  StdOut.println(p+" "+q+" "+uf.count()+" "+uf.cost());
    StdOut.print(p + " " + q + " ");
    uf.show();
    StdOut.println(" #components: " + uf.count());
  }
   StdOut.println("# array accesses: " + uf.cost());
}//End of main
}//End of WeightedUF based on Algorithms, 4th Edition, p. 228
//largeUF.txt: last p q count aacost = 943868 727438 6 328912302594
