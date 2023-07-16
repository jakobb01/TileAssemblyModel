package com.jakobbeber.tam;

import com.google.common.collect.Table;

import java.util.*;

public class Assembler {
    private Assembly assembly;

    public Assembler(Assembly assembly) {
        this.assembly = assembly;
    }

    public boolean assemble(Map.Entry<Integer,Integer> searchTile, Tile[] choosingTiles) {

        Table<Integer, Integer, Tile> table = assembly.getTable();
        // System.out.println("table: " + Arrays.toString(table.get(0, 0).getColors()));
        // indx  0  1  2  3
        // [] = {N, S, W, E}
        int [] neighborsColors = new int[4];
        int [] neighborsGlue = new int[4];

        int x = searchTile.getKey();
        int y = searchTile.getValue();
        //System.out.println("x: " + x + " y: " + y);

        // get neighbours of searchTile, each their color and glue and store into one array

        // TO DO: check if neighbor exists; if x, y = 0, 0 then no north or west

        if (y == 0) {
            neighborsColors[0] = -1;
            neighborsGlue[0] = 1;
        } else {
            // get north neighbor
            Tile north = table.get(x, y-1);
            neighborsColors[0] = north.getColors()[1];
            neighborsGlue[0] = north.getGlue()[1];
        }


        // get south neighbor
        Tile south = table.get(x, y+1);
        neighborsColors[1] = -5;//south.getColors()[0];
        neighborsGlue[1] = -5;//south.getGlue()[0];

        if (x == 0) {
            neighborsColors[2] = -1;
            neighborsGlue[2] = 1;
        } else {
            // get west neighbor
            Tile west = table.get(x-1, y);
            //System.out.println("coordinates: " + (x-1) + " "+y+" west: " + west.getColors()[3]);
            neighborsColors[2] = west.getColors()[3];
            neighborsGlue[2] = west.getGlue()[3];
        }



        // get east neighbor
        Tile east = table.get(x+1, y);
        neighborsColors[3] = -5;//east.getColors()[2];
        neighborsGlue[3] = -5;//east.getGlue()[2];

        //------------------------------------------------------------------------------------------
        // compare searchTile with each choosingTile
        for (int i = 0; i < choosingTiles.length; i++) {
            if (choosingTiles[i] == null) {
                continue;
            }
            int[] choosingTileColors = Arrays.copyOf(choosingTiles[i].getColors(), 4);
            int[] choosingTileGlue = Arrays.copyOf(choosingTiles[i].getGlue(), 4);
            //System.out.println("table: " + Arrays.toString(table.get(0, 0).getColors()));
            choosingTileColors[1] = -5; choosingTileGlue[1] = -5;
            //System.out.println("table: " + Arrays.toString(table.get(0, 0).getColors()));
            choosingTileColors[3] = -5; choosingTileGlue[3] = -5;
            //System.out.println("neighborsColors: " + Arrays.toString(neighborsColors));
            //System.out.println("choosingTileColors: " + Arrays.toString(choosingTileColors));


            if (Arrays.equals(neighborsColors, choosingTileColors)) {
                if (Arrays.equals(neighborsGlue, choosingTileGlue)) {
                    // we have a match
                    // put choosingTile into table
                    table.put(x, y, choosingTiles[i]);
                    break;
                }
            }

        }

        assembly.setTable(table);

        if (!(assembly.containsQueue(x+1, (y)))) {
            assembly.addQueue(x+1, y);
        }
        if (!(assembly.containsQueue((x), (y+1)))) {
            assembly.addQueue(x, y+1);
        }

        return true;



    }

}
