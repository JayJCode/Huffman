package Huffman_Encryption;

import java.util.ArrayList;
import java.util.List;

public class TransformFile {
    private List<Symbol> symbols;

    public TransformFile(String text) {
        this.symbols = new ArrayList<>();
        for(int i=0; i<text.length(); i++) {
            char c = text.charAt(i);
            for(Symbol symbol : symbols) {
                if(symbol.getSymbol() == c) {
                    symbol.setFreq(symbol.getFreq()+1);
                }
            }
        }
    }
}
