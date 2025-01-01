package Huffman_Encryption;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    private List<Symbol> symbols;
    private int head;
    private int tail;

    public Queue() {
        this.symbols = new ArrayList<>();
        this.head = 0;
        this.tail = -1;
    }

    private boolean isFull(int[] Q) {
        return ((tail + 2) % Q.length) == head;
    }

    private boolean isEmpty(int[] Q) {
        return ((tail + 1) % Q.length) == head;
    }

    public int[] enqueue(int[] Q, int x) {
        if(!isFull(Q)) {
            Q[(tail + 1) % Q.length] = x;
            tail = (tail + 1) % Q.length;
        }
        return Q;
    }

    public int dequeue(int[] Q) {
        if(!isEmpty(Q)) {
            head = (head + 1) % Q.length;
        }
        return Q[(head - 1) % Q.length];
    }

}
