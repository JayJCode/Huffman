package Huffman_Encryption;

public class Symbol {
    private final char symbol;
    private int freq;

    public Symbol(char symbol) {
        this.symbol = symbol;
        this.freq = 1;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
}
