package com.concurrency.commontasks;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static List<Integer> readFlatList(String filePath) throws IOException {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String val : line.split(",")) {
                    list.add(Integer.parseInt(val.trim()));
                }
            }
        }
        return list;
    }

    public static int[][] readMatrix(String filePath, int rows, int cols) throws IOException {
        int[][] matrix = new int[rows][cols];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < rows; i++) {
                String[] parts = br.readLine().split(",");
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = Integer.parseInt(parts[j].trim());
                }
            }
        }
        return matrix;
    }
}
