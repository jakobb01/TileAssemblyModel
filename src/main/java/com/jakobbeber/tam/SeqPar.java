package com.jakobbeber.tam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.min;

public class SeqPar {

    private Assembly assembly;
    private Assembler assembler;

    public SeqPar(Assembly assembly, Assembler assembler) {
        this.assembly = assembly;
        this.assembler = assembler;
    }

    public long sequential(List<Map.Entry<Integer,Integer>>  queue, Tile[] tilesArray) {
        List<Map.Entry<Integer, Integer>> nextIter;

        // TO DO: Stop loop if certain size of table is reached
        int i = 0;
        long startTime = System.nanoTime();
        while (!queue.isEmpty() && !(i==100000)) {

            // we pick from the queue one tile at the time
            int randomNum = ThreadLocalRandom.current().nextInt(0, queue.size());
            Map.Entry<Integer,Integer> picked = queue.get(randomNum);

            // we find the tile in tilesArray and assemble it
            assembler.assemble(picked, tilesArray);
            // we remove the tile from queue
            queue.remove(picked);
            // we update nextIter
            nextIter = assembly.getQueue();

            // if queue is empty, we put nextIter into queue and clear nextIter
            if (queue.isEmpty()) {
                queue.addAll(nextIter);
                nextIter.clear();
            }
            i++;
        }
        long stopTime = System.nanoTime();
        //System.out.println(Arrays.asList(assembly.getTable()));
        return (stopTime - startTime);
    }

    public long parallel(List<Map.Entry<Integer,Integer>>  queue, Tile[] tilesArray, List<Map.Entry<Integer,Integer>> nextIter) throws InterruptedException {

        long startTime = System.nanoTime();

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        System.out.println(queue.size());
        int m = min(availableProcessors, (queue.size()));
        System.out.println(m);

        while (!queue.isEmpty()) {
            for (int i = 1; i < m+1; i++) {
                Multi multi = new Multi(i, assembly, assembler, queue.get(0));
                queue.remove(0);
                multi.start();
                multi.join();
            }
            if (queue.isEmpty()) {
                queue.addAll(nextIter);
                nextIter.clear();
            }

        }



        System.out.println(Arrays.asList(assembly.getTable()));

        long stopTime = System.nanoTime();


        return startTime - stopTime;
    }



}
