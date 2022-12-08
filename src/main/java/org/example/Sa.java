package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sa {

    static int rounder = 0;

    public static void main(String[] args) {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        double accuracy = 0d;
        double acc = 0d;

        try {
            accuracy = Double.parseDouble(bufferedReader.readLine());
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        acc = accuracy;

        while ((int) accuracy * 10 == 0) {
            accuracy *= 10;
            rounder++;
        }

        ArrayList<Double> xn = new ArrayList<>();
        ArrayList<Double> fn = new ArrayList<>();
        ArrayList<Double> fAn = new ArrayList<>();
        ArrayList<Double> diffX = new ArrayList<>();

        xn.add(4d);
        fn.add((Math.sqrt(xn.get(0)) - Math.log(xn.get(0) + 4) / xn.get(0) - 1.5));
        fAn.add((Math.log(xn.get(0) + 4) / Math.pow(xn.get(0), 2) - 1 / (xn.get(0) * (xn.get(0) + 4)) + 1 / (2 * Math.sqrt(xn.get(0)))));
        diffX.add(xn.get(0));

        xn.add((xn.get(0) - fn.get(0) / fAn.get(0)));
        fn.add((Math.sqrt(xn.get(1)) - Math.log(xn.get(1) + 4) / xn.get(1) - 1.5));
        fAn.add((Math.log(xn.get(1) + 4) / Math.pow(xn.get(1), 2) - 1 / (xn.get(1) * (xn.get(1) + 4)) + 1 / (2 * Math.sqrt(xn.get(1)))));
        diffX.add((xn.get(1) - xn.get(0)));

        int i = 1;
        while (Math.abs(diffX.get(i)) > acc) {
            i++;
            xn.add((xn.get(i - 1) - fn.get(i - 1) / fAn.get(i - 1)));
            fn.add((Math.sqrt(xn.get(i)) - Math.log(xn.get(i) + 4) / xn.get(i) - 1.5));
            fAn.add((Math.log(xn.get(0) + 4) / Math.pow(xn.get(0), 2) - 1 / (xn.get(0) * (xn.get(0) + 4)) + 1 / (2 * Math.sqrt(xn.get(0)))));
            diffX.add((xn.get(i) - xn.get(i - 1)));
        }


        for (double j : xn) {
            System.out.println(roundUp(xn.get(xn.indexOf(j))) + "\t" + roundUp(fn.get(xn.indexOf(j)))+ "\t" + roundUp(fAn.get(xn.indexOf(j))) + "\t" + roundUp(diffX.get(xn.indexOf(j))));
        }
    }

    public static double roundUp (double toRound) {
        return Math.round(Math.pow(10, rounder) * toRound) / Math.pow(10, rounder);
    }
}
