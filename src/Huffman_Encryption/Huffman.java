package Huffman_Encryption;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Huffman extends PriorityQueue {

    public Huffman(List<Symbol> symbols) {
        for(Symbol symbol : symbols) {
            Node node = new Node(symbol, null, null);
            enqueue(node);
        }
    }

    public Node huffmanAlgorithm() {
        while (size() > 1) {
            Node left = dequeue();
            Node right = dequeue();
            Symbol combinedSymbol = new Symbol(left.getParent().getSymbol() + right.getParent().getSymbol());
            combinedSymbol.setFreq(left.getParent().getFreq() + right.getParent().getFreq());
            Node parent = new Node(combinedSymbol, left, right);
            enqueue(parent);
        }
        return dequeue();
    }

    public Map<String, String> generateHuffmanCodes(Node root) {
        Map<String, String> codes = new HashMap<>();
        generateCodesRecursive(root, "", codes);
        return codes;
    }

    private void generateCodesRecursive(Node node, String code, Map<String, String> codes) {
        if (node.isLeaf()) {
            codes.put(node.getParent().getSymbol(), code);
            return;
        }
        if (node.getLeft() != null) {
            generateCodesRecursive(node.getLeft(), code + "1", codes);
        }
        if (node.getRight() != null) {
            generateCodesRecursive(node.getRight(), code + "0", codes);
        }
    }

    public void printTree(Node root, int depth) {
        if (root == null) {
            return;
        }
        printTree(root.getRight(), depth + 1);
        System.out.println(" ".repeat(depth * 8) + root.getParent().getSymbol() + " [" + root.getParent().getFreq() + "]");
        printTree(root.getLeft(), depth + 1);
    }
}
