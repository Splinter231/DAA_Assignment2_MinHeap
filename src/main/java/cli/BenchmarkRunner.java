package cli;

import algorithms.MinHeap;
import metrics.PerformanceTracker;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] inputSizes = {100, 1000, 10000};
        Random random = new Random();

        System.out.println("=== MinHeap Benchmark ===");
        System.out.println("Size\tTime(ms)\tComparisons\tSwaps\tAccesses");

        for (int n : inputSizes) {
            MinHeap heap = new MinHeap(n);
            PerformanceTracker tracker = heap.getTracker();

            int[] data = new int[n];
            for (int i = 0; i < n; i++) data[i] = random.nextInt(1_000_000);

            tracker.reset();
            tracker.start();

            for (int value : data) {
                heap.insert(value);
            }

            tracker.stop();

            System.out.printf(
                    "%d\t%.3f\t%d\t%d\t%d%n",
                    n,
                    tracker.getExecutionTimeMillis(),
                    tracker.getComparisons(),
                    tracker.getSwaps(),
                    tracker.getArrayAccesses()
            );
        }
    }
}
