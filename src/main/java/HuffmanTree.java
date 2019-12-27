public class HuffmanTree {
    private final byte ENCODING_TABLE_SIZE = 127;
    private String myString;
    private BinaryTree huffmanTree;
    private int[] freqArray;
    private String[] encodingArray;



    public HuffmanTree(String newString) {
        myString = newString;

        freqArray = new int[ENCODING_TABLE_SIZE];
        fillFrequenceArray();

        huffmanTree = getHuffmanTree();

        encodingArray = new String[ENCODING_TABLE_SIZE];
        fillEncodingArray(huffmanTree.getRoot(), "", "");
    }


    private void fillFrequenceArray() {
        for (int i = 0; i < myString.length(); i++) {
            freqArray[(int) myString.charAt(i)]++;
        }
    }

    public int[] getFrequenceArray() {
        return freqArray;
    }


    private BinaryTree getHuffmanTree() {
        PriorityQueue pq = new PriorityQueue();
        //алгоритм описан выше
        for (int i = 0; i < ENCODING_TABLE_SIZE; i++) {
            if (freqArray[i] != 0) {
                Node newNode = new Node((char) i, freqArray[i]);
                BinaryTree newTree = new BinaryTree(newNode);
                pq.insert(newTree);
            }
        }

        while (true) {
            BinaryTree tree1 = pq.remove();

            try {
                BinaryTree tree2 = pq.remove();

                Node newNode = new Node();
                newNode.addChild(tree1.getRoot());
                newNode.addChild(tree2.getRoot());

                pq.insert(new BinaryTree(newNode));
            } catch (IndexOutOfBoundsException e) {
                return tree1;
            }
        }
    }

    public BinaryTree getTree() {
        return huffmanTree;
    }


    void fillEncodingArray(Node node, String codeBefore, String direction) {
        if (node.isLeaf()) {
            encodingArray[(int) node.getLetter()] = codeBefore + direction;
        } else {
            fillEncodingArray(node.getLeftChild(), codeBefore + direction, "0");
            fillEncodingArray(node.getRightChild(), codeBefore + direction, "1");
        }
    }

    String[] getEncodingArray() {
        return encodingArray;
    }

    public void displayEncodingArray() {
        fillEncodingArray(huffmanTree.getRoot(), "", "");

        System.out.println("Encoding table");
        for (int i = 0; i < ENCODING_TABLE_SIZE; i++) {
            if (freqArray[i] != 0) {
                System.out.print((char) i + " ");
                System.out.println(encodingArray[i]);
            }
        }
        System.out.println("*********************************************");
    }


    String getOriginalString() {
        return myString;
    }
}
