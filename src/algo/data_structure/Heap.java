package algo.data_structure;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Heap {
    private List<Integer> tree;
    private final int MIN_ELEMENT_IDX = 1;

    public Heap() {
        tree = new ArrayList<Integer>();
        tree.add(null);
    }

    public void push(Integer element) {
        tree.add(element);
        bubleUp();
    }

    public Integer pop() {
        int lastIdx = getLastIdx();
        if ( lastIdx < MIN_ELEMENT_IDX ) {
            return -1;
        }
        Integer popData = tree.get(MIN_ELEMENT_IDX);
        Collections.swap(tree, MIN_ELEMENT_IDX, lastIdx);
        tree.remove(lastIdx);
        bubleDown();
        return popData;
    }

    private void bubleUp() {
        int currentIdx = getLastIdx();
        int parentIdx = currentIdx / 2;
        while (currentIdx > 1) {
            Integer element = tree.get(currentIdx);
            Integer parentElement = tree.get(parentIdx);
            if (element > parentElement) {
                break;
            }
            Collections.swap(tree, currentIdx, parentIdx);
            currentIdx = parentIdx;
            parentIdx = currentIdx / 2;
        }
    }

    private void bubleDown() {
        int currentIdx = MIN_ELEMENT_IDX;
        int lastIdx = getLastIdx();
        int childIdx = getChildIdx(currentIdx, lastIdx);
        while (currentIdx < lastIdx) {
            if (childIdx == -1) {
                break;
            }
            Integer element = tree.get(currentIdx);
            Integer childElement = tree.get(childIdx);
            if (element < childElement) {
                break;
            }
            Collections.swap(tree, currentIdx, childIdx);
            currentIdx = childIdx;
            childIdx = getChildIdx(currentIdx, lastIdx);
        }
    }

    private int getChildIdx(int idx, int lastIdx) {
        int leftChildIdx = idx * 2;
        int rightChildIdx = (idx * 2) + 1;
        if (leftChildIdx > lastIdx) {
            return -1;
        } else if (rightChildIdx > lastIdx) {
            return leftChildIdx;
        }
        int leftChild = tree.get(leftChildIdx);
        int rightChild = tree.get(rightChildIdx);
        return leftChild < rightChild ? leftChildIdx : rightChildIdx;
    }

    private int getLastIdx() {
        return tree.size() - 1;
    }

    public Integer peek() {
        return tree.get(MIN_ELEMENT_IDX);
    }

    public Integer size() {
        return tree.size();
    }
    public void display() {
        System.out.print("{");
        tree.stream().forEach(el -> System.out.print(el + ","));
        System.out.println("}");
    }
}
