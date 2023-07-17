package com.jakobbeber.tam;

import java.util.*;
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

    public long parallel() throws InterruptedException {

        SimplerAssembler simplerAssembler = new SimplerAssembler(assembly);
        List<Map.Entry<Integer, Integer>> nextIter;
        List<Map.Entry<Integer, Integer>> queue = new ArrayList<>();

        long startTime = System.nanoTime();

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        // we put 0, 1 and 1, 0 into queue
        assembly.firstQueue();
        nextIter = assembly.getQueue();
        queue.addAll(nextIter);
        assembly.setQueue2(queue);
        nextIter.clear();

        // run function till when queue is empty or x and y is enough
        int k = 0;
        while ((!queue.isEmpty()) && !(k==10000)) {
            Thread thread[] = new Thread[availableProcessors+1];
            for (int i = 1; i < availableProcessors+1; i++) {
                thread[i] = new Thread() {
                    public void run() {
                        try {
                            simplerAssembler.simpleAssemble();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };
                thread[i].start();
            }
            for (int i = 1; i < availableProcessors+1; i++) {
                thread[i].join(10);
            }
            queue = assembly.getQueue2();
            nextIter = assembly.getQueue();
            if (queue.isEmpty()) {

                queue.addAll(nextIter);
                nextIter.clear();
            }
            k++;
            //System.out.println(queue.isEmpty());
        }




        System.out.println(Arrays.asList(assembly.getTable()));

        long stopTime = System.nanoTime();


        return stopTime - startTime;
    }



}
