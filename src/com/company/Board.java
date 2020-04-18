package com.company;
import java.lang.Object;
import java.util.Iterator;
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
    public int dimension(){return this.m;}

    // number of tiles out of place
    public int hamming()
    {
        int k=0;
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(board[i][j] != (i*m+j+1))
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

    private class It implements Iterator<Board>
    {
        public boolean hasNext(){}
    }
    // all neighboring boards
    public Iterable<Board> neighbors()
    {
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()

    // unit testing (not graded)
    public static void main(String[] args){}

}