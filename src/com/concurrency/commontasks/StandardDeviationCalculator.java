package com.concurrency.commontasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class StandardDeviationCalculator {
    public static double calculate(List<Integer> numbers) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        int n = numbers.size();
        double mean = numbers.stream().mapToDouble(i -> i).average().orElse(0.0);

        List<Future<Double>> results = new ArrayList<>();
        int chunkSize = n / 4;

        for (int i = 0; i < 4; i++) {
            int start = i * chunkSize;
            int end = (i == 3) ? n : (i + 1) * chunkSize;
            List<Integer> subList = numbers.subList(start, end);

            results.add(executor.submit(() -> {
                double sum = 0;
                for (int val : subList) {
                    sum += Math.pow(val - mean, 2);
                }
                return sum;
            }));
        }

        double totalSum = 0;
        for (Future<Double> future : results) {
            try {
                totalSum += future.get();
            } catch (Exception ignored) {}
        }

        executor.shutdown();
        return Math.sqrt(totalSum / n);
    }
}
