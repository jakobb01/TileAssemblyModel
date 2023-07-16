package com.jakobbeber.tam;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

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

    public long parallel(List<Map.Entry<Integer,Integer>>  queue, Tile[] tilesArray, List<Map.Entry<Integer,Integer>> nextIter) {

        long startTime = System.nanoTime();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sequential(queue, tilesArray);
            }
        });

        t1.start();

        long stopTime = System.nanoTime();


        return startTime - stopTime;
    }



}
