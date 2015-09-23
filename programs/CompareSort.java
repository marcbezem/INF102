// CompareSort compares two sorting algorithms on T randomized arrays of N Doubles
// More precisely, one algorithm is run on T such arrays, measuring total runtime, 
// and then the other algorithm proceeds similarly on the same T arrays, 
// after which the respective total runtimes are compared.
// Methodological remark: by comparing the two sorting algorithms directly,
// running them on the same arrays, we hope to get a more realistic comparison.

public class CompareSort {
  
public static void run(String sortalg, Double[] a){ // sort a using "sortalg"
  switch (sortalg) {
            case "ExampleSort"      :  ExampleSort.sort(a);
                     break;
            case "JavaArraySort"    :  JavaArraySort.sort(a);
                     break;
            case "InsertionSort"    :  InsertionSort.sort(a);
                     break;
            case "ShellSort"        :  ShellSort.sort(a);
                     break;
            case "TopDownMergeSort" :  TopDownMergeSort.sort(a);
                     break;
            case "BottomUpMergeSort":  BottomUpMergeSort.sort(a);
                     break;
            case "QuickSort"        :  QuickSort.sort(a);
                     break;
            case "NaiveHeapSort"    :  NaiveHeapSort.sort(a);
                     break;
           default                  : StdOut.println(sortalg + " not known");
   }
}

public static double time(String sortalg, Double[] a){ // timing the run
  Stopwatch timer = new Stopwatch();
  run(sortalg,a);
  return timer.elapsedTime();
}

public static double compareSort(String alg1, String alg2, int N, int T){
//running alg1 and alg2 on T arrays of N random Doubles
  double total1 = 0.0;
  double total2 = 0.0;
  Double[] a1 = new Double[N];
  Double[] a2 = new Double[N]; // we NEED two arrays! (Why?)
  for (int t=0; t<T; t++){
    for (int i=0; i<N; i++) { a1[i] = StdRandom.uniform(); a2[i] = a1[i]; }
    total1 += time(alg1,a1); // summing runtime of alg1
    total2 += time(alg2,a2); // summing runtime of alg2
  }
  return total1/total2;
}

public static void main(String[] args){
  String alg1 = args[0];
  String alg2 = args[1];
  int N = Integer.parseInt(args[2]);
  int T = Integer.parseInt(args[3]);
  double cmp = compareSort(alg1, alg2, N, T);
  StdOut.println(alg1 + "/" + alg2 + " = " + cmp);
}//End of main
}//End of CompareSort, modifying SortCompare, Algorithms, 4th Edition, p. 256

