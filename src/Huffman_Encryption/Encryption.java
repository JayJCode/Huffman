package Huffman_Encryption;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Encryption {

    public void writeFile(String dictionary, String original, Map<String, String> codes) throws IOException {
        String binaryText = transferToBinary(codes, original);

        int paddingLength = 8 - (binaryText.length() % 8);
        if (paddingLength > 0) {
            binaryText = "0".repeat(paddingLength) + binaryText;
        }

        //System.out.println("Tekst binarny:");
        //System.out.println(binaryText);
        byte[] byteArray = makeByteArray(binaryText);

        try (FileOutputStream fos = new FileOutputStream("encrypted.txt")) {
            fos.write((paddingLength + "\n").getBytes(StandardCharsets.UTF_8));
            fos.write("---DICTIONARY---\n".getBytes(StandardCharsets.UTF_8));
            fos.write(dictionary.getBytes(StandardCharsets.UTF_8));
            fos.write("\n---BINARY-DATA---\n".getBytes(StandardCharsets.UTF_8));
            fos.write(byteArray);
        }
    }

    private String transferToBinary(Map<String, String> codes, String original) {
        String binaryText = "";
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            String code = codes.get(String.valueOf(c));
            if (code != null) {
                binaryText += code;
            } else {
                throw new IllegalArgumentException("Nie znaleziono kodu dla znaku: " + c);
            }
        }
        return binaryText;
    }

    private byte[] makeByteArray(String binaryText) {
        int byteCount = binaryText.length() / 8;
        byte[] byteArray = new byte[byteCount];
        for (int i = 0; i < byteCount; i++) {
            String byteString = binaryText.substring(i * 8, (i + 1) * 8);
            byteArray[i] = (byte) Integer.parseInt(byteString, 2);
        }
        return byteArray;
    }
}
