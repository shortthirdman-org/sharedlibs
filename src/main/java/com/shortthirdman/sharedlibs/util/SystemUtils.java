package com.shortthirdman.sharedlibs.util;

public class SystemUtils {

    /**
     * @return the free Java Virtual Machine memory in bytes
     */
    public static long getFreeMemory() {
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        return freeMemory;
    }

    /**
     * @return the maximum Java Virtual Machine memory in bytes
     */
    public static long getMaxMemory() {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        return maxMemory;
    }

    /**
     * @return the total Java Virtual Machine memory in bytes
     */
    public static long getTotalMemory() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        return totalMemory;
    }
}
