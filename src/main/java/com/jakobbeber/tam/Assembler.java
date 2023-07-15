package com.jakobbeber.tam;

import com.google.common.collect.Table;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Assembler {
    public Table<Integer, Integer, Tile> table;

    public Table<Integer, Integer, Tile> getTable() {
        return table;
    }

    public void setTable(Table<Integer, Integer, Tile> table) {
        this.table = table;
    }

    public Assembler(Table<Integer, Integer, Tile> table) {
        this.table = table;
    }

    public Set assemble(Duo searchTile, Tile[] choosingTiles, Table table) {

        setTable(table);

        // indx  0  1  2  3
        // [] = {N, S, W, E}
        int [] neighborsColors = new int[4];
        int [] neighborsGlue = new int[4];

        int x = searchTile.getFirst();
        int y = searchTile.getSecond();

        // get neighbours of searchTile, each their color and glue and store into one array

        // TO DO: check if neighbor exists; if x, y = 0, 0 then no north or west

        if (y == 0) {
            neighborsColors[0] = -1;
            neighborsGlue[0] = 1;
        } else {
            // get north neighbor
            Tile north = (Tile) table.get(x, y+1);
            neighborsColors[0] = north.getColors()[1];
            neighborsGlue[0] = north.getGlue()[1];
        }


        // get south neighbor
        Tile south = (Tile) table.get(x, y-1);
        neighborsColors[1] = -5;//south.getColors()[0];
        neighborsGlue[1] = -5;//south.getGlue()[0];

        if (x == 0) {
            neighborsColors[2] = -1;
            neighborsGlue[2] = 1;
        } else {
            // get west neighbor
            Tile west = (Tile) table.get(x-1, y);
            neighborsColors[2] = west.getColors()[3];
            neighborsGlue[2] = west.getGlue()[3];
        }



        // get east neighbor
        Tile east = (Tile) table.get(x+1, y);
        neighborsColors[3] = -5;//east.getColors()[2];
        neighborsGlue[3] = -5;//east.getGlue()[2];

        //------------------------------------------------------------------------------------------
        // compare searchTile with each choosingTile

        for (int i = 0; i < choosingTiles.length; i++) {
            if (choosingTiles[i] == null) {
                continue;
            }
            int[] choosingTileColors = choosingTiles[i].getColors();
            int[] choosingTileGlue = choosingTiles[i].getGlue();
            choosingTileColors[1] = -5; choosingTileGlue[1] = -5;
            choosingTileColors[3] = -5; choosingTileGlue[3] = -5;
            if (Arrays.equals(neighborsColors, choosingTileColors)) {
                if (Arrays.equals(neighborsGlue, choosingTileGlue)) {
                    // we have a match
                    // put choosingTile into table
                    table.put(x, y, choosingTiles[i]);
                    break;
                }
            }

        }

        setTable(table);


        Set<Duo> set = new HashSet<>();
        Duo duo = new Duo(x+1, y);
        Duo duo1 = new Duo(x, y-1);
        set.add(duo);
        set.add(duo1);
        System.out.println(set);

        return set;



    }

}
