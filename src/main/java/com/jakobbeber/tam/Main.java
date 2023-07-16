package com.jakobbeber.tam;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.*;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        Tiles tiles = new Tiles();
        Assembly assembly = new Assembly();
        Assembler assembler = new Assembler(assembly);
        SeqPar seqPar = new SeqPar(assembly, assembler);


        // create tiles to build from
        Tile[] tilesArray = tiles.createBinary();
        assembly.setTiles(tilesArray);


        // starting table & tile (seed)
        assembly.setTable(HashBasedTable.create());
        Table<Integer, Integer, Tile> table = assembly.getTable();
        table.put(0, 0, tilesArray[0]);
        assembly.setTable(table);



        // define two queues - we always pick from queue (local) and put into nextIter
        List<java.util.Map.Entry<Integer,Integer>> nextIter;
        // we put 0, 1 and 1, 0 into queue
        assembly.firstQueue();
        nextIter = assembly.getQueue();
        java.util.List<java.util.Map.Entry<Integer,Integer>> queue= new java.util.ArrayList<>();
        queue.addAll(nextIter);
        nextIter.clear();


        //timer
        long elapsedTime = -1;


        // ASSEMBLY - SEQUENTIAL
        //elapsedTime = seqPar.sequential(queue, tilesArray);
        //System.out.println("Elapsed time for sequential: " + elapsedTime/1000000 + " ms");


        // ASSEMBLY - PARALLEL
        elapsedTime = seqPar.parallel(queue, tilesArray, nextIter);
        System.out.println("Elapsed time for parallel: " + elapsedTime/1000000 + " ms");






    }


}
