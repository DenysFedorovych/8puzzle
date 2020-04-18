package com.company;

public class Main {

    public static void main(String[] args) {
        int[][] tiles = new int[3][3];
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                tiles[i][j] = 3*i+j;
            }
        }
        Board a = new Board(tiles);
        System.out.println(a.toString());
    }
}
