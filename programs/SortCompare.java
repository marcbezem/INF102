public class SortCompare {
  
public static void run(String sortalg, Double[] a){ // running sortalg on a
  switch (sortalg) {
            case "ExampleSort"    :  ExampleSort.sort(a);
                     break;
            case "JavaArraySort"  :  JavaArraySort.sort(a);
                     break;
            case "Insertion"      :  Insertion.sort(a);
                     break;
            case "Selection"      :  Selection.sort(a);
                     break;
   }
}

public static double time(String sortalg, Double[] a){ // timing the run
  Stopwatch timer = new Stopwatch();
  run(sortalg,a);
  return timer.elapsedTime();
}

public static double timeRandomInputs(String sortalg, int N, int T){
//running sortalg T times on array of N random doubles
  double total = 0.0;
  Double[] a = new Double[N];
  for (int t=0; t<T; t++){   StdOut.println(total);
    for (int i=0; i<N; i++) a[i] = StdRandom.uniform();
    total += time(sortalg,a);
  }

  return total;
}

public static void main(String[] args){
  String alg1 = args[0];
  String alg2 = args[1];
  int N = Integer.parseInt(args[2]);
  int T = Integer.parseInt(args[3]);
  double t1 = timeRandomInputs(alg1,N,T);
  double t2 = timeRandomInputs(alg2,N,T);
  StdOut.println(alg1 + "/" + alg2 + " = " + t1/t2);
}//End of main
}//End of SortCompare based on Algorithms, 4th Edition, p. 256

