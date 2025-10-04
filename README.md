# Assignment 2 – Min-Heap Implementation (Heap Data Structure)
# Student: Sergey Chepurnenko
# Group: SE-2422

## Overview
This project implements a **Min-Heap** data structure with additional operations `decreaseKey()` and `merge()`.  
It focuses on algorithmic efficiency, asymptotic analysis, and empirical performance validation.  
The implementation includes metrics tracking, unit testing, and CLI benchmarking.

## Features
* Clean and modular Java implementation
* Operations: `insert`, `extractMin`, `peek`, `decreaseKey`, `merge`, `buildHeap`
* Performance tracking: comparisons, swaps, array accesses, allocations, execution time
* CLI benchmark runner with configurable input sizes
* JUnit tests covering all edge cases
* CSV export ready for performance plots

## Project Structure
DAA_Assignment2_MinHeap/
├── src/main/java/
│ ├── algorithms/MinHeap.java
│ ├── metrics/PerformanceTracker.java
│ └── cli/BenchmarkRunner.java
├── src/test/java/
│ └── algorithms/MinHeapTest.java
├── docs/
│ ├── analysis-report.pdf
│ └── performance-plots/
├── README.md
└── pom.xml

## Usage

### Build
```bash
mvn clean package
```
Run Tests
```bash
mvn test
```
Run Benchmarks
```bash
mvn exec:java -Dexec.mainClass="cli.BenchmarkRunner"
```
## Benchmark Results

### **Heap Construction and Insertion Performance**

| Input Size | Time (ms) | Comparisons | Swaps | Array Accesses |
|-------------|------------|--------------|--------|----------------|
| 100 | 0.039 | 223 | 132 | 496 |
| 1,000 | 0.240 | 2,218 | 1,223 | 4,669 |
| 10,000 | 0.638 | 22,856 | 12,861 | 48,583 |

**Observation:**  
The runtime grows approximately as **O(n log n)** — consistent with the theoretical complexity of heap insertions.  
The number of comparisons and swaps increases logarithmically relative to input size, validating the expected efficiency.

---

### **Functionality Tests**

Additional operations were validated using the CLI test suite.

#### `decreaseKey()`
Before decreaseKey: 10  
After decreaseKey: 5

The element value successfully decreased and re-heapified upward to become the new minimum element.

#### `merge()`


Merged heap min: 1  
Extracting all elements after merge:
1 2 3 4 7 8

The merged heap correctly combined elements from two separate heaps and restored heap order using a bottom-up `buildHeap()` process.

---

## Algorithm Description

### MinHeap — Core Operations

| Operation | Description |
|------------|-------------|
| `insert(value)` | Adds a new element and maintains heap order (heapify-up) |
| `extractMin()` | Removes and returns the smallest element, rebalances (heapify-down) |
| `peek()` | Returns the smallest element without removing it |
| `decreaseKey(index, newValue)` | Decreases a key and rebalances upward |
| `merge(MinHeap other)` | Merges two heaps and rebuilds structure efficiently |
| `buildHeap()` | Constructs a valid heap in O(n) time from unsorted data |