package Huffman_Encryption;

public class Main {
    public static void main(String[] args) {
        ExtractFile originalFile = new ExtractFile("original.txt");
        System.out.println(originalFile.getText());

        SymbolsDictionary dictionary = new SymbolsDictionary(originalFile.getText());
        for(Symbol symbol : dictionary.getDictionary()) {
            System.out.println(symbol.toString());
        }

        PriorityQueue Q = new PriorityQueue();
        for(Symbol symbol : dictionary.getDictionary()) {
            Q.enqueue(symbol);
        }

        System.out.println("\n");
        while (!Q.isEmpty()) {
            System.out.println(Q.dequeue().toString());
        }
    }
}
