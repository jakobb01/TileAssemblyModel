package com.jakobbeber.tam;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Main {

    public static void main(String[] args) {

        Assembly assembly = new Assembly();
        Table<Integer, Integer, Tile> table = assembly.getTable();


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
        s.setColors(new int[]{3, -1, 2, -1});
        s.setGlue(new int[]{1, 1, 1, 1});
        s.setName("Seed");

        Tile r = new Tile();
                            //N, S, W, E
        r.setColors(new int[]{3, 3, 1, -1});
        r.setGlue(new int[]{1, 1, 1, 1});
        r.setName("R");

        Tile b = new Tile();
                            //N, S, W, E
        b.setColors(new int[]{0, -1, 2, 2});
        b.setGlue(new int[]{1, 1, 1, 1});
        b.setName("B");
        //TO DO: MISSING TILES


        //create set of tiles
        Tile[] tiles = new Tile[8];
        tiles[0] = s;
        tiles[1] = r;
        tiles[2] = b;



        // INITIALIZATION
        // starting tile
        table.put(0, 0, s);

        // we put 0, 1 and 1, 0 into queue
        assembly.firstsetQueue();

        // define two queues - we always pick from 1 and put into 2, when 1 is empty, we switch
        Set<Duo> queue = assembly.getQueue();
        Set<Duo> queue2 = new HashSet<>();
        Assembler assembler = new Assembler();


        // ASSEMBLY - INFINITE LOOP
        // TO DO: Stop loop if certain size of table is reached
        while (!queue.isEmpty()) {
            // we pick from the queue one tile at the time
            Duo picked = queue.iterator().next();
            queue2.addAll( assembler.assemble(picked, tiles, table) );
            queue.remove(picked);
            if (queue.isEmpty()) {
                assembly.setQueue(queue2);
                queue2 = new HashSet<>();
            }
        }





    }


}
