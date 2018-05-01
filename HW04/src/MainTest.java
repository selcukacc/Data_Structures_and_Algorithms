import java.io.*;

public class MainTest {

    public static void main(String args[]) {
        GeneralTree<String> x = new GeneralTree<>();
        x.root = new BinaryTree.Node<String>("0");
        x.add("0", "1");
        x.add("0", "2");
        x.add("0", "3");
        x.add("1", "4");
        x.add("1", "5");
        x.add("2", "6");
        x.add("2", "7");
        x.add("3", "8");
        x.add("3", "9");
        x.add("4", "10");
        x.add("10", "11");
        x.root = x.root.left;
        System.out.println("PreOrderTravers method'u ile agacin gosterimi:");
        System.out.println(x.toString());

        System.out.print("Elemanin bulunmadigi durum icin levelOrderSearch:");
        String y = x.levelOrderSearch("111");
        System.out.println("\nMethod result: " + y);

        System.out.print("\nElemanin bulundugu durum icin levelOrderSearch:");
        y = x.levelOrderSearch("11");
        System.out.println("\nMethod result: " + y);

        System.out.print("\nElemanin bulunmadigi durum icin PostOrderSearch:");
        GeneralTree.Node<String> z = x.postOrderSearch("678");
        System.out.println("\nMethod result: " + z);

        System.out.print("\nElemanin bulundugu durum icin PostOrderSearch:");
        z = x.postOrderSearch("8");
        System.out.println("\nMethod result: " + z);
    }

}
