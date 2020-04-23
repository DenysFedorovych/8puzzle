//package com.company;
import java.util.Comparator;
import java.util.ArrayList;
import edu.princeton.cs.algs4.MinPQ;
public class Solver {
    private SearchNode pobeda;
    private int g;
    private class SearchNode implements Comparable<SearchNode>
    {
      private final Board board;
      private final SearchNode bboard;
      private int moves=0;
      private int priority;
      private SearchNode(Board a, SearchNode b, int k)
      {
          board = a;
          bboard = b;
          moves = k;
          priority = moves + a.manhattan();
      }
      private Board getBoard() {return board;}
      private Board getBboard()
      {
          if(bboard != null)
          {return bboard.getBoard();}
          else
              return null;
      }
      private SearchNode getprev() {return bboard;}
      public int compareTo(SearchNode a)
      {
          int h=0;
          if(this.priority > a.priority){h=1;}
          if(this.priority == a.priority){h=0;}
          if(this.priority < a.priority){h=(-1);}
          return h;
      }
    }
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        MinPQ<SearchNode> solv = new MinPQ<SearchNode>();
        SearchNode a = new SearchNode(initial,null,0);
        solv.insert(a);
        int p = 0;
        int moves = 0;
        while(p==0)
        {
            SearchNode current;
            current = solv.delMin();
            Board l;
            l = current.getBoard();
            if(moves>100000)
            {
                p++;
                break;
            }
            if(l.isGoal())
            {
                pobeda = current;
                p++;
                break;
            }
            moves++;
            for(Board each : l.neighbors())
            {
                if(!each.equals(current.getBboard()))
                {
                    SearchNode j = new SearchNode(each,current,moves);
                    solv.insert(j);
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        return this.solution()!=null;
    }

    // min number of moves to solve initial board
    public int moves(){SearchNode n;
        n = pobeda;
        ArrayList<Board> list = new ArrayList<Board>();
        while(n != null)
        {
            list.add(n.getBoard());
            n = n.getprev();
        }
    return (list.size()-1);}

    // sequence of boards in a shortest solution
    public Iterable<Board> solution()
    {
        SearchNode n;
        n = pobeda;
        ArrayList<Board> list = new ArrayList<Board>();
        while(n != null)
        {
            list.add(n.getBoard());
            n = n.getprev();
        }
        ArrayList<Board> list1 = new ArrayList<Board>();
        while(list.size()>0)
        {
            list1.add(list.remove(list.size()-1));
        }
        return list1;
    }

    // test client (see below)
    public static void main(String[] args)
    {

        /*// create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);*/
    }
}
