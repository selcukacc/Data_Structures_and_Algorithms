public class testClass {

    public static void main(String[] args) {
        /**
         * WORST CASE OF RED-BLACK TREE
         */
        System.out.println("\nWORST CASE OF RED-BLACK TREE\n");
        RedBlackTree<Integer> case1 = new RedBlackTree<>();
        case1.add(1);
        case1.add(2);
        case1.add(3);
        case1.add(4);
        case1.add(5);
        case1.add(6);
        case1.add(7);
        case1.add(8);
        case1.add(9);
        case1.add(10);
        case1.add(11);
        case1.add(12);
        case1.add(13);
        case1.add(14);
        System.out.println(case1 + "\n");

        RedBlackTree<String> case2 = new RedBlackTree<>();
        case2.add("The");
        case2.add("quick");
        case2.add("brown");
        case2.add("fox");
        case2.add("jumps");
        case2.add("over");
        case2.add("the");
        case2.add("lazy");
        case2.add("dog");


        System.out.println("Case2: \n" +case2);
    }
}
