package Huffman_Encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CompareFiles {

    public void compareSize(String fileName, String fileName2) {
        File file1 = new File(fileName);
        File file2 = new File(fileName2);

        long size1 = file1.length();
        long size2 = file2.length();

        if (size2 - size1 > 1 | size1 - size2 > 1) {
            System.out.println("Rozmiary plików są różne:");
            System.out.printf("Plik 1 (%s): %d bajtów%n", fileName, size1);
            System.out.printf("Plik 2 (%s): %d bajtów%n", fileName2, size2);
        } else {
            System.out.println("Rozmiary plików są takie same: " + size1 + " bajtów.");
        }
    }

    public void compareTexts(String fileName, String fileName2) {
        try {
            String original = new String(Files.readAllBytes(Paths.get(fileName)));
            String decrypted = new String(Files.readAllBytes(Paths.get(fileName2)));

            String text1 = original.replace("\r\n", "\n");
            String text2 = decrypted.replace("\r\n", "\n");

            List<Integer> differences = new ArrayList<>();
            int minLength = Math.min(text1.length(), text2.length());

            for (int i = 0; i < minLength; i++) {
                if (text1.charAt(i) != text2.charAt(i)) {
                    differences.add(i);
                }
            }

            if (text1.length() != text2.length()) {
                for (int i = minLength; i < Math.max(text1.length(), text2.length()); i++) {
                    differences.add(i);
                }
            }

            if (differences.isEmpty()) {
                System.out.println("Teksty są identyczne!");
            } else {
                System.out.println("Teksty różnią się w miejscach: " + differences);
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu plików: " + e.getMessage());
        }
    }
}

