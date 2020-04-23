package com.company;

public class Main {

    public static void main(String[] args) {
        int[][] tile = {
                {5 , 2 , 4},
                {0 , 3 , 8},
                {7,  1,  6}};
        Board a = new Board(tile);
        Board b = new Board(tile);
        System.out.println(a.equals(b));
        /*for(Board each : t.solution())
        {
            System.out.println(each.toString());
        }
        System.out.println(t.moves());*/
        }
    }
