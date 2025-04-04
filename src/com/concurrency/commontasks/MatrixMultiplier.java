package com.concurrency.commontasks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixMultiplier {
    public static int[][] multiply(int[][] A, int[][] B) {
        int size = A.length;
        int[][] result = new int[size][size];

        ExecutorService executor = Executors.newFixedThreadPool(size);

        for (int i = 0; i < size; i++) {
            final int row = i;
            executor.submit(() -> {
                for (int j = 0; j < size; j++) {
                    for (int k = 0; k < size; k++) {
                        result[row][j] += A[row][k] * B[k][j];
                    }
                }
            });
        }

        executor.shutdown();
        executor.shutdown();
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return result;
    }
}
