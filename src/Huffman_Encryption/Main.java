package Huffman_Encryption;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ExtractFile originalFile = new ExtractFile("original.txt");
        SymbolsDictionary dictionary = new SymbolsDictionary(originalFile.getText());
        Huffman H = new Huffman(dictionary.getDictionary());

        Node root = H.huffmanAlgorithm();

        Map<String, String> codes = H.generateHuffmanCodes(root);

        System.out.println("Kody Huffmana:");
        for (Map.Entry<String, String> entry : codes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nDrzewo Huffmana:");
        H.printTree(root, 0);
    }
}
