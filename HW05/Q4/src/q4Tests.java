import java.util.LinkedList;

public class q4Tests {
    public static void main(String[] args) {
        mergeWithDLLTime();
        insertionTime();
        mergeTime();
        quickTime();
        heapTime();
    }

    public static int[] createRandomArray(int size) {
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            Integer nextInt = (int) (32000 * Math.random());
            arr[i] = nextInt;
        }

        return arr;
    }

    public static void mergeTime() {
        int size = 1000;
        long average = 0;
        for (int i = 0; i < 10; i++) {
            int[] temp = createRandomArray(size);
            long first = System.nanoTime();
            AvarageRunTime.mergeSort(temp);
            long last = System.nanoTime();
            average += (last - first);
        }

        average /= 10;
        System.out.println("\nAverage of MERGE-SORT is \"" + average/1000 + "\" milliseconds.(array size: 1000)");
    }

    public static void insertionTime() {
        int size = 1000;
        long average = 0;
        for (int i = 0; i < 10; i++) {
            int[] temp = createRandomArray(size);
            long first = System.nanoTime();
            AvarageRunTime.insertionSort(temp);
            long last = System.nanoTime();
            average += (last - first);
        }

        average /= 10;
        System.out.println("\nAverage of INSERTION-SORT is \"" + average/1000 + "\" milliseconds.(array size: 1000)");
    }

    public static void heapTime() {
        int size = 1000;
        long average = 0;
        for (int i = 0; i < 10; i++) {
            int[] temp = createRandomArray(size);
            long first = System.nanoTime();
            AvarageRunTime.heapSort(temp);
            long last = System.nanoTime();
            average += (last - first);
        }

        average /= 10;
        System.out.println("\nAverage of HEAP-SORT is \"" + average/1000 + "\" milliseconds.(array size: 1000)");
    }

    public static void quickTime() {
        int size = 1000;
        long average = 0;
        for (int i = 0; i < 10; i++) {
            int[] temp = createRandomArray(size);
            long first = System.nanoTime();
            AvarageRunTime.quickSort(temp);
            long last = System.nanoTime();
            average += (last - first);
        }

        average /= 10;
        System.out.println("\nAverage of QUICK-SORT is \"" + average/1000 + "\" milliseconds.(array size: 1000)");
    }

    public static void mergeWithDLLTime() {
        int size = 1000;
        long average = 0;

        for (int i = 0; i < 10; i++) {
            LinkedList<Integer> temp = new LinkedList<>();
            for(int j = 0; j < size; j++) {
                Integer nextInt = (int) (32000 * Math.random());
                temp.add(nextInt);
            }

            long first = System.nanoTime();
            MergeDLL.mergeSort(temp);
            long last = System.nanoTime();
            average += (last - first);
        }

        average /= 10;
        System.out.println("\nAverage of MERGE-SORT-WITH-DLL is \"" + average/1000 + "\" milliseconds.(array size: 1000)");
    }
}
