package oddsAndEnds;

import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;

public class ExistsThreeSumPos{

public static boolean existsThreeSumPos(int[] t) {
  int N = t.length;
  if (N<2) return false;
//put the three largest on the first three positions
  for (int i = 0; i < 3; i++){
  int maxpos = i;
    for (int j = i+1; j < N; j++) 
      if (t[j] > t[maxpos]) maxpos = j;
  int temp = t[i]; t[i] = t[maxpos]; t[maxpos] = temp;
  }
  return t[0]+t[1]+t[2] > 0;
} 

public static void main(String[] args)  { 
//In infile = new In(args[0]);
  int[] a = { 1, -2, 1, -3 ,0};
  StdOut.println(existsThreeSumPos(a));
}//End of main
}
