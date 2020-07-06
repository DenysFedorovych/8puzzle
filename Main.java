package com.company;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
	int[][] tiles = new int[3][3];
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3;j++)
            {
                tiles[i][j] = 3*i+j+1;
            }
        }
        tiles[2][0]=0;
        tiles[2][1]=7;
        tiles[2][2]=8;
        Board a = new Board(tiles);
        //System.out.println(a.toString());
        Solver b = new Solver(a);
        //System.out.println(3);
        System.out.println(b.moves());
        for (Board each : b.solution())
        {
            System.out.println(each.toString());
        }
    }

}
