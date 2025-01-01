package Huffman_Encryption;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    private final List<Symbol> heap;

    public Heap() {
        this.heap = new ArrayList<>();
    }

    private void heapifyDown(int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        if (index > 0 && heap.get(index).compareTo(heap.get(parent)) < 0) {
            swap(index, parent);
            heapifyUp(parent);
        }
    }

    private void swap(int i, int j) {
        Symbol temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void insert(Symbol symbol) {
        heap.add(symbol);
        heapifyUp(heap.size() - 1);
    }

    public Symbol extractMin() {
        if (heap.isEmpty()) {
            return null;
        }
        Symbol min = heap.get(0);
        Symbol last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }
}