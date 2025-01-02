package Huffman_Encryption;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Encryption {

    public void writeFile(String dictionary, String original, Map<String, String> codes) throws IOException {
        String binaryText = transferToBinary(codes, original);

        int paddingLength = 8 - (binaryText.length() % 8);
        if (paddingLength < 8) {
            binaryText = "0".repeat(paddingLength) + binaryText;
        }

        int byteCount = binaryText.length() / 8;
        byte[] byteArray = new byte[byteCount];
        for (int i = 0; i < byteCount; i++) {
            String byteString = binaryText.substring(i * 8, (i + 1) * 8);
            byteArray[i] = (byte) Integer.parseInt(byteString, 2);
        }

        try (FileOutputStream fos = new FileOutputStream("encrypted.txt")) {
            fos.write("\n---DICTIONARY---\n".getBytes(StandardCharsets.UTF_8));
            fos.write(dictionary.getBytes(StandardCharsets.UTF_8));
            fos.write("\n---BINARY-DATA---\n".getBytes(StandardCharsets.UTF_8));
            fos.write(byteArray);
        }
    }

    private String transferToBinary(Map<String, String> codes, String original) {
        String binaryText = "";
        for(int i=0; i<original.length(); i++) {
            char c = original.charAt(i);
            for (Map.Entry<String, String> entry : codes.entrySet()) {
                if(String.valueOf(c).equals(entry.getKey())) {
                    binaryText += entry.getValue();
                }
            }
        }
        return binaryText;
    }
}