package com.company;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import edu.princeton.cs.algs4.MinPQ;
public class Solver {
    private ArrayList<Board> list = new ArrayList<>();
    private Comp comp = new Comp();
    private MinPQ<Node> PQ = new MinPQ<Node>(comp);
    private MinPQ<Node> PQ1 = new MinPQ<Node>(comp);
    private Board start;
    private boolean solvable;
    private int l=0;

    private class Node{
        public int prio;
        public Board board;
        public Board pboard;
        public int moves;
        public Node pnode;
        public Node(Board a, int b, Board c, Node d){
            this.board = a;
            this.pboard = c;
            this.moves = b;
            this.pnode = d;
            this.prio = a.manhattan() + moves;
        }
    }
    private class Comp implements Comparator<Node>{
        public int compare(Node a, Node b) {
            if (a.prio > b.prio) {
                return 1;
            } else {
                if (a.prio == b.prio) {
                    return 0;
                }
                else {
                    return -1;
                }
            }
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
        this.start = initial;
        if(initial==null){throw new IllegalArgumentException("Null argument");}
        Node intl = new Node(initial,0,null, null);
        Node intl1 = new Node(initial.twin(),0,null, null);
        PQ.insert(intl);
        PQ1.insert(intl1);
        int p=0;
        while(p==0){
            Node current = PQ.delMin();
            Node current1 = PQ1.delMin();
            //if(current.moves>50){p++; break;}
            if(current1.board.isGoal()){this.solvable=false; p++; break;}
            if(current.board.isGoal()){
                this.solvable=true;
                while(current!=null)
                {
                    list.add(current.board);
                    current = current.pnode;
                }
                p++;
                break;}
            for(Board each : current.board.neighbors())
            {
                if(!each.equals(current.pboard)){ Node a = new Node(each,current.moves+1,current.board, current);
                    PQ.insert(a); }
            }
            for(Board each : current1.board.neighbors())
            {
                if(!each.equals(current1.pboard)){ Node a = new Node(each,current1.moves+1,current1.board, current1);
                    PQ1.insert(a); }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
            return list.size()-1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        if(this.isSolvable()) {
            Collections.reverse(list);
            return list;
        }
        else {return null;}}

    // test client (see below)
    public static void main(String[] args){int a=0;}

}