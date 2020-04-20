package com.company;
import java.lang.Object;
import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;
public class Board {
    private int m;
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
    public int manhattan()
    {
        int k=0;
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
                int p = board[i][j];
                if(p/m>i && (p-(p/m)*m)>j)
                {
                    k+= p/m - i + p-(p/m)*m - j;
                }
                else
                {
                    if(p/m>i && (p-(p/m)*m)<j)
                    {
                        k+= p/m - i + j - p-(p/m)*m;
                    }
                    else
                    {
                        if(p/m<i && (p-(p/m)*m)>j)
                        {
                            k+= i - p/m + p-(p/m)*m - j;
                        }
                        else
                        {
                            k+= i - p/m + j - p-(p/m)*m;
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
    public boolean equals(Object y)
    {
        int k=0;
        int[][] list = (int[][]) y;
        if(list.length != m)
            return false;
        else {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] != list[i][j])
                        k++;
                }
            }
            return k==0;
        }
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
            for(int j=0; j<m; m++)
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
        int k = StdRandom.uniform(m-1);
        int p = StdRandom.uniform(m-1);
        return neigh(k,p,k+1,p+1);
    }

    // unit testing (not graded)
    public static void main(String[] args){}

}