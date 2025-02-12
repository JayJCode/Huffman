package Huffman_Encryption;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExtractFile {
    private String text;

    public ExtractFile(String fileName) {
        try {
            File File = new File(fileName);
            Scanner textFileReader = new Scanner(File);
            this.text = "";
            while(textFileReader.hasNextLine()) {
                this.text += textFileReader.nextLine();
                if(textFileReader.hasNextLine()) {
                    this.text += "\n";
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("Program nie znalazł pliku o nazwie: "+fileName);
            throw new RuntimeException(e);
        }
    }

    public String getText() {   return this.text;   }
}
