package com.jakobbeber.tam;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.*;


public class Main {

    public static void main(String[] args) {

        //TO DO: User can create his own tiles, this is just a demo
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

        Tile nic0 = new Tile();
                               //N, S, W, E
        nic0.setColors(new int[]{0, 1, 1, 1});
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
        ena2.setColors(new int[]{1, 0, 0, 1});
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

        // we put 0, 1 and 1, 0 into queue
        assembly.setQueue(new HashSet<>());
        assembly.firstsetQueue();

        // define two queues - we always pick from 1 and put into 2, when 1 is empty, we switch
        Set<Duo> queue = assembly.getQueue();
        Set<Duo> queue2 = new HashSet<>();
        Assembler assembler = new Assembler(table);


        // ASSEMBLY - INFINITE LOOP
        // TO DO: Stop loop if certain size of table is reached
        while (!queue.isEmpty()) {
            // we pick from the queue one tile at the time
            Duo picked = queue.iterator().next();
            queue2.addAll( assembler.assemble(picked, tiles, table) );
            table = assembler.getTable();
            queue.remove(picked);
            if (queue.isEmpty()) {
                assembly.setQueue(queue2);
                queue2 = new HashSet<>();
            }
            System.out.println(Arrays.asList(table));
        }





    }


}
