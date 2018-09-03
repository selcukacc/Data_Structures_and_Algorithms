import java.util.LinkedList;

public class MergeDLL {
    public static void mergeSort(LinkedList<Integer> table) {
        if(table.size() > 1) {
            int halfSize = table.size() / 2;
            LinkedList<Integer> leftTable = new LinkedList<Integer>();
            LinkedList<Integer> rightTable = new LinkedList<Integer>();

            int k = 0;
            for(int i : table) {
                if (k < halfSize)
                    leftTable.add(i);
                else
                    rightTable.add(i);
                k++;
            }

            mergeSort(leftTable);
            mergeSort(rightTable);
            merge(table, leftTable, rightTable);
        }
    }

    public static void merge(LinkedList<Integer> output,
                             LinkedList<Integer> left,
                             LinkedList<Integer> right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while(i < left.size() && j < right.size()) {
            if(left.get(i) < right.get(j))
                output.set(k++, left.get(i++));
            else
                output.set(k++, right.get(j++));
        }

        while(i < left.size())
            output.set(k++, left.get(i++));
        while(j < right.size())
            output.set(k++, right.get(j++));
    }

    public static void main(String[] argc) {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            Integer nextInt = (int) (32000 * Math.random()) % 100;
            list.add(nextInt);
        }

        for(int i : list)
            System.out.print(i + " ");

        System.out.println();
        mergeSort(list);
        for(int i : list)
            System.out.print(i + " ");
    }
}
