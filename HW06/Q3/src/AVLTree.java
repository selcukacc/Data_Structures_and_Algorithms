public class AVLTree<E extends Comparable<E>>
        extends BinarySearchTreeWithRotate<E> {

    private static class AVLNode<E> extends Node<E> {

        public static final int LEFT_HEAVY = -1;
        public static final int BALANCED = 0;
        public static final int RIGHT_HEAVY = 1;

        private int balance;

        /**
         * Construct a node with given data and no children.
         * @param data The data to store in this node
         */
        public AVLNode(E data) {
            super(data);
            balance = BALANCED;
        }

        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }

    private boolean increase;
    private boolean decrease;

    public AVLTree() {}

    public AVLTree(BinarySearchTree<E> tree) {
        if(isAVLTree(tree.root, true)) {
            System.out.println("Given BinaryTree is an AVL tree.");
            binaryToAvl(tree.root, this);
        }
        else {
            System.out.println("Given BinaryTree is not an AVL tree.");
        }
    }

    private void binaryToAvl(Node<E> binNode, AVLTree<E> avlTree) {
        if(binNode != null) {
            avlTree.add(binNode.data);
            binaryToAvl(binNode.left, avlTree);
            binaryToAvl(binNode.right, avlTree);
        }
    }

    private boolean isAVLTree(Node node, boolean AVL) {
        if(node != null) {
            int left = leftDepth(node);
            int right = rightDepth(node);
            if(right-left > 1 || right-left < -1)
                AVL = false;
            AVL = isAVLTree(node.left, AVL);
            AVL = isAVLTree(node.right, AVL);

        }
        return AVL;
    }

    private int leftDepth(Node<E> node) {
        if(node != null) {
            return leftDepth(node.left) + 1;
        }
        return 0;
    }

    private int rightDepth(Node<E> node) {
        if(node != null) {
            return rightDepth(node.right) + 1;
        }
        return 0;
    }

    /**
     * add starter method.
     * @pre the item to insert implements the Comparable interface.
     * @param item The item being inserted.
     * @return true if the object is inserted; false
     *         if the object already exists in the tree
     * @throws ClassCastException if item is not Comparable
     */
    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    private AVLNode<E> add(AVLNode<E> localRoot, E item) {
        if(localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode<>(item);
        }

        if(item.compareTo(localRoot.data) == 0) {
            addReturn = false;
            increase = false;
            return localRoot;
        }
        else if(item.compareTo(localRoot.data) < 0) {
            localRoot.left = add((AVLNode<E>) localRoot.left, item);

            if(increase) {
                decrementBalance(localRoot);
                if(localRoot.balance < -1) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot;
        }
        else {
            localRoot.right = add((AVLNode<E>) localRoot.right, item);
            if(increase) {
                incrementBalance(localRoot);
                if(localRoot.balance > 1) {
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot;
        }

    }

    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
        if(leftChild.balance > 0) {
            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;

            if(leftRightChild.balance < 0) {
                leftChild.balance = 0;
                leftRightChild.balance = 0;
                localRoot.balance = 1;
            }
            else {
                leftChild.balance = -1;
                leftRightChild.balance = 0;
                localRoot.balance = 0;
            }

            localRoot.left = rotateLeft(leftChild);
        }
        else {
            leftChild.balance = 0;
            localRoot.balance = 0;
        }
        return (AVLNode<E>) rotateRight(localRoot);
    }

    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        AVLNode<E> rightChild = (AVLNode<E>)localRoot.right;
        if(rightChild.balance < 0) {
            AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;
            if(rightLeftChild.balance > 0) {
                rightChild.balance = 0;
                rightLeftChild.balance = 0;
                localRoot.balance = -1;
            }
            else if(rightLeftChild.balance < 0) {
                rightChild.balance = 1;
                rightLeftChild.balance = 0;
                localRoot.balance = 0;
            }
            else {
                localRoot.balance = 0;
                rightChild.balance = 0;
                rightLeftChild.balance = 0;
            }

            increase = false;
            decrease = true;
            localRoot.right = rotateRight(rightChild);
            return (AVLNode<E>)rotateLeft(localRoot);
        }
        else {
            rightChild.balance = 0;
            localRoot.balance = 0;
            increase = false;
            decrease = true;
            return (AVLNode<E>) rotateLeft(localRoot);
        }
    }

    private void decrementBalance(AVLNode<E> node) {
        node.balance -= 1;
        if(node.balance == 0)
            increase = false;
    }

    private void incrementBalance(AVLNode<E> node) {
        node.balance += 1;
        if(node.balance > 0) {
            increase = true;
            decrease = false;
        }
        else {
            increase = false;
            decrease = true;
        }
    }

    public E delete(E item) {
        decrease = false;
        root = delete((AVLNode<E>) root, item);
        return deleteReturn;
    }

    private AVLNode<E> delete(AVLNode<E> localRoot, E item) {
        if(localRoot == null) {
            deleteReturn = null;
            return localRoot;
        }
        if(item.compareTo(localRoot.data) == 0) {
            deleteReturn = localRoot.data;
            return findNode(localRoot);
        }
        else if(item.compareTo(localRoot.data) < 0) {
            localRoot.left = delete((AVLNode<E>)localRoot.left, item);
            if(decrease) {
                incrementBalance(localRoot);
                if(localRoot.balance > 1)
                    return rebalanceRight((AVLNode<E>) localRoot);
            }
            return localRoot;
        }
        else {
            localRoot.right = delete( (AVLNode < E > ) localRoot.right, item);
            if (decrease) {
                decrementBalance(localRoot);
                if (localRoot.balance < -1)
                    return rebalanceLeft(localRoot);
            }

            return localRoot;
        }
    }

    private AVLNode<E> findNode(AVLNode<E> node) {
        if(node.left == null) {
            decrease = true;
            return (AVLNode<E>)node.right;
        }
        else if(node.right == null) {
            decrease = true;
            return (AVLNode<E>)node.left;
        }
        else {
            if(node.left.right == null) {
                node.data = node.left.data;
                node.left = node.left.left;
                incrementBalance(node);
                return node;
            }
            else {
                node.data = findLargest((AVLNode<E>)node.left);
                if(((AVLNode<E>)node.left).balance < -1)
                    node.left = rebalanceLeft((AVLNode<E>)node.left);
                if(decrease)
                    incrementBalance(node);
                return node;
            }
        }
    }

    private E findLargest(AVLNode<E> localRoot) {
        if(localRoot.right.right == null) {
            E temp = localRoot.right.data;
            localRoot.right = localRoot.right.left;
            decrementBalance(localRoot);
            return temp;
        }
        else {
            E temp = findLargest((AVLNode<E>) localRoot.right);
            if(((AVLNode<E>) localRoot.right).balance < -1)
                localRoot.right = rebalanceLeft((AVLNode<E>)localRoot.right);
            if(decrease)
                decrementBalance(localRoot);
            return temp;
        }
    }
}
