package com.concurrency.commontasks;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\\\Users\\\\Diyan\\\\Downloads\\\\data.csv";

        List<Integer> flatList = CSVReader.readFlatList(filePath);
        int[][] matrix = CSVReader.readMatrix(filePath, 20, 10);

        double stdDev = StandardDeviationCalculator.calculate(flatList);
        System.out.println("Standard Deviation: " + stdDev);

        int[][] matrixA = new int[10][10];
        int[][] matrixB = new int[10][10];
        System.arraycopy(matrix, 0, matrixA, 0, 10);
        System.arraycopy(matrix, 10, matrixB, 0, 10);

        int[][] product = MatrixMultiplier.multiply(matrixA, matrixB);
        System.out.println("Matrix multiplication result:");
        for (int[] row : product) {
            for (int val : row) System.out.print(val + " ");
            System.out.println();
        }

    }
}
