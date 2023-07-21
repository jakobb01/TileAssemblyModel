package Distributed;

// Uncomment this to run the program, make sure you have MPI installed, in compiler specify:
// Enviorment variables: MPJ_HOME = path\to\mpj
// VM options: -jar C:\mpj\lib\starter.jar Distributed -np 4
/*
import mpi.MPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        MPI.Init(args);

        // how many tiles will we compute? x * y
        long x = 1000;
        long y = 1000;

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();


        ArrayList<ArrayList<Integer>> nextIter = new ArrayList<>();
        ArrayList<ArrayList<Integer>> queue = new ArrayList<>();

        long startTime = System.nanoTime();

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        // we put 0, 1 and 1, 0 into queue
        // We put 0, 1 and 1, 0 into queue
        if (rank == 0) {
            queue.add(new ArrayList<>(Arrays.asList(0, 1)));
            queue.add(new ArrayList<>(Arrays.asList(1, 0)));
        }

        // Convert queue to array for broadcasting
        int[][] queueArray = convertArrayListToArray(queue);

        // Broadcast queue to all processes
        MPI.COMM_WORLD.Bcast(queueArray, 0, queueArray.length * 2, MPI.INT, 0);

        // Reconstruct sharedQueue on all processes
        ArrayList<ArrayList<Integer>> sharedQueue = convertArrayToArrayList(queueArray);

        // run function till when queue is empty or k is enough
        while ((!sharedQueue.isEmpty()) && (sharedQueue.get(0).get(0) < x) && (sharedQueue.get(0).get(1) < y)) {

            for (int i = 1; i < size; i++) {
                if (i == rank) {
                    Calculate calculate = new Calculate(sharedQueue, nextIter);
                    nextIter = calculate.start();
                }
                MPI.COMM_WORLD.Barrier(); // Wait for all processes to complete their computation
            }

            // Gather all nextIter lists from all processes to process 0
            ArrayList<ArrayList<Integer>> gatheredNextIter = null;
            if (rank == 0) {
                gatheredNextIter = new ArrayList<>();
            }
            MPI.COMM_WORLD.Gather(convertArrayListToArray(nextIter), 0, nextIter.size() * 2, MPI.INT, convertArrayListToArray(gatheredNextIter), 0, nextIter.size() * 2, MPI.INT, 0);

            // Broadcast gatheredNextIter back to all processes
            nextIter = convertArrayToArrayList(convertArrayListToArray(gatheredNextIter));
            int[][] nextIterArray = convertArrayListToArray(nextIter);
            MPI.COMM_WORLD.Bcast(nextIterArray, 0, nextIterArray.length * 2, MPI.INT, 0);
            nextIter = convertArrayToArrayList(nextIterArray);

            // Clear sharedQueue in all processes
            sharedQueue.clear();
            if (rank == 0) {
                sharedQueue.addAll(nextIter);
            }
            int[][] sharedQueueArray = convertArrayListToArray(sharedQueue);
            MPI.COMM_WORLD.Bcast(sharedQueueArray, 0, sharedQueueArray.length * 2, MPI.INT, 0);
            sharedQueue = convertArrayToArrayList(sharedQueueArray);
        }

        MPI.Finalize();




        long stopTime = System.nanoTime();
        long time = stopTime - startTime;

        System.out.println("Size: " + size + " Time: " + time);


        MPI.Finalize();
    }

    private static int[][] convertArrayListToArray(ArrayList<ArrayList<Integer>> list) {
        int[][] array = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> sublist = list.get(i);
            array[i][0] = sublist.get(0);
            array[i][1] = sublist.get(1);
        }
        return array;
    }

    private static ArrayList<ArrayList<Integer>> convertArrayToArrayList(int[][] array) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            ArrayList<Integer> sublist = new ArrayList<>();
            sublist.add(array[i][0]);
            sublist.add(array[i][1]);
            list.add(sublist);
        }
        return list;
    }
}

 */
