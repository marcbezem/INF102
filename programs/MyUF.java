import java.util.LinkedList; import java.util.ArrayList; import edu.princeton.cs.algs4.StdOut;

public class MyUF {
  
private int[] id; // id[p] is the identifier of p

public MyUF(int N){
  id = new int[N];
  for (int i=0; i<N; i++) id[i] = i;
}

public int find(int p) { return id[p]; }

public void union(int p, int q) {
  int idp = find(p);
  int idq = find(q);
  if (idp != idq) 
    for (int i=0; i<id.length; i++)
      if (id[i] == idp)
        id[i]=idq;
}

public boolean isComponentId(int p) { return p == id[p]; }

public LinkedList<Integer> componentIdList(){
  LinkedList<Integer> l = new LinkedList<>();
  for (int i=0; i<id.length; i++)
    if (isComponentId(i))
      l.add(0,i);
  return l;
}

public void showComponents(){
  LinkedList<Integer> component_ids = componentIdList();
  for (Integer j : component_ids) {
    for (int i=0; i<id.length; i++)
      if (id[i]==j) StdOut.print(i+"\t");
    StdOut.println();
  }
}  

public void fastComponents(){
  LinkedList<Integer> component_ids = componentIdList();
  ArrayList<LinkedList<Integer>> a = new ArrayList<>();
  for (int i=0; i<id.length; i++) a.add(new LinkedList<>());
  for (int i=0; i<id.length; i++) a.get(find(i)).add(0, i);
  for (Integer j : component_ids) {
    for (Integer i : a.get(j)) StdOut.print(i+"\t");
    StdOut.println();
  }
}  

public static void main(String[] args){
  int N = 10;
  MyUF uf = new MyUF(N);
  uf.union(0,9);
  uf.union(8,9);
  uf.showComponents();
  uf.fastComponents();
}//End of main
}// Simple UF with fast find and slow union.


