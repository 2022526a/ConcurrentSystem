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


    }
}
