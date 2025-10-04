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

            tracker.exportToCSV("docs/performance-plots/minheap_performance.csv", "MinHeap", n);
        }

        System.out.println("\n=== Testing additional MinHeap functions ===");
        System.out.println("\nTesting decreaseKey():");
        MinHeap heap = new MinHeap(10);
        heap.insert(40);
        heap.insert(30);
        heap.insert(50);
        heap.insert(10);

        System.out.println("Before decreaseKey: " + heap.peek());
        heap.decreaseKey(2, 5);
        System.out.println("After decreaseKey: " + heap.peek());

        System.out.println("\nTesting merge():");
        MinHeap heap1 = new MinHeap(5);
        heap1.insert(3);
        heap1.insert(1);
        heap1.insert(7);

        MinHeap heap2 = new MinHeap(5);
        heap2.insert(2);
        heap2.insert(8);
        heap2.insert(4);

        heap1.merge(heap2);
        System.out.println("Merged heap min: " + heap1.peek());

        System.out.println("Extracting all elements after merge:");
        while (!heap1.isEmpty()) {
            System.out.print(heap1.extractMin() + " ");
        }
        System.out.println();
    }
}
