package algorithms;

import metrics.PerformanceTracker;
import java.util.Arrays;

public class MinHeap {
    private int[] heap;
    private int size;
    private final PerformanceTracker tracker;

    public MinHeap(int capacity) {
        heap = new int[Math.max(1, capacity)];
        tracker = new PerformanceTracker();
    }

    public void insert(int value) {
        ensureCapacity();
        heap[size] = value;
        tracker.incrementArrayAccesses();
        size++;
        heapifyUp(size - 1);
    }

    public int extractMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        int min = heap[0];
        tracker.incrementArrayAccesses();
        heap[0] = heap[size - 1];
        tracker.incrementArrayAccesses();
        size--;
        heapifyDown(0);
        return min;
    }

    public int peek() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        tracker.incrementArrayAccesses();
        return heap[0];
    }

    public void decreaseKey(int index, int newValue) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index for decreaseKey");
        if (index < 0 || index >= size) throw new IllegalArgumentException("Invalid index");
        if (newValue > heap[index]) throw new IllegalArgumentException("New value is greater");
        heap[index] = newValue;
        tracker.incrementArrayAccesses();
        heapifyUp(index);
    }

    public void merge(MinHeap other) {
        if (other == null || other.size == 0) {
            return;
        }
        int newSize = size + other.size;
        int[] merged = Arrays.copyOf(heap, newSize);
        System.arraycopy(other.heap, 0, merged, size, other.size);
        tracker.incrementMemoryAllocations();
        heap = merged;
        size = newSize;
        buildHeap();
    }


    private void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) heapifyDown(i);
    }

    private void ensureCapacity() {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
            tracker.incrementMemoryAllocations();
        }
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            tracker.incrementComparisons();
            if (heap[index] < heap[parent]) {
                swap(index, parent);
                index = parent;
            } else break;
        }
    }

    private void heapifyDown(int index) {
        while (index < size / 2) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = left;

            if (right < size) {
                tracker.incrementComparisons();
                if (heap[right] < heap[left]) smallest = right;
            }

            tracker.incrementComparisons();
            if (heap[index] <= heap[smallest]) break;

            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
        tracker.incrementSwaps();
        tracker.incrementArrayAccesses(3);
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public PerformanceTracker getTracker() { return tracker; }
}
