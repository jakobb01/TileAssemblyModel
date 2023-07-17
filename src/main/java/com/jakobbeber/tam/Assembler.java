package com.jakobbeber.tam;

import com.google.common.collect.Table;

import java.util.*;

public class Assembler {
    private Assembly assembly;

    public Assembler(Assembly assembly) {
        this.assembly = assembly;
    }



    public int[] neighbors(int x, int y) {

        Table<Integer, Integer, Tile> table = assembly.getTable();

        int [] neighbors = new int[8];

        // TO DO: check if neighbor exists; if x, y = 0, 0 then no north or west


        if (y == 0) {
            neighbors[0] = -1;
            neighbors[4] = 1;
        } else {
            // get north neighbor
            Tile north = table.get(x, y-1);
            while (north == null) {
                try {
                    Thread.sleep(10);
                    north = table.get(x, y-1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            neighbors[0] = north.getColors()[1];
            // glue north is 4
            neighbors[4] = north.getGlue()[1];
        }


        // get south neighbor
        //Tile south = table.get(x, y+1);
        neighbors[1] = -5;//south.getColors()[0];
        neighbors[5] = -5;//south.getGlue()[0];

        if (x == 0) {
            neighbors[2] = -1;
            neighbors[6] = 1;
        } else {
            // get west neighbor
            Tile west = table.get(x-1, y);
            while (west == null) {
                try {
                    Thread.sleep(10);
                    west = table.get(x, y-1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //System.out.println("coordinates: " + (x-1) + " "+y+" west: " + west.getColors()[3]);
            neighbors[2] = west.getColors()[3];
            neighbors[6] = west.getGlue()[3];
        }



        // get east neighbor
        //Tile east = table.get(x+1, y);
        neighbors[3] = -5;//east.getColors()[2];
        neighbors[7] = -5;//east.getGlue()[2];

        // first 0-3 are colors, last 4-7 are glue
        return neighbors;

    }

    public boolean compare (int x, int y, Tile[] choosingTiles, int[] neighborsColors, int[] neighborsGlue) {

        Table<Integer, Integer, Tile> table = assembly.getTable();

        boolean match = false;

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
                    match = true;
                    break;
                }
            }

        }

        assembly.setTable(table);

        return match;
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
        int [] neighbors = neighbors(x, y);
        // first 0-3 are colors, last 4-7 are glue
        neighborsColors[0] = neighbors[0];
        neighborsColors[1] = neighbors[1];
        neighborsColors[2] = neighbors[2];
        neighborsColors[3] = neighbors[3];
        neighborsGlue[0] = neighbors[4];
        neighborsGlue[1] = neighbors[5];
        neighborsGlue[2] = neighbors[6];
        neighborsGlue[3] = neighbors[7];


        //------------------------------------------------------------------------------------------
        // compare searchTile with each choosingTile
        // if match -> true
        if (compare(x, y, choosingTiles, neighborsColors, neighborsGlue)) {
            if (!(assembly.containsQueue(x+1, (y)))) {
                assembly.addQueue(x+1, y);
            }
            if (!(assembly.containsQueue((x), (y+1)))) {
                assembly.addQueue(x, y+1);
            }
            return true;
        }
        // if no match -> false
        else {
            return false;
        }








    }

}
