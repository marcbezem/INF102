import edu.princeton.cs.algs4.StdOut;

public class PolyPair <
  T1 extends Comparable<T1>,
  T2 extends Comparable<T2>> 
implements 
  Comparable<PolyPair<T1,T2>> {
  
private T1 fst; public T1 getFst() {return fst;}
private T2 snd; public T2 getSnd() {return snd;}

public PolyPair(T1 fst, T2 snd){
  this.fst = fst;
  this.snd = snd;
}

public int compareTo(PolyPair<T1,T2> p){
  int comparefst = this.fst.compareTo(p.getFst());
  if (comparefst != 0) return comparefst;
  return this.snd.compareTo(p.getSnd());
} // TODO: equals(), hashCode()

@Override public String toString(){
  return "(" + this.getFst() + "," + this.getSnd() + ")";
  }

public static void main(String[] args){
    PolyPair<Integer,String> p = new PolyPair<Integer,String>(1,"aa");
    PolyPair<Integer,String> q;
    for (int i=0; i<3; i++) { 
      q = new PolyPair<Integer,String>(i,"a");
      StdOut.print(p + " compared to " + q + " yields ");
      StdOut.println(p.compareTo(q));
    }

  }//End of main
}//End of PolyPair Class
