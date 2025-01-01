package Huffman_Encryption;

public class PriorityQueue {
    private final Heap heap;

    public PriorityQueue() {
        this.heap = new Heap();
    }

    public void enqueue(Node node) {
        heap.insert(node);
    }

    public Node dequeue() {
        return heap.extractMin();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }
}
