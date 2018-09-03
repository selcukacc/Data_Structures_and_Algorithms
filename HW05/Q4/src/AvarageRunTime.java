import java.util.LinkedList;

public class AvarageRunTime {

    public static void insertionSort(int[] table) {
        for(int index = 1; index < table.length; index++) {
            insert(table, index);
        }
    }

    private static void insert(int[] table, int index) {
        int value = table[index];
        while(index > 0 && value < table[index - 1]) {
            table[index] = table[index - 1];
            index--;
        }
        table[index] = value;
    }

    public static void mergeSort(int[] table) {
        if(table.length > 1) {
            int halfSize = table.length / 2;
            int[] leftTable = new int[halfSize];
            int[] rightTable = new int[table.length - halfSize];
            System.arraycopy(table, 0, leftTable, 0, halfSize);
            System.arraycopy(table, halfSize, rightTable, 0, table.length - halfSize);

            mergeSort(leftTable);
            mergeSort(rightTable);

            merge(table, leftTable, rightTable);
        }
    }

    public static void merge(int[] output, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while(i < left.length && j < right.length) {
            if(left[i] < right[j])
                output[k++] = left[i++];
            else
                output[k++] = right[j++];
        }

        while(i < left.length)
            output[k++] = left[i++];
        while(j < right.length)
            output[k++] = right[j++];
    }

    public static void quickSort(int[] table) {
        quickSort(table, 0, table.length - 1);
    }

    private static void quickSort(int[] table, int firstIndex, int lastIndex) {
        if(firstIndex < lastIndex) {
            int pivIndex = partition(table, firstIndex, lastIndex);
            quickSort(table, firstIndex, pivIndex - 1);
            quickSort(table, pivIndex + 1, lastIndex);
        }
    }

    private static int partition(int[] table, int firstIndex, int lastIndex) {
        int pivot = table[firstIndex];
        int up = firstIndex;
        int down = lastIndex;

        do {
            while(up < lastIndex && pivot >= table[up])
                up++;
            while(pivot < table[down])
                down--;

            if(up < down)
                swap(table, up, down);

        } while(up < down);

        swap(table, firstIndex, down);
        return down;
    }

    private static void swap(int[] table, int i, int j) {
        int temp = table[i];
        table[i] = table[j];
        table[j] = temp;
    }

    public static void heapSort(int[] table) {
        buildHeap(table);
        shrinkHeap(table);
    }

    private static void buildHeap(int[] table) {
        int n = 1;

        while(n < table.length) {
            n++;
            int child = n - 1;
            int parent = (child - 1) / 2; // Find parent.
            while (parent >= 0 && table[parent] < table[child]) {
                swap(table, parent, child);
                child = parent;
                parent = (child - 1) / 2;
            }
        }
    }

    private static void shrinkHeap(int[] table) {
        int n = table.length;

        // Invariant: table[0 . . . n - 1] forms a heap.
        // table[n . . . table.length - 1] is sorted.
        while (n > 0) {
            n--;
            swap(table, 0, n);
            // table[1 . . . n - 1] form a heap.
            // table[n . . . table.length - 1] is sorted.
            int parent = 0;
            while (true) {
                int leftChild = 2 * parent + 1;
                if (leftChild >= n) {
                    break; // No more children.
                }
                int rightChild = leftChild + 1;
                // Find the larger of the two children.
                int maxChild = leftChild;
                if (rightChild < n // There is a right child.
                        && table[leftChild] < table[rightChild]) {
                    maxChild = rightChild;
                }
                // If the parent is smaller than the larger child,
                if (table[parent] < table[maxChild]) {
                    // Swap the parent and child.
                    swap(table, parent, maxChild);
                    // Continue at the child level.
                    parent = maxChild;
                }
                else { // Heap property is restored.
                    break; // Exit the loop.
                }
            }
        }
    }


}
