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

    public String printHuffmanCodes(Map<String, String> codes) {
        StringBuilder visualDictionary = new StringBuilder();
        System.out.println("Kody Huffmana:");

        codes.forEach((symbol, code) -> {
            System.out.println(symbol + " -> " + code + ",");
            visualDictionary.append(symbol).append(" -> ").append(code).append(",\n");
        });

        if (!visualDictionary.isEmpty()) {
            visualDictionary.setLength(visualDictionary.length() - 2);
        }

        return visualDictionary.toString();
    }

    public void printTree(Node root, int depth) {
        // Older version of printHorizontalTree

        if (root == null) {
            return;
        }
        printTree(root.getRight(), depth + 1);

        String originalSymbol = root.getParent().getSymbol();
        String modifiedSymbol = originalSymbol;
        if(root.hasRightLeaf(root)) {
            System.out.println(" ".repeat(depth * 8 + 10) + "/");
        }

        if (originalSymbol.contains("\n")) {
            modifiedSymbol = originalSymbol.replace("\n", "{Enter}");
        } else if (originalSymbol.contains("\t")) {
            modifiedSymbol = originalSymbol.replace("\t", "{Tab}");
        }
        System.out.println(" ".repeat(depth * 8) + modifiedSymbol + " [" + root.getParent().getFreq() + "]");

        if(root.hasLeftLeaf(root)) {
            System.out.println(" ".repeat(depth * 8 + 10) + "\\");
        }
        printTree(root.getLeft(), depth + 1);
    }

    public void printHorizontalTree(Node node, int depth, String branch) {
        if (node == null) {
            return;
        }

        printHorizontalTree(node.getRight(), depth + 1, "/");

        String nodeSymbol = node.getParent().getSymbol();
        if (nodeSymbol.contains("\n")) {
            nodeSymbol = nodeSymbol.replace("\n", "{Enter}");
        } else if (nodeSymbol.contains("\t")) {
            nodeSymbol = nodeSymbol.replace("\t", "{Tab}");
        }
        System.out.printf("%s%s%s [%d]%n",
                " ".repeat(depth * 10),
                branch,
                nodeSymbol,
                node.getParent().getFreq()
        );

        printHorizontalTree(node.getLeft(), depth + 1, "\\");
    }
}
