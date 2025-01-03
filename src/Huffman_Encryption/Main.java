package Huffman_Encryption;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        ExtractFile originalFile = new ExtractFile("original.txt");
        SymbolsDictionary dictionary = new SymbolsDictionary(originalFile.getText());
        Huffman H = new Huffman(dictionary.getDictionary());

        Node root = H.huffmanAlgorithm();

        Map<String, String> codes = H.generateHuffmanCodes(root);

        String visualDictionary = "";
        System.out.println("Kody Huffmana:");
        for (Map.Entry<String, String> entry : codes.entrySet()) {
            String pair = entry.getKey() + ": " + entry.getValue() + "\t";
            System.out.print(pair);
            visualDictionary += pair;
        }

        System.out.println("\nDrzewo Huffmana:");
        H.printTree(root, 0);

        Encryption encryption = new Encryption();
        encryption.writeFile(visualDictionary, originalFile.getText(), codes);

        Decryption decryption = new Decryption();
        decryption.readFile();
    }
}

