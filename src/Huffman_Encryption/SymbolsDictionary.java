package Huffman_Encryption;

import java.util.ArrayList;
import java.util.List;

public class SymbolsDictionary {
    private List<Symbol> dictionary;

    public SymbolsDictionary(String text) {
        this.dictionary = new ArrayList<>();
        for(int i=0; i<text.length(); i++) {
            char c = text.charAt(i);
            if(dictionary.isEmpty()) {
                dictionary.add(new Symbol(String.valueOf(c)));
            }
            else {
                setSymbol(c);
            }
        }
    }

    public List<Symbol> getDictionary() {
        return dictionary;
    }

    private void setSymbol(char c) {
        boolean charSet = false;
        for(Symbol symbol : dictionary) {
            if(symbol.getSymbol().equals(String.valueOf(c))) {
                symbol.setFreq(symbol.getFreq()+1);
                charSet = true;
                break;
            }
        }
        if(!charSet) {
            dictionary.add(new Symbol(String.valueOf(c)));
        }
    }
}
