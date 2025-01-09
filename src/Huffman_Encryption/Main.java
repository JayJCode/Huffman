package Huffman_Encryption;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        Jest to tylko przykładowy plik main,
        dzięki któremu można zobaczyć działanie programu
        i wszystkich jego funkcji :)
        */

        // Punkt pierwszy wczytywanie pliku i liczenie znaków 5 pkt
        ExtractFile originalFile = new ExtractFile("original.txt");
        SymbolsDictionary dictionary = new SymbolsDictionary(originalFile.getText());

        // Punkt trzeci tworzenie drzewa i kody wynikowe 5 pkt
        Huffman huffman = new Huffman(dictionary.getDictionary());
        Node root = huffman.huffmanAlgorithm();
        Map<String, String> codes = huffman.generateHuffmanCodes(root);
        String visualDictionary = huffman.printHuffmanCodes(codes);

        System.out.println("Drzewo Huffmana:");
        //huffman.printTree(root, 0);     // Alternatywnie to była pierwsza opcja
        huffman.printHorizontalTree(root);

        // Punkt czwarty zapis do pliku (upewnić się że jest mniejszy rozmiar) 5 pkt
        Encryption encryption = new Encryption();
        encryption.writeFile(visualDictionary, originalFile.getText(), codes);
        /*  notatka - uwaga dla sprawdzajacego
        Różnice rozmiaru bedą przechodzić na korzyść zaszyfrowanego pliku,
        w prost proporcjonalnie do zwiększanego rozmiaru.
        Przy małych plikach element Dictionary, który do niego dodaliśmy powoduje,
         że jest on w gruncie rzeczy znacznie większy.
        */
        CompareFiles compareCodedFiles = new CompareFiles();
        compareCodedFiles.compareSize("original.txt", "encrypted.txt");

        // Punkt piąty dekodowanie 5 pkt (razem z porównaniem zawartości oryginalnego pliku).
        Decryption decryption = new Decryption();
        decryption.decryptionFile();
        compareCodedFiles.compareTexts("original.txt", "decrypted.txt");
    }
}


/*
Można by było jakoś poczyścić ten kod i pododawać komentarze :)
Ewentualnie rozbić troche PriorityQueue od Heap, Bo teraz troche to Heap ma zachowania kolejki,
a ona tylko zamienia nazwy - w  sumie to jeszcze zmienie!
Testy jednostkowe może? Ale raczej odpuszcze sobie :P
 */

