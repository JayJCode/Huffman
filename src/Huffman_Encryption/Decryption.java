package Huffman_Encryption;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Decryption {

    public void decryptionFile() throws IOException {
        try (FileInputStream fis = new FileInputStream("encrypted.txt")) {
            byte[] fileContent = fis.readAllBytes();

            String content = new String(fileContent, StandardCharsets.UTF_8);

            int paddingLengthEnd = content.indexOf("\n");
            if (paddingLengthEnd == -1) {
                throw new IOException("Brak informacji o liczbie zer w pliku.");
            }
            int paddingLength = Integer.parseInt(content.substring(0, paddingLengthEnd).trim());

            int dictStart = content.indexOf("\n---DICTIONARY---\n");
            int binaryStart = content.indexOf("\n---BINARY-DATA---\n");

            if (dictStart == -1 || binaryStart == -1) {
                throw new IOException("Brak separatorów w pliku.");
            }

            String dictionary = content.substring(dictStart + "---DICTIONARY---\n\n".length(), binaryStart);
            Map<String, String> codes = readCodes(dictionary);
            System.out.println(codes);

            byte[] binaryData = extractBinaryData(fileContent, binaryStart);

            String binaryString = convertBinaryToString(binaryData);
            binaryString = binaryString.substring(paddingLength+8);

            System.out.println(binaryString);
            String decryptedText = decodeText(binaryString, codes).trim();

            System.out.println(decryptedText);
            writeFile(decryptedText);
        }
    }

    private Map<String, String> readCodes(String text) {
        Map<String, String> codes = new HashMap<>();
        String[] dictionaryArr = text.split(",\n");
        for (String entry : dictionaryArr) {
            String[] pair = entry.split(" -> ");

            if (pair.length == 2) {
                codes.put(pair[1], pair[0]);
            }
        }
        return codes;
    }

    private byte[] extractBinaryData(byte[] fileContent, int binaryStart) {
        int binaryDataOffset = binaryStart + "---BINARY-DATA---\n".length();
        byte[] binaryData = new byte[fileContent.length - binaryDataOffset];
        System.arraycopy(fileContent, binaryDataOffset, binaryData, 0, binaryData.length);
        return binaryData;
    }

    private String convertBinaryToString(byte[] binaryData) {
        StringBuilder binaryString = new StringBuilder();
        for (byte b : binaryData) {
            binaryString.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        return binaryString.toString();
    }

    private String decodeText(String binaryString, Map<String, String> codes) {
        StringBuilder decodedText = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();

        for (char bit : binaryString.toCharArray()) {
            currentCode.append(bit);
            if (codes.containsKey(currentCode.toString())) {
                decodedText.append(codes.get(currentCode.toString()));
                currentCode.setLength(0);
            }
        }

        return decodedText.toString();
    }

    private void writeFile(String decryptedText) throws IOException {
        Path decrypted = Path.of("decrypted.txt");
        Files.writeString(decrypted, decryptedText);
        System.out.println("Odszyfrowany tekst zapisany w pliku: " + decrypted.toAbsolutePath());
    }
}
