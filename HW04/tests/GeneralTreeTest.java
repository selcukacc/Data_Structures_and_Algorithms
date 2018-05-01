import org.junit.Test;

public class GeneralTreeTest {

    GeneralTree<String> x = new GeneralTree<>("0");
    GeneralTree<Integer> y = new GeneralTree<>(-1);

    public void initializeTheTree() {
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
        x.add("1", "11");

        y.add(-1, -2);
        y.add(-2, -3);
        y.add(-2, -4);
        y.add(-1, -5);
        y.add(-1, -6);
        y.add(-1, -7);
        y.add(-5, -8);
        y.add(-5, -9);
        y.add(-3, -10);
        y.add(-10, -11);
        y.add(-11, -12);
    }

    @org.junit.Test
    public void add() {
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
        x.add("1", "11");

        y.add(-1, -2);
        y.add(-2, -3);
        y.add(-2, -4);
        y.add(-1, -5);
        y.add(-1, -6);
        y.add(-1, -7);
        y.add(-5, -8);
        y.add(-5, -9);
        y.add(-3, -10);
        y.add(-10, -11);
        y.add(-11, -12);

        System.out.println("Birinci Agac: ");
        System.out.println(x.toString());
        System.out.println("Ikinci Agac: ");
        System.out.println(y.toString());

        // Eklenmenin basarisiz oldugu durum
        System.out.println("Agacta olmayan elemana(-19) child ekleme durumu: " +
                y.add(-19, -5));
    }

    @org.junit.Test
    public void preOrderTraverse() {
        // PreOrderTraverse metodu toString icin yardimci method oldugu icin private
        // tanimlandi. Dolayisiyla test toString metoduyla yapildi.
        initializeTheTree();
        System.out.println("Birinci agac: \n" + x.toString());
        System.out.println("Ikinci agac: \n" + y.toString());
    }

    @org.junit.Test
    public void levelOrderSearch() {
        initializeTheTree();
        // Agacta olmayan bir elemani aratarak tum liste gezdirilir.
        x.levelOrderSearch("111");
        // Agacta bulunan bir eleman icin arama yapilir.
        // Elemana ulasildiginda arama durur ve eleman return edilir.
        String item = x.levelOrderSearch("10");
        System.out.println("Target found: " + item);

        // Agacta olmayan bir elemani aratarak tum liste gezdirilir.
        y.levelOrderSearch(-122);
        // Agacta bulunan bir eleman icin arama yapilir.
        // Elemana ulasildiginda arama durur ve eleman return edilir.
        int item2 = y.levelOrderSearch(-4);
        System.out.println("Target found: " + item2);
    }

    @Test
    public void postOrderSearch() {
        initializeTheTree();
        // Agacta olmayan bir elemani aratarak tum liste gezdirilir.
        x.postOrderSearch("111");
        // Agacta bulunan bir eleman icin arama yapilir.
        // Elemana ulasildiginda elemanin bulundugu node return edilir.
        BinaryTree.Node<String> item = x.postOrderSearch("10");
        System.out.println("Target found: " + item);

        // Agacta olmayan bir elemani aratarak tum liste gezdirilir.
        y.postOrderSearch(12345);
        // Agacta bulunan bir eleman icin arama yapilir.
        // Elemana ulasildiginda elemanin bulundugu node return edilir.
        BinaryTree.Node<Integer> item2 = y.postOrderSearch(-4);
        System.out.println("Target found: " + item2);
    }
}