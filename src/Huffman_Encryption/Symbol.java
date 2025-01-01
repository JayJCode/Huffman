package Huffman_Encryption;

public class Symbol implements Comparable<Symbol> {
    private final String symbol;
    private int freq;

    public Symbol(String symbol) {
        this.symbol = symbol;
        this.freq = 1;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    @Override
    public int compareTo(Symbol o) {
        return Integer.compare(this.freq, o.freq);
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "symbol='" + symbol + '\'' +
                ", freq=" + freq +
                '}';
    }
}
