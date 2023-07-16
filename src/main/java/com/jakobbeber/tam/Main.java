package com.jakobbeber.tam;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Main {

    public static void main(String[] args) {

        //TO DO: two set of tiles for computation
        //
        //create tiles
        // INT TO CHARS COLORS:
        // -5 = anything, except border
        // -1 = BORDER
        // 0 = 0
        // 1 = 1
        // 2 = B
        // 3 = R
        Tile s = new Tile();
                            //N, S, W, E
        s.setColors(new int[]{-1, 3, -1, 2});
        s.setGlue(new int[]{1, 1, 1, 1});
        s.setName("Seed");

        Tile r = new Tile();
                            //N, S, W, E
        r.setColors(new int[]{3, 3, -1, 1});
        r.setGlue(new int[]{1, 1, 1, 1});
        r.setName("R");

        Tile b = new Tile();
                            //N, S, W, E
        b.setColors(new int[]{-1, 0, 2, 2});
        b.setGlue(new int[]{1, 1, 1, 1});
        b.setName("B");

        Tile nic0 = new Tile();
                               //N, S, W, E
        nic0.setColors(new int[]{1, 0, 1, 1});
        nic0.setGlue(new int[]{1, 1, 1, 1});
        nic0.setName("0");

        Tile nic1 = new Tile();
                               //N, S, W, E
        nic1.setColors(new int[]{0, 0, 0, 0});
        nic1.setGlue(new int[]{1, 1, 1, 1});
        nic1.setName("0_");

        Tile ena1 = new Tile();
                               //N, S, W, E
        ena1.setColors(new int[]{1, 1, 0, 0});
        ena1.setGlue(new int[]{1, 1, 1, 1});
        ena1.setName("1");

        Tile ena2 = new Tile();
                               //N, S, W, E
        ena2.setColors(new int[]{0, 1, 1, 0});
        ena2.setGlue(new int[]{1, 1, 1, 1});
        ena2.setName("1_");


        //create set of tiles
        Tile[] tiles = new Tile[8];
        tiles[0] = s;
        tiles[1] = r;
        tiles[2] = b;
        tiles[3] = nic0;
        tiles[4] = nic1;
        tiles[5] = ena1;
        tiles[6] = ena2;
        tiles[7] = null;


        // ----------------------------------------
        // INITIALIZATION
        Assembly assembly = new Assembly();
        assembly.setTiles(tiles);
        // starting tile
        assembly.setTable(HashBasedTable.create());
        Table<Integer, Integer, Tile> table = assembly.getTable();
        table.put(0, 0, s);
        assembly.setTable(table);




        // define two queues - we always pick from queue (local) and put into nextIter
        List<java.util.Map.Entry<Integer,Integer>> nextIter = assembly.getQueue();
        // we put 0, 1 and 1, 0 into queue
        assembly.firstQueue();
        nextIter = assembly.getQueue();
        java.util.List<java.util.Map.Entry<Integer,Integer>> queue= new java.util.ArrayList<>();



        // ASSEMBLY - INFINITE LOOP
        Assembler assembler = new Assembler(assembly);
        queue.addAll(nextIter);
        nextIter.clear();
        // TO DO: Stop loop if certain size of table is reached
        int i = 0;
        long start_Time = System.nanoTime();
        while (!queue.isEmpty() && !(i==100000)) {

            //System.out.println("Queue size: " + queue.size() + " NextIter size: " + nextIter.size());

            // we pick from the queue one tile at the time
            int randomNum = ThreadLocalRandom.current().nextInt(0, queue.size());
            Map.Entry<Integer,Integer> picked = queue.get(randomNum);
            assembler.assemble(picked, tiles);
            queue.remove(picked);
            nextIter = assembly.getQueue();

            //System.out.println("NextIter: " + assembly.getQueue().toString());

            if (queue.isEmpty()) {
                queue.addAll(nextIter);
                nextIter.clear();
            }
            i++;
            //System.out.println(Arrays.asList(assembly.getTable()));
        }
        long stopTime = System.nanoTime();


        System.out.println(Arrays.asList(assembly.getTable()));

        long elapsedTime = (stopTime - start_Time);
        System.out.println("Elapsed time: " + elapsedTime/1000000 + " ms");






    }


}
