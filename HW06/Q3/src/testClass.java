public class testClass {

    public static void main(String[] args) {

        System.out.println("BinaryTree Constructor\n");
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
        binaryTree.add(1);
        binaryTree.add(-1);
        binaryTree.add(2);
        binaryTree.add(3);
        binaryTree.add(-2);
        AVLTree<Integer> case3 = new AVLTree<>(binaryTree);
        System.out.println(case3);


        /**
         * LEFT-LEFT TEST CASE
         */
        System.out.println("\nLEFT-LEFT TEST CASE\n");
        AVLTree<Integer> case1 = new AVLTree<>();
        case1.add(50);
        case1.add(25);
        case1.add(60);
        case1.add(30);
        case1.add(15);
        case1.add(5);
        case1.add(10);
        case1.add(20);
        case1.add(40);
        System.out.println(case1.toString());


        /**
         * LEFT-RIGHT TEST CASE
         */
        System.out.println("\nLEFT-RIGHT TEST CASE\n");
        AVLTree<Integer> case2 = new AVLTree<>();
        case2.add(50);
        case2.add(25);
        case2.add(60);
        case2.add(30);
        case2.add(15);
        case2.add(20);
        case2.add(40);
        System.out.println(case2.toString());
    }
}
