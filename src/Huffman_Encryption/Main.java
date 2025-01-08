package Huffman_Encryption;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        ExtractFile originalFile = new ExtractFile("original.txt");
        SymbolsDictionary dictionary = new SymbolsDictionary(originalFile.getText());
        Huffman huffman = new Huffman(dictionary.getDictionary());

        Node root = huffman.huffmanAlgorithm();

        Map<String, String> codes = huffman.generateHuffmanCodes(root);
        String visualDictionary = huffman.printHuffmanCodes(codes);

        System.out.println("Drzewo Huffmana:");
        huffman.printTree(root, 0);

        Encryption encryption = new Encryption();
        encryption.writeFile(visualDictionary, originalFile.getText(), codes);

        Decryption decryption = new Decryption();
        decryption.decryptionFile();
    }
}

