package org.example;

import java.util.LinkedList;

public class Sa2 {

    static int rounder = 3;

    public static void main(String[] args) {
        LinkedList<Double> x = new LinkedList<>();
        LinkedList<Double> fn = new LinkedList<>();
        LinkedList<Double> deltaX = new LinkedList<>();
        double k = 0.2;

        x.add(4d);
        fn.add((x.getLast() * k - Math.sqrt(x.getLast()) + Math.log(4 + x.getLast()) / x.getLast() + 1.5) / k);
        deltaX.add(0d);

        do {
            x.add(fn.getLast());
            fn.add((x.getLast() * k - Math.sqrt(x.getLast()) + Math.log(4 + x.getLast()) / x.getLast() + 1.5) / k);
            deltaX.add((Math.abs(x.getLast() - x.get(x.indexOf(x.getLast()) - 1))));
        } while (Math.abs(x.getLast() - x.get(x.indexOf(x.getLast()) - 1)) > 0.003);

        for (double i : x) {
            System.out.println(roundUp(i) + "\t" + roundUp(fn.get(x.indexOf(i))) + "\t" + roundUp(deltaX.get(x.indexOf(i))));
        }
    }

    public static double roundUp(double toRound) {
        return Math.round(Math.pow(10, rounder) * toRound) / Math.pow(10, rounder);
    }
}
