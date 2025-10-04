import matplotlib.pyplot as plt

sizes = [100, 1000, 10000]
times = [0.039, 0.240, 0.638]
comparisons = [223, 2218, 22856]
swaps = [132, 1223, 12861]

plt.figure(figsize=(8,5))
plt.plot(sizes, times, marker='o', label='Execution Time (ms)')
plt.title('MinHeap Insertion Performance')
plt.xlabel('Input Size (n)')
plt.ylabel('Time (ms)')
plt.xscale('log')
plt.grid(True)
plt.legend()
plt.savefig('docs/performance-plots/time_vs_n.png')
plt.close()

plt.figure(figsize=(8,5))
plt.plot(sizes, comparisons, marker='o', label='Comparisons')
plt.plot(sizes, swaps, marker='o', label='Swaps')
plt.title('Heap Operations vs Input Size')
plt.xlabel('Input Size (n)')
plt.ylabel('Count')
plt.xscale('log')
plt.grid(True)
plt.legend()
plt.savefig('docs/performance-plots/operations_vs_n.png')
plt.close()

print("✅ Saved plots to docs/performance-plots/")
