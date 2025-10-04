package algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class MinHeapTest {

    private MinHeap heap;

    @BeforeEach
    void setUp() {
        heap = new MinHeap(10);
    }

    @Test
    void insertAndPeek_ShouldReturnMinimum() {
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        assertEquals(3, heap.peek());
    }

    @Test
    void extractMin_ShouldReturnElementsInSortedOrder() {
        heap.insert(4);
        heap.insert(2);
        heap.insert(6);
        heap.insert(1);

        assertEquals(1, heap.extractMin());
        assertEquals(2, heap.extractMin());
        assertEquals(4, heap.extractMin());
        assertEquals(6, heap.extractMin());
    }

    @Test
    void extractMin_OnEmptyHeap_ShouldThrowException() {
        assertThrows(IllegalStateException.class, () -> heap.extractMin());
    }

    @Test
    void peek_OnEmptyHeap_ShouldThrowException() {
        assertThrows(IllegalStateException.class, () -> heap.peek());
    }

    @Test
    void insertMany_ShouldResizeArrayAutomatically() {
        for (int i = 0; i < 25; i++) {
            heap.insert(i);
        }
        assertEquals(25, heap.size());
        assertEquals(0, heap.peek());
    }

    @Test
    void handleDuplicateValues_CorrectlyMaintainsOrder() {
        heap.insert(5);
        heap.insert(1);
        heap.insert(5);
        heap.insert(1);

        assertEquals(1, heap.extractMin());
        assertEquals(1, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(5, heap.extractMin());
    }

    @Test
    void performanceTracker_ShouldCountComparisonsAndSwaps() {
        heap.insert(10);
        heap.insert(5);
        heap.insert(2);
        assertTrue(heap.getTracker().getComparisons() > 0);
        assertTrue(heap.getTracker().getSwaps() > 0);
    }
}
