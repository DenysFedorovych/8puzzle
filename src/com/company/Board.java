//package com.company;
import java.lang.Object;
import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;
public class Board {
    private int m=0;
    private int[][] board;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles)
    {
        m = tiles.length;
        board = new int[m][m];
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
                board[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString()
    {
        String str = new String();
        str = m + "\n";
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
               str+= " " + board[i][j];
            }
            str+="\n";
        }
        return str;
    }

    // board dimension n
    public int dimension(){return m;}

    // number of tiles out of place
    public int hamming()
    {
        int k=0;
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(board[i][j] != (i*m+j+1) && board[i][j] != 0)
                {k++;}
            }
        }
        return k;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int k = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int p = board[i][j];
                if (p == 0) {
                    continue;
                } else {
                    if ((p-1) / m >= i && (p - ((p-1) / m) * m) - 1 >= j) {
                        k += ((p-1) / m - i + (p - ((p-1) / m) * m) - 1 - j);
                        //System.out.println("a" + p);
                    } else {
                        if ((p-1) / m >= i && (p - ((p-1) / m) * m) - 1 < j) {
                            k += ((p-1) / m  - i + j + 1 - (p - ((p-1) / m) * m));
                            //System.out.println("b" + p);
                        } else {
                            if ((p-1) / m  < i && (p - ((p-1) / m) * m) - 1 >= j) {
                                k += (i - (p-1) / m + (p - ((p-1) / m) * m) - 1 - j);
                                //System.out.println("c" + p);
                            } else {
                                k += (i - (p-1) / m + j + 1 - (p - ((p-1) / m) * m));
                                //System.out.println("d" + p);
                            }
                        }
                    }
                }
            }
        }
        return k;
    }
    // is this board the goal board?
    public boolean isGoal(){return this.hamming()==0;}

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (this == y) return true;
        if (this.getClass() != y.getClass()) return false;
        Board boardY = (Board) y;
        if (boardY.dimension() != this.dimension()) return false;
        for (int i=0;i<this.dimension();i++)
            for (int j=0;j<this.dimension();j++){
                if (this.board[i][j] != boardY.board[i][j])
                    return false;
            }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()
    {ArrayList<Board> list = new ArrayList<Board>();
    int a=0,b=0;
    for(int i=0; i<m; i++)
    {
        for(int j=0; j<m; j++)
        {
            if(board[i][j]==0){a = i;b = j;}
        }
    }
    if(a != 0 && a != m-1)
    {
        list.add(neigh(a,b,a-1,b));
        list.add(neigh(a,b,a+1,b));
    }
    else
    {
        if(a==0){list.add(neigh(a,b,a+1,b));}
        else {list.add(neigh(a,b,a-1,b));}
    }
        if(b != 0 && b != m-1)
        {
            list.add(neigh(a,b,a,b-1));
            list.add(neigh(a,b,a,b+1));
        }
        else
        {
            if(b==0){list.add(neigh(a,b,a,b+1));}
            else {list.add(neigh(a,b,a,b-1));}
        }
     return list;
    }
    private int[][] copy()
    {
        int[][] a = new int[m][m];
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
                a[i][j] = board[i][j];
            }
        }
        return a;
    }
    private void swap(int a, int b, int c, int d)
    {
        int l = board[a][b];
        board[a][b] = board[c][d];
        board[c][d] = l;
    }
    private Board neigh(int a, int b, int c, int d)
    {
        Board k = new Board(this.copy());
        k.swap(a,b,c,d);
        return k;
    }
    // a board that is obtained by exchanging any pair of tiles
    public Board twin()
    {
        if(m>1) {
            return neigh(0, m-1, m-1, 0);
        }
        else
            return this;
    }

    // unit testing (not graded)
    public static void main(String[] args){}

}