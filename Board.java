package com.company;
import  java.util.ArrayList;
public class Board {
    private int length;
    private int[][] tiles;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles)
    {
        this.length = tiles.length;
        this.tiles = new int[length][length];
        for(int i=0; i<this.length; i++)
        {
            for(int j=0; j<this.length;j++)
            {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString()
    {
        String a = new String();
        a+=this.length;
        for(int i=0; i<this.length; i++)
        {
            a+= "\n";
            for(int j=0; j<this.length;j++)
            {
                a += " " + this.tiles[i][j] ;
            }
        }
        return a;
    }


    // board dimension n
    public int dimension()
    {
        return this.length;
    }

    // number of tiles out of place
    public int hamming()
    {
        int hamming = 0;
        for(int i=0; i<this.length; i++)
        {
            for(int j=0; j<this.length;j++)
            {
                if(tiles[i][j]==0){continue;}
                else{
                    if(tiles[i][j] != (length*i+j+1)){hamming++;}
                }
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan()
    {
        int manhattan = 0;
        for(int i=0; i<this.length; i++)
        {
            for(int j=0; j<this.length;j++) {
                if (tiles[i][j] == 0) {
                    continue;
                } else {
                    if ((tiles[i][j] - 1) / length - i >= 0) { manhattan += (tiles[i][j] - 1) / length - i; }
                    else { manhattan -= (tiles[i][j] - 1) / length - i; }
                    if(tiles[i][j] - ((tiles[i][j] - 1) / length)*length - j - 1 >= 0){manhattan+=tiles[i][j] - ((tiles[i][j] - 1) / length)*length - j - 1;}
                    else{manhattan -= tiles[i][j] - ((tiles[i][j] - 1) / length)*length - j - 1;}
                }
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal(){return  this.hamming()==0;}

    // does this board equal y?
    public boolean equals(Object y)
    {
        if (y == null)
            return false;
        if (this.getClass() != y.getClass())
            return false;

        Board anotherBoard = (Board) y;

        if (dimension() != anotherBoard.dimension())
            return false;

        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] != anotherBoard.tiles[i][j]) return false;
            }
        }

        return true;
    }
    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbours = new ArrayList<>();
        int a=0,b=0;
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                if(tiles [i][j]==0){a=i; b=j;}
            }
        }
        if(a >0 && a< length-1)
        {neighbours.add(exchanged(this,a,b,a-1,b));
        neighbours.add(exchanged(this,a,b,a+1,b));}
        if(a == 0){neighbours.add(exchanged(this,a,b,a+1,b));}
        if(a == length-1){neighbours.add(exchanged(this,a,b,a-1,b));}

        if(b >0 && b< length-1)
        {neighbours.add(exchanged(this,a,b,a,b-1));
            neighbours.add(exchanged(this,a,b,a,b+1));}
        if(b == 0){neighbours.add(exchanged(this,a,b,a,b+1));}
        if(b == length-1){neighbours.add(exchanged(this,a,b,a,b-1));}
        return neighbours;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()
    {
        if(this.tiles[0][0]!=0&&this.tiles[length-1][length-1]!=0){return exchanged(this,0,0,length-1, length-1); }
        else{return exchanged(this,0,length - 1,length-1, 0); }
    }
    private Board exchanged(Board board, int a, int b, int c, int d)
    {
        int[][] tiles1 = new int[board.length][board.length];
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board.length; j++)
            {
                tiles1[i][j]=board.tiles[i][j];
            }
        }
        tiles1[a][b]=board.tiles[c][d];
        tiles1[c][d]=board.tiles[a][b];
        Board k = new Board(tiles1);
        return k;
    }
    // unit testing (not graded)
    public static void main(String[] args){}

}
