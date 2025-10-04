package metrics;

import java.io.FileWriter;
import java.io.IOException;

public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long arrayAccesses;
    private long memoryAllocations;
    private long startTime;
    private long endTime;

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementSwaps() {
        swaps++;
    }

    public void incrementArrayAccesses() {
        arrayAccesses++;
    }

    public void incrementArrayAccesses(int count) {
        arrayAccesses += count;
    }

    public void incrementMemoryAllocations() {
        memoryAllocations++;
    }

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public double getExecutionTimeMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getArrayAccesses() {
        return arrayAccesses;
    }

    public long getMemoryAllocations() {
        return memoryAllocations;
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        memoryAllocations = 0;
        startTime = 0;
        endTime = 0;
    }
    public void exportToCSV(String filePath, String algorithmName, int inputSize) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(String.format(
                    "%s,%d,%.3f,%d,%d,%d,%d%n",
                    algorithmName,
                    inputSize,
                    getExecutionTimeMillis(),
                    getComparisons(),
                    getSwaps(),
                    getArrayAccesses(),
                    getMemoryAllocations()
            ));
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }
}
