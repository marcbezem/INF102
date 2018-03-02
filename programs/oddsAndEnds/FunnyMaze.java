package oddsAndEnds;

import edu.princeton.cs.algs4.StdOut;

// simple pathfinder in maze
public class FunnyMaze {

private static boolean searchPath(// searches a path in the maze
 // from (inx,iny) to (outx,outx) via cells that are true
 // by horizontal, vertical and diagonal steps
    int N, // size of the square maze NxN
    int inx, int iny, int outx, int outy,
    boolean[][] maze, boolean[][] visited, boolean[][] onpath) {

    if (!maze[inx][iny] || visited[inx][iny] ) {return false;}
    onpath[inx][iny] = true;
    StdOut.println("at " + inx + "," + iny);          // print statement 1
    if (inx == outx && iny == outy ) {return true;}
    visited[inx][iny] = true; 
    for (int x = Math.max(inx-1,0); x < Math.min(inx+2,N); ++x)
        for (int y = Math.max(iny-1,0); y < Math.min(iny+2,N); ++y){
            if (!visited[x][y] && 
                searchPath(N,x,y,outx,outy,maze,visited,onpath)) 
               { return true; } 
        }
    onpath[inx][iny] = false;
    StdOut.println("not on path " + inx + "," + iny); // print statement 2
    return false;
}  

public static void main(String[] args)  {

    boolean[][] onpath = new boolean[4][4]; // all cells initially false
    boolean[][] visited = new boolean[4][4]; // all cells initially false
    boolean[][] maze = new boolean[4][];
    maze[0] = new boolean[]{true,true,false,true};
    maze[1] = new boolean[]{true,false,false,false};
    maze[2] = new boolean[]{true,true,false,true};
    maze[3] = new boolean[]{true,false,true,true};
    
    searchPath(4,0,0,3,3,maze,visited,onpath);

    for (int x=0; x<4; ++x){                      // print loop
        for(int y=0; y<4; ++y)
            if (onpath[x][y]) {StdOut.print(1);}
            else {StdOut.print(0);}
        StdOut.println();                         // end of print loop
     } 

} // end of main

} // end of class FunnyMaze
