package Huffman_Encryption;

public class Node implements Comparable<Node> {
    private final Symbol parent;
    private final Node left;
    private final Node right;

    public Node(Symbol symbol, Node left, Node right) {
        this.parent = symbol;
        this.left = left;
        this.right = right;
    }

    public Symbol getParent() {
        return parent;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.parent.getFreq(), o.getParent().getFreq());
    }
}