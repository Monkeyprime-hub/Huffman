import java.util.ArrayList;
import java.util.HashMap;

class PriorityQueue {
    private ArrayList<BinaryTree> data;
    private int nElems;

    public PriorityQueue() {
        data = new ArrayList<BinaryTree>();
        nElems = 0;
    }

    public void insert(BinaryTree newTree) {
        if (nElems == 0)
            data.add(newTree);
        else {
            for (int i = 0; i < nElems; i++) {
                if (data.get(i).getFrequence() > newTree.getFrequence()) {
                    data.add(i, newTree);
                    break;
                }
                if (i == nElems - 1)
                    data.add(newTree);
            }
        }
        nElems++;
    }

    public BinaryTree remove() {
        BinaryTree tmp = data.get(0);
        data.remove(0);
        nElems--;
        return tmp;
    }
}