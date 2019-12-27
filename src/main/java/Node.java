public class Node {
    private int frequence;
    private char letter;
    private Node leftChild;
    private Node rightChild;



    public Node(char letter, int frequence) {
        this.letter = letter;
        this.frequence = frequence;
    }

    public Node() {}
    public void addChild(Node newNode) {
        if (leftChild == null)
            leftChild = newNode;
        else {
            if (leftChild.getFrequence() <= newNode.getFrequence())
                rightChild = newNode;
            else {
                rightChild = leftChild;
                leftChild = newNode;
            }
        }

        frequence += newNode.getFrequence();
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public int getFrequence() {
        return frequence;
    }

    public char getLetter() {
        return letter;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }
}