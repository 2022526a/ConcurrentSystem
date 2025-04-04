package com.concurrency.commontasks;

import java.util.*;
import java.util.concurrent.*;

public class MergeSorter {
    public static List<Integer> concurrentMergeSort(List<Integer> data) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new MergeSortTask(data));
    }

    static class MergeSortTask extends RecursiveTask<List<Integer>> {
        private final List<Integer> list;

        MergeSortTask(List<Integer> list) {
            this.list = list;
        }

        @Override
        protected List<Integer> compute() {
            if (list.size() <= 1) return list;

            int mid = list.size() / 2;
            MergeSortTask leftTask = new MergeSortTask(list.subList(0, mid));
            MergeSortTask rightTask = new MergeSortTask(list.subList(mid, list.size()));

            invokeAll(leftTask, rightTask);
            return merge(leftTask.join(), rightTask.join());
        }

        private List<Integer> merge(List<Integer> left, List<Integer> right) {
            List<Integer> merged = new ArrayList<>();
            int i = 0, j = 0;

            while (i < left.size() && j < right.size()) {
                if (left.get(i) > right.get(j)) {
                    merged.add(left.get(i++));
                } else {
                    merged.add(right.get(j++));
                }
            }

            while (i < left.size()) merged.add(left.get(i++));
            while (j < right.size()) merged.add(right.get(j++));
            return merged;
        }
    }
}
