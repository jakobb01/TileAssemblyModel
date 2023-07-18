package com.jakobbeber.tam;

import com.google.common.util.concurrent.Monitor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelAssembler {

    private Assembly assembly;

    public ParallelAssembler(Assembly assembly) {
        this.assembly = assembly;
    }
    private Monitor mutex = new Monitor();
    private Lock lock = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();



    public boolean simpleAssemble() throws InterruptedException {
        Tile[] foundationTiles = assembly.getTiles();

        int[] neighborsColors = new int[4];
        int[] neighborsGlue = new int[4];
        int x = 0, y = 0;

        List<Map.Entry<Integer,Integer>> queue = assembly.getQueue2();

        lock.lock();
        try {
            while (queue.isEmpty()) {
                Thread.sleep(5);
            }
            Map.Entry<Integer,Integer> picked = queue.get(0);
            queue.remove(picked);
            x = picked.getKey();
            y = picked.getValue();
            assembly.setQueue2(queue);
            //System.out.println("Thread " + Thread.currentThread().getName() + " is assembling tile " + x + ", " + y);
        } finally {
            lock.unlock();
        }



        Assembler assembler = new Assembler(assembly);
        int[] neighbors = assembler.neighbors(x, y);
        // first 0-3 are colors, last 4-7 are glue
        neighborsColors[0] = neighbors[0];
        neighborsColors[1] = neighbors[1];
        neighborsColors[2] = neighbors[2];
        neighborsColors[3] = neighbors[3];
        neighborsGlue[0] = neighbors[4];
        neighborsGlue[1] = neighbors[5];
        neighborsGlue[2] = neighbors[6];
        neighborsGlue[3] = neighbors[7];

        if (assembler.compare(x, y, foundationTiles, neighborsColors, neighborsGlue)) {
            if (!(assembly.containsQueue(x+1, (y)))) {
                assembly.addQueue(x+1, y);
            }
            if (!(assembly.containsQueue((x), (y+1)))) {
                assembly.addQueue(x, y+1);
            }
            return true;

        }
        else {
            return false;
        }
    }
}
