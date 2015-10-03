import edu.princeton.cs.algs4.StdOut;  import edu.princeton.cs.algs4.Stopwatch;
public class STCompare {
  
public static void run(String st_alg, String filename){ // symbol table "st_alg"
  String[] args = new String[1]; args[0] = filename;      // input file "filename"
  switch (st_alg) {
            case "ArrayListST"      :  ArrayListST.main(args);
                     break;
            case "UBST"             :  UBST.main(args);
                     break;
 //           case "ArrayListHashST"  :  ArrayListHashST.main(args);
 //                    break;
           default                 : StdOut.println(st_alg + " not known");
   }
}

public static double time(String st_alg, String filename){ // timing the run
  Stopwatch timer = new Stopwatch();
  run(st_alg,filename);
  return timer.elapsedTime();
}

public static void main(String[] args){
  String alg1     = args[0];
  String alg2     = args[1];
  String filename = args[2];
  double t1 = time(alg1,filename);
  double t2 = time(alg2,filename);
  StdOut.println(alg1 + "/" + alg2 + " = " + t1/t2);
}//End of main
}//End of STCompare based on Algorithms, 4th Edition, p. 256

