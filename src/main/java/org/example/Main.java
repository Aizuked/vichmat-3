package org.example;

//f(x) = sqrt(x) - ln(x+4)/x - 1.5
//разностный метод Ньютона с постоянным шагом

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    static final double h = 0.001;
    static int rounder = 0;

    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        double acc = 0d;

        try {
            acc = Double.parseDouble(bf.readLine());
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while ((int) acc * 10 == 0) {
            acc *= 10;
            rounder++;
        }

        LinkedList<Double> cache = new LinkedList<>();
        LinkedList<Double> fValue = new LinkedList<>();
        LinkedList<Double> deltaX = new LinkedList<>();

        cache.addFirst(4d);
        fValue.addFirst(roundUp(Math.sqrt(cache.getLast()) - Math.log(cache.getLast() + 4) / cache.getLast() - 1.5));
        deltaX.addFirst(0d);

        cache.addLast(roundUp(cache.getLast() - ((fValue.getLast() * h) /
                        (Math.sqrt(cache.getLast() + h) - Math.log(cache.getLast() + h + 4) / (cache.getLast() + h) - 1.5 - fValue.getLast()))));
        fValue.addLast(roundUp(Math.sqrt(cache.getLast()) - Math.log(cache.getLast() + 4) / cache.getLast() - 1.5));
        deltaX.addLast(roundUp(Math.abs(cache.getLast() - cache.get(cache.indexOf(cache.getLast()) - 1))));

        while (Math.abs(deltaX.getLast()) > acc) {
            cache.addLast(roundUp(cache.getLast() -
                    (fValue.getLast() * h) /
                            (Math.sqrt(cache.getLast() + h) - Math.log(cache.getLast() + h + 4) - 1.5 - fValue.getLast())));
            fValue.addLast(roundUp(Math.sqrt(cache.getLast()) - Math.log(cache.getLast() + 4) / cache.getLast() - 1.5));
            deltaX.addLast(roundUp(Math.abs(cache.getLast() - cache.get(cache.indexOf(cache.getLast()) - 1))));
        }

        cache.stream().forEachOrdered(i -> {
            System.out.println(i + " " + fValue.get(cache.indexOf(i)) + " " + deltaX.get(cache.indexOf(i)));
        });
    }

    public static double roundUp(double toRound) {
        return Math.round(Math.pow(10, rounder) * toRound) / Math.pow(10, rounder);
    }
}