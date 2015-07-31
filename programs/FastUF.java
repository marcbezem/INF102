public class FastUF {
  
private int[] id; // id[p] is the identifier of p
private int count; // count is the number of components
private long aacost; // aacost counts the number of array accesses

public FastUF(int N){
  id = new int[N]; for (int i=0; i<N; i++) id[i]=i;
  aacost = N; // cost of intializing the array
  count = N; // initially, each element is only connected to itself
}

public int count() {return count;}
public long cost() {return aacost;}
public int find(int p){
  while (p != id[p]) {p=id[p]; aacost+=2;} // one aa for the test, one in the loop
  aacost++; // one aa for failing test
  return p;
}
public boolean connected(int p, int q) {return find(p) == find(q);}
public void union(int p, int q) {
  int idp = find(p);
  int idq = find(q);
  if (idp != idq){ 
    id[idp] = idq;
    aacost++;
    count--; // one component less
  }
}

public static void main(String[] args){
  int N = StdIn.readInt();
  FastUF uf = new FastUF(N);
  while (!StdIn.isEmpty()){
    int p = StdIn.readInt(); 
    int q = StdIn.readInt();
    if (!uf.connected(p,q)) uf.union(p,q);
    StdOut.println(p+" "+q+" "+uf.count()+" "+uf.cost());
  }
}//End of main
}//End of FastUF based on Algorithms, 4th Edition, p. 221
//last p q count aacost: 943868 727438 6 561290740942
