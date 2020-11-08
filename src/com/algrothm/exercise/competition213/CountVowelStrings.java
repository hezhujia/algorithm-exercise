package com.algrothm.exercise.competition213;

public class CountVowelStrings {
    public static int countVowelStrings(int n) {
        // 对于第一位是aeiou的且长j的数的个数
        int[][] cal = new int[5][n+1];

        // 对于首字母是a，长2位的数，个数有 5 个
        cal[0][1] = 5;  //a  cal[0][3] = cal[0][2] + cal[1][2] + cal[2][2] + cal[3][2] + cal[4][2] = 15
        cal[1][1] = 4;  //e  cal[1][3] = cal[1][2] + cal[2][2] + cal[3][2] + cal[4][2] = 10
        cal[2][1] = 3;  //i  cal[2][3] = cal[2][2] + cal[3][2] + cal[4][2]
        cal[3][1] = 2;  //o  cal[3][3] = cal[3][2] + cal[4][2]
        cal[4][1] = 1;  //u  cal[4][3] = cal[4][2]

        for (int i = 2; i <= n; i++) {
            cal[4][i] = cal[4][i-1];
            cal[3][i] = cal[4][i] + cal[3][i-1];
            cal[2][i] = cal[3][i] + cal[2][i-1];
            cal[1][i] = cal[2][i] + cal[1][i-1];
            cal[0][i] = cal[1][i] + cal[0][i-1];
        }

        return cal[0][n];
    }

    public static void main(String[] args) {
        System.out.println(countVowelStrings(33));
    }
}
