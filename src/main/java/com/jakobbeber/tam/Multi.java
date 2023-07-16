package com.jakobbeber.tam;

import java.util.List;
import java.util.Map;

public class Multi extends Thread {

    int threadNum;
    private Assembly assembly;
    private Assembler assembler;
    private Map.Entry<Integer, Integer> picked;

    public Multi(int threadNum, Assembly assembly, Assembler assembler, Map.Entry<Integer, Integer> picked) {
        this.threadNum = threadNum;
        this.assembly = assembly;
        this.assembler = assembler;
        this.picked = picked;
    }


    @Override
    public void run() {
        System.out.println("Thread " + threadNum + " is running");

        System.out.println("Thread " + threadNum + " is assembling tile " + picked.getKey() + ", " + picked.getValue());
        System.out.println(assembly.getTiles().toString());
        assembler.assemble(picked, assembly.getTiles());


    }

}
