package fundamentals.unionFind;

import edu.princeton.cs.algs4.StdIn; import edu.princeton.cs.algs4.StdOut;
public class FastUFanimated{
  
private int[] id; // id[p] is the identifier of p
private int N; // length of id
private int count; // count is the number of components

public FastUFanimated(int N){
  id = new int[N];
  for (int i=0; i<N; i++) id[i]=i;
  this.N = N;
  count = N; // initially, each element is only connected to itself
}

public int count() {return count;}

private void showln(int[] a, String message) {
  for (int i=0; i<a.length; i++) StdOut.print(a[i]+" ");
  StdOut.println(message);
}

public void show() {
  int[] aux = new int[N];
  for (int i=0; i<N; i++) aux[i] = i;
  int auxi;
  boolean done = false;
  showln(id," id with encoded pointer structure");
  showln(aux," positions");
  while (!done){
    done = true;
    for (int i=0; i<N; i++) {
      auxi = aux[i]; 
      if (auxi != id[auxi]) {
        StdOut.print(id[auxi]+" ");
        done = false;
        aux[i] = id[auxi];}
      else StdOut.print("  ");
    }
    StdOut.println();
  } 
}

public int find(int p){
  while (p != id[p]) { p=id[p]; } // one aa for the test, one in the loop
  return p;
}

public boolean connected(int p, int q) { return find(p) == find(q); }

public void union(int p, int q) {
  int idp = find(p);
  int idq = find(q);
  if (idp != idq){ 
    id[idp] = idq;
    count--; // one component less
  }
}

public static void main(String[] args){
  int N = StdIn.readInt();
  FastUFanimated uf = new FastUFanimated(N);
  while (!StdIn.isEmpty()){
    int p = StdIn.readInt(); 
    int q = StdIn.readInt();
    if (!uf.connected(p,q)) uf.union(p,q);
    uf.show();
  }
}//End of main
}//End of FastUF based on Algorithms, 4th Edition, p. 221,224
//last p q count aacost: 943868 727438 6 561290740942
