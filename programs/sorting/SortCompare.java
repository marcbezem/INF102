package sorting;// SortCompare compares two sorting algorithms on 2T randomized arrays of N Doubles
// More precisely, one algorithm is run on T such arrays, measuring total runtime, 
// and then the other algorithm proceeds similarly with the next T arrays, 
// after which the respective total runtimes are compared.
// Methodological remark: the two sorting algorithms are never compared directly,
// running them on the same arrays. Our CompareSort does this. On large samples there
// should be no difference. On small samples we hope to get more stability.

import edu.princeton.cs.algs4.StdOut; import edu.princeton.cs.algs4.Stopwatch; import edu.princeton.cs.algs4.StdRandom;
import sorting.priorityQueues.NaiveHeapSort;
import sorting.quickSort.QuickSort;
import sorting.elementarySorts.ShellSort;
import sorting.elementarySorts.ExampleSort;
import sorting.elementarySorts.InsertionSort;
import sorting.mergeSort.BottomUpMergeSort;
import sorting.mergeSort.TopDownMergeSort;

public class SortCompare {
  
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
  run(sortalg, a);
  return timer.elapsedTime();
}

public static double timeRandomInputs(String sortalg, int N, int T){
//running algorithm "sortalg" T times on array of N random doubles
  double total = 0.0;
  Double[] a = new Double[N];
  for (int t=0; t<T; t++){
    for (int i=0; i<N; i++) a[i] = StdRandom.uniform();
    total += time(sortalg, a);
  }
  return total;
}

public static void main(String[] args){
  String alg1 = args[0];
  String alg2 = args[1];
  int N = Integer.parseInt(args[2]);
  int T = Integer.parseInt(args[3]);
  double t1 = timeRandomInputs(alg1, N, T);
  double t2 = timeRandomInputs(alg2, N, T);
  StdOut.println(alg1 + "/" + alg2 + " = " + t1 / t2);
}//End of main
}//End of SortCompare based on Algorithms, 4th Edition, p. 256

