public class SlowUF {
  
private int[] id; // id[p] is the identifier of p
private int count; // count is the number of components
private long aacost; // aacost counts the number of array accesses

public SlowUF(int N){
  id = new int[N]; for (int i=0; i<N; i++) id[i]=i;
  aacost = N; // cost of intializing the array
  count = N; // initially, each element is only connected to itself
}

public int count() {return count;}
public long cost() {return aacost;}
public int find(int p) {aacost++; return id[p];}
public boolean connected(int p, int q) {return find(p) == find(q);}
public void union(int p, int q) {
  int idp = find(p);
  int idq = find(q);
  if (idp != idq){ 
    for (int i=0; i<id.length; i++) if (id[i]==idq) {aacost++; id[i]=idp;}
    count--; // one component less
    aacost+=id.length; // cost of the previous N tests id[i]==idp
  }
}

public static void main(String[] args){
  int N = StdIn.readInt();
  SlowUF uf = new SlowUF(N);
  while (!StdIn.isEmpty()){
    int p = StdIn.readInt(); 
    int q = StdIn.readInt();
    if (!uf.connected(p,q)) uf.union(p,q);
    StdOut.println(p+" "+q+" "+uf.count()+" "+uf.cost());
  }
}//End of main
}//End of SlowUF based on Algorithms, 4th Edition, p. 221,222 
