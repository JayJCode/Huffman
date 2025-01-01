package Huffman_Encryption;

public class Main {
    public static void main(String[] args) {
        ExtractFile originalFile = new ExtractFile("original.txt");
        System.out.println(originalFile.getText());
    }
}
