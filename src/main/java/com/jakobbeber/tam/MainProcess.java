package com.jakobbeber.tam;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.*;


public class MainProcess {

    private static Assembly assembly;

    public MainProcess(Assembly assembly) {
        this.assembly = assembly;
    }

    public static long main_process(String createTile, long xLengthTable, long yLengthTable, int computeType) throws InterruptedException {
        Tiles tiles = new Tiles();
        Assembler assembler = new Assembler(assembly);
        SeqPar seqPar = new SeqPar(assembly, assembler);

        // test & debug
        //System.out.println(createTile);

        Tile[] tilesArray = new Tile[8];
        // create tiles to build from
        if (createTile.equals("binary")) {
            tilesArray = tiles.createBinary();
        }
        else if (createTile.equals("triangle")) {
            tilesArray = tiles.createSierpinski();
        }
        else{
            System.out.println("No tile type specified. Exiting.");
            System.exit(1);
        }
        assembly.setTiles(tilesArray);


        // starting table & tile (seed)

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

        if (computeType == 0) {
            // ASSEMBLY - SEQUENTIAL
            elapsedTime = seqPar.sequential(xLengthTable, yLengthTable, queue, tilesArray);
            System.out.println("Elapsed time for sequential: " + elapsedTime/1000000 + " ms");
        } else if (computeType == 1) {
            // ASSEMBLY - PARALLEL
            elapsedTime = seqPar.parallel(xLengthTable, yLengthTable);
            System.out.println("Elapsed time for parallel: " + elapsedTime/1000000 + " ms");
        } else if (computeType == 3) {
            System.out.println("For Distributed computation, please look into README & package com.jakobbeber.tam.distributed");
        } else {
            System.out.println("No computation type specified. Exiting.");
            System.exit(1);
        }

        return elapsedTime;


    }

    public static void main(String[] args) throws InterruptedException  {
        Assembly assembly = new Assembly();
        MainProcess mainProcess = new MainProcess(assembly);
        assembly.setTable(HashBasedTable.create());
        // For manual testing, arguments start here:
        String nic = "binary";
        String ena = "triangle";
        // 0 = sequential, 1 = parallel
        int computeType = 1;
        main_process(nic, 10000, 10000, computeType);
    }
}
