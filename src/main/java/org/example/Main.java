package org.example;

//f(x) = sqrt(x) - ln(x+4)/x - 1.5
//разностный метод Ньютона с постоянным шагом

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    static final double h = 0.001;

    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        double acc = 0d;

        try {
            acc = Double.parseDouble(bf.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LinkedList<Double> cache = new LinkedList<>();
        LinkedList<Double> fValue = new LinkedList<>();
        LinkedList<Double> deltaX = new LinkedList<>();

        cache.addFirst(4d);
        fValue.addFirst(Math.sqrt(cache.getLast()) - Math.log(cache.getLast() + 4) / cache.getLast() - 1.5);
        deltaX.addFirst(0d);

        cache.addLast(cache.getLast() - ((fValue.getLast() * h) /
                        (Math.sqrt(cache.getLast() + h) - Math.log(cache.getLast() + h + 4) / (cache.getLast() + h) - 1.5 - fValue.getLast())));
        fValue.addLast(Math.sqrt(cache.getLast()) - Math.log(cache.getLast() + 4) / cache.getLast() - 1.5);
        deltaX.addLast(Math.abs(cache.getLast() - cache.get(cache.indexOf(cache.getLast()) - 1)));

        while(Math.abs(cache.getLast() - cache.get(cache.indexOf(cache.getLast()) - 1)) >= acc) {
            cache.addLast(cache.getLast() -
                    (fValue.getLast() * h) /
                            (Math.sqrt(cache.getLast() + h) - Math.log(cache.getLast() + h + 4) - 1.5 - fValue.getLast()));
            fValue.addLast(Math.sqrt(cache.getLast()) - Math.log(cache.getLast() + 4) / cache.getLast() - 1.5);
            deltaX.addLast(Math.abs(cache.getLast() - cache.get(cache.indexOf(cache.getLast()) - 1)));
        }

        cache.stream().forEachOrdered(i -> {
            System.out.println(i + " " + fValue.get(cache.indexOf(i)) + " " + deltaX.get(cache.indexOf(i)));
        });
    }
}