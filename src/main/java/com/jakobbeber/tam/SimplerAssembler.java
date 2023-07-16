package com.jakobbeber.tam;

import com.google.common.collect.Table;
import com.google.common.util.concurrent.Monitor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimplerAssembler {

    private Assembly assembly;

    public SimplerAssembler(Assembly assembly) {
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

        Queue<int[]> conveyor = assembly.getConveyor();

        lock.lock();
        try {
            while (conveyor.isEmpty()) {
                Thread.sleep(10);
            }
            int[] xy = conveyor.remove();
            x = xy[0];
            y = xy[1];
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
            /*
            lock2.lock();
            try {

             */
                conveyor.add(new int[]{x+1, y});
                conveyor.add(new int[]{x, y+1});
                //System.out.println("here");
            /*
            } finally {
                lock2.unlock();
            }

             */
            return true;
        }
        else {
            return false;
        }
    }
}
