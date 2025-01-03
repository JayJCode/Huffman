package Huffman_Encryption;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Decryption {

    public void readFile() throws IOException {
        try (FileInputStream fis = new FileInputStream("encrypted.txt")) {
            byte[] fileContent = fis.readAllBytes();

            String content = new String(fileContent, StandardCharsets.UTF_8);
            int separatorIndexStart = content.indexOf("\n---BINARY-DATA---\n");
            if (separatorIndexStart == -1) {
                throw new IOException("Separator nie został znaleziony.");
            }
            int separatorIndexStop = content.indexOf("\n---BINARY-DATA---\n");
            if (separatorIndexStop == -1) {
                throw new IOException("Separator nie został znaleziony.");
            }

            String dictionary = content.substring(0, separatorIndexStop);
            System.out.println(dictionary);

            byte[] binaryData = new byte[fileContent.length - (separatorIndexStop + "\n---BINARY-DATA---\n".length())];
            System.arraycopy(fileContent, separatorIndexStop + "\n---BINARY-DATA---\n".length(), binaryData, 0, binaryData.length);

            System.out.println("\n---BINARY-DATA---");
            for (byte b : binaryData) {
                System.out.print(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
            }
        }
    }
}
