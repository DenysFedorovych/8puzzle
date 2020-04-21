package com.company;

public class Main {

    public static void main(String[] args) {
        int p = 5;
        int m = 3;
        int k = 0;
        int i = 1;
        int j = 2;
        if (p == 0) {
            continue;
        } else {
            if (p / m >= i && (p - (p / m) * m) - 1 >= j) {
                k += (p / m - i + (p - (p / m) * m) - 1 - j);
            } else {
                if (p / m >= i && (p - (p / m) * m) - 1 < j) {
                    k += (p / m - i + j + 1 - (p - (p / m) * m));
                } else {
                    if (p / m < i && (p - (p / m) * m) - 1 >= j) {
                        k += (i - p / m + (p - (p / m) * m) - 1 - j);
                    } else {
                        k += (i - p / m + j + 1 - (p - (p / m) * m));
                    }
                }
            }
            System.out.println(k);
        }
    }
}
