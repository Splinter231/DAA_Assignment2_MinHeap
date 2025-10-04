package cli;

import algorithms.MinHeap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@Fork(1)
@State(Scope.Thread)
public class MinHeapJMHBenchmark {

    private MinHeap heap;
    private int[] data;
    private static final int SIZE = 10000;

    @Setup(Level.Trial)
    public void setup() {
        heap = new MinHeap(SIZE);
        data = new int[SIZE];
        Random random = new Random(42);
        for (int i = 0; i < SIZE; i++) {
            data[i] = random.nextInt(1_000_000);
        }
    }

    @Benchmark
    public void testInsert() {
        for (int value : data) {
            heap.insert(value);
        }
    }

    @Benchmark
    public void testExtractMin() {
        for (int value : data) {
            heap.insert(value);
        }
        while (!heap.isEmpty()) {
            heap.extractMin();
        }
    }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(MinHeapJMHBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
