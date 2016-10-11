import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut; 
import edu.princeton.cs.algs4.Stopwatch; import edu.princeton.cs.algs4.StdRandom;
public class CountSort99 {

public  static void sort(int[] a) {
 int[] count = new int[100];
 for (int i=0; i<a.length; i++) count[a[i]]+=1;
 int i=0;
 for (int j=0; j<100; j++) for (int k=0; k < count[j]; k++) a[i++]=j;
}

private static void show(int[] a){
  for (Comparable o : a) StdOut.print(o+" "); StdOut.println();}

public static void main(String[] args){
  int N = Integer.parseInt(args[0]);
  int[] a;
  if (N==0){
    a = In.readInts();
    for (int i=0; i<a.length; i++) a[i] = ((a[i] % 100) + 100) % 100 ;}
  else {
    a = new int[N];
    for (int i=0; i<N; i++) a[i] = StdRandom.uniform(100);}

  Stopwatch stw = new Stopwatch(); sort(a); StdOut.println(stw.elapsedTime());

}//End of main
}//End of CountSort of integers 0..99 
