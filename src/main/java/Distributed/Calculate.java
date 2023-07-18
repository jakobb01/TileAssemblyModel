package Distributed;

import java.util.ArrayList;
import java.util.List;

public class Calculate {

    private ArrayList<ArrayList<Integer>> nextIter;
    private ArrayList<ArrayList<Integer>> queue;



    public Calculate(ArrayList<ArrayList<Integer>> queue, ArrayList<ArrayList<Integer>> nextIter) {
        this.queue = queue;
        this.nextIter = nextIter;
    }

    public ArrayList<ArrayList<Integer>> start() {

        int a = queue.get(0).get(0);
        int b = queue.get(0).get(1);
        int k = 0;

        for (int i = 0; i < 8; i++) {
            if (new int[]{1, 2, 3, a} == new int[]{1, 2, 3, b}) {
                k++;

            }

        }


        nextIter.add(new ArrayList<>(List.of(a, b + 1)));
        nextIter.add(new ArrayList<>(List.of(a + 1, b)));


        return nextIter;
    }
}
