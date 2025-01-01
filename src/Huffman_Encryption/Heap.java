package Huffman_Encryption;

public class Heap {
    static void heapify(int[] arr, int N, int i)
    {
        int smallest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < N && arr[l] < arr[smallest])
            smallest = l;

        if (r < N && arr[r] < arr[smallest])
            smallest = r;

        if (smallest != i) {
            int swap = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = swap;

            heapify(arr, N, smallest);
        }
    }

    static void buildHeap(int[] arr, int N)
    {
        int startIdx = (N / 2) - 1;

        for (int i = startIdx; i >= 0; i--) {
            heapify(arr, N, i);
        }
    }

    static void printHeap(int[] arr, int N)
    {
        for (int i = 0; i < N; ++i)
            System.out.print(arr[i] + " ");
    }

}
