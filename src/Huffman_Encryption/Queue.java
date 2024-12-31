package Huffman_Encryption;

public class Queue {
    private int head;
    private int tail;

    public Queue() {
        this.head = 0;
        this.tail = -1;
    }

    private boolean isFull(String[] Q) {
        return ((tail + 2) % Q.length) == head;
    }

    private boolean isEmpty(String[] Q) {
        return ((tail + 1) % Q.length) == head;
    }

    public String[] enqueue(String[] Q, String x) {
        if(!isFull(Q)) {
            Q[(tail + 1) % Q.length] = x;
            tail = (tail + 1) % Q.length;
        }
        return Q;
    }

    public String enqueue(String[] Q) {
        if(!isEmpty(Q)) {
            head = (head + 1) % Q.length;
        }
        return Q[(head - 1) % Q.length];
    }

}
