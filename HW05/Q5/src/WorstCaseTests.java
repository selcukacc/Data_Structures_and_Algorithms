import java.util.LinkedList;

public class WorstCaseTests {
    private static int[] sizes = new int[4];

    public static void main(String[] args) {
        sizes[0] = 100;
        sizes[1] = 1000;
        sizes[2] = 5000;
        sizes[3] = 10000;
        worstInsertion();
        worstMergeDLL();
        worstMerge();
        worstHeap();
        worstQuick();
    }

    public static int[] reverseOrderedArray(int size) {
        int[] arr = new int[size];
        for(int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    public static void worstInsertion() {
        System.out.println("\nWorst case for INSERTION-SORT is: ");
        for(int i = 0; i < 4; i++) {
            int size = sizes[i];
            int[] temp = reverseOrderedArray(size);
            long first = System.nanoTime();
            AvarageRunTime.insertionSort(temp);
            long last = System.nanoTime();
            System.out.println("Array size " + size +": \"" + ((last-first) / 1000) +
                    "\" milliseconds.");
        }
    }

    public static LinkedList<Integer> createMergeDLLList(int size) {
        LinkedList<Integer> temp = new LinkedList<>();
        for(int i = 0; i < size; i += 2)
            temp.add(i);
        for(int i = 1; i < size; i+= 2)
            temp.add(i);

        return temp;
    }

    public static int[] createMergeArray(int size) {
        int[] temp = new int[size];
        for(int i = 0; i < size; i += 2)
            temp[i] = i;
        for(int i = 1; i < size; i += 2)
            temp[i] = i;

        return temp;
    }

    public static void worstMerge() {
        System.out.println("\nWorst case for MERGE-SORT is: ");
        for(int i = 0; i < 4; i++) {
            int size = sizes[i];
            int[] temp = createMergeArray(size);
            long first = System.nanoTime();
            AvarageRunTime.mergeSort(temp);
            long last = System.nanoTime();
            System.out.println("Array size " + size +": \"" + ((last-first)/1000) +
                    "\" milliseconds.");
        }
    }

    public static void worstMergeDLL() {
        System.out.println("\nWorst case for MERGE-DLL-SORT is: ");
        for(int i = 0; i < 4; i++) {
            int size = sizes[i];
            LinkedList<Integer> temp = createMergeDLLList(size);
            long first = System.nanoTime();
            MergeDLL.mergeSort(temp);
            long last = System.nanoTime();
            System.out.println("Array size " + size +": \"" + ((last-first)/1000) +
                    "\" milliseconds.");
        }
    }

    public static void worstHeap() {
        System.out.println("\nWorst case for HEAP-SORT is: ");
        for(int i = 0; i < 4; i++) {
            int size = sizes[i];
            int[] temp = reverseOrderedArray(size);
            long first = System.nanoTime();
            AvarageRunTime.heapSort(temp);
            long last = System.nanoTime();
            System.out.println("Array size " + size +": \"" + ((last-first) / 1000) +
                    "\" milliseconds.");
        }
    }

    public static void worstQuick() {
        System.out.println("\nWorst case for QUICK-SORT is: ");
        for(int i = 0; i < 4; i++) {
            int size = sizes[i];
            int[] temp = reverseOrderedArray(size);
            long first = System.nanoTime();
            AvarageRunTime.quickSort(temp);
            long last = System.nanoTime();
            System.out.println("Array size " + size +": \"" + ((last-first) / 1000) +
                    "\" milliseconds.");
        }
    }
}
