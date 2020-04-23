package com.company;

public class Main {

    public static void main(String[] args) {
        int[][] li = {{0,1,2},{3,4,5},{6,7,8}};
        Board a = new Board(li);
            System.out.println(a.manhattan());
        }
    }
