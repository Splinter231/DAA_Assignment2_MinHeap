package algorithms;

import metrics.PerformanceTracker;
import java.util.Arrays;

public class MinHeap {
    private int[] heap;
    private int size;
    private final PerformanceTracker tracker;

    public MinHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
        tracker = new PerformanceTracker();
    }

    public void insert(int value) {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
            tracker.incrementMemoryAllocations();
        }
        heap[size] = value;
        tracker.incrementArrayAccesses();
        size++;
        heapifyUp(size - 1);
    }

    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = heap[0];
        tracker.incrementArrayAccesses();
        heap[0] = heap[size - 1];
        tracker.incrementArrayAccesses();
        size--;
        heapifyDown(0);
        return min;
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        tracker.incrementArrayAccesses();
        return heap[0];
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            tracker.incrementComparisons();
            if (heap[index] < heap[parent]) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        while (index < size / 2) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = left;

            if (right < size) {
                tracker.incrementComparisons();
                if (heap[right] < heap[left]) {
                    smallest = right;
                }
            }

            tracker.incrementComparisons();
            if (heap[index] <= heap[smallest]) break;

            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        tracker.incrementSwaps();
        tracker.incrementArrayAccesses(3);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public PerformanceTracker getTracker() {
        return tracker;
    }
}
