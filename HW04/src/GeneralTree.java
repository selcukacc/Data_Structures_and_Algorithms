/** General tree yapisini kullanan bir sinif.
 * @author Islam Goktan Selcuk
 * */
public class GeneralTree< E extends Comparable < E > >
                            extends BinaryTree<E> {
    // Data fields
    /** Elemanin agaca eklenebilme durumu */
    private boolean addResult;
    /** postOrderSearch metodunun return degeri */
    private Node<E> postResult;

    /** root degeri null olan bos bir agac olusturur. */
    public GeneralTree() {
        root = null;
        postResult = null;
    }

    /**
     * Data parametresindeki degeri root'a atayarak bir agac olusturur.
     * @param data Root icin atanan deger
     */
    public GeneralTree(E data) {
        root = new Node<E>(data);
        root.left = null;
        root.right = null;
        postResult = null;
    }

    /**
     * Parent'in oldugu node'a child'i ekler ve true dondurur.
     * Eger parent agacta yoksa false dondurur.
     * @param parent Child'in eklenecegi eleman
     * @param child Parent'a ait node'a eklenecek eleman
     * @return Eklenme durumunu dondurur
     */
    public boolean add(E parent, E child) {
            add(root, parent, child);
            return addResult;
    }

    /**
     * add metodu icin yardimci metottur.
     * Eklenilmek istenen node'un son cocuguna ulasilir ve sagina eleman eklenir.
     * @param parent Child'in eklenecegi eleman
     * @param child Parent'a ait node'a eklenecek eleman
     * @return Bulunulan node'un sag node'una gecer
     */
    private Node<E> addLastChild(Node<E> parent, E child) {

        if(parent.right == null) {
            parent.right = new Node<>(child);
            return parent.right;
        }
        else {
            return addLastChild(parent.right, child);
        }
    }

    /**
     * starter add method'unda cagirilir
     * @param root agacin root node'u
     * @param parent Child'in eklenecegi eleman
     * @param child Parent'a ait node'a eklenecek eleman
     */
    private void add(Node<E> root, E parent, E child) {
        if(root == null)
            addResult = false;
        else if(parent.compareTo(root.data) == 0) {
            if(root.left == null)
                root.left = new Node<>(child);
            else
                addLastChild(root.left, child);
            addResult = true;
        }
        else if(root != null) {
            add(root.right, parent, child);
            add(root.left, parent, child);
        }

    }

    /**
     * Tum agac preOrderTraverse ile sb'ye aktarilir.
     * @return Agac string'e donusturulerek dondurulur.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Tum agac preOrder gezilerek StringBuilder'e aktarilir.
     * @param node The local root
     * @param depth The depth
     * @param sb The string buffer to save the output
     */
    @Override
    protected void preOrderTraverse(Node < E > node, int depth,
                                  StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if(node != null) {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            for(int i = sb.length()-1; i > 0 && (sb.charAt(i) == ' ' || sb.charAt(i) == '\n'); i--)
                sb.deleteCharAt(i);
            sb.append("\n");
            preOrderTraverse(node.right, depth, sb);
        }
    }

    /**
     * Seviye gezerek agac uzerinde arama yapilir.
     * @param target Aranan eleman.
     * @return Eger eleman agacta bulunuyorsa eleman dondurulur.
     * Bulunmuyorsa null dondururlur.
     */
    public E levelOrderSearch(E target) {
        E item = levelOrderSearch(root, target);
        return item;
    }

    /**
     * Started method'un icinde cagirilir.
     * @param root Aramanin yapilacagi agacin root node'u.
     * @param target Aranan eleman.
     * @return Eger eleman agacta bulunuyorsa eleman dondurulur.
     * Bulunmuyorsa null dondururlur.
     */
    private E levelOrderSearch(Node<E> root, E target) {
        System.out.println("\nLevel Order Search for " + target + ":");
        System.out.print("My Tree: ");
        if(root != null) {
            E item = searchSiblings(root, target);
            if(item != null && item.compareTo(target) == 0) {
                return item;
            }
        }
        while(root != null) {
            E item = searchingOneLevel(root, target);
            if(item != null && item.compareTo(target) == 0) {
                return item;
            }
            root = root.left;
        }
        return null;
    }

    /**
     * LevelOrderSearch method'u icin yardımcı method'tur.
     * Tek seviye icinde arama yapilir.
     * @param level Arama yapilmak istenen seviyenin bir ustu.
     * @param target Aranan eleman.
     * @return Eger eleman agacta bulunuyorsa eleman dondurulur.
     * Bulunmuyorsa null dondururlur.
     */
    private E searchingOneLevel(Node<E> level, E target) {

        if(level == null)
            return null;
        else if(level.left != null) {
            E found = searchSiblings(level.left, target);
            if(found != null)
                return target;
            else if(level.right != null)
                return searchingOneLevel(level.right, target);
            else
                return null;
        }
        else
            return null;
    }

    /**
     * LevelOrderSearch method'u icin yardımcı method'tur.
     * Kardes node'lar icinde arama yapilir.
     * @param node Aramanin yapilmak istendigi node.
     * @param target Aranan eleman
     * @return Eger eleman agacta bulunuyorsa eleman dondurulur.
     * Bulunmuyorsa null dondururlur.
     */
    private E searchSiblings(Node<E> node, E target) {
        if(node == null)
            return null;
        else if(node.data.compareTo(target) == 0)
            return target;
        else {
            System.out.print(node.data + " ");
            return searchSiblings(node.right, target);
        }
    }

    /**
     * PostOrderTraver ile agacta eleman aranir.
     * @param target Aranan eleman.
     * @return Eleman bulunursa elemanin bulundugu node dondurulur.
     * Bulunmazsa null dondurulur.
     */
    public Node<E> postOrderSearch(E target) {
        System.out.println("\nPost Order Search for " + target + ":");
        System.out.print("My Tree: ");
        postOrderTraverse(root, target, false);
        return postResult;
    }

    /**
     * PostOrderSearch icin yardimci method.
     * Verilen root icinde postOrderTravers ile target'i arar.
     * @param root Elemanin arandigi agacin root node'u.
     * @param target Aranan eleman.
     * @param found Elemanin bulunma durumunu tutar.
     * @return Eleman bulunursa elemanin bulundugu node dondurulur.
     * Bulunmazsa null dondurulur.
     */
    private boolean postOrderTraverse(Node<E> root, E target, boolean found) {
        if(root != null) {
            found = postOrderTraverse(root.left, target, found);
            found = postOrderTraverse(root.right, target, found);
            if(target != null && target.compareTo(root.data) == 0) {
                found = true;
                postResult = root;
            }

            System.out.print(root.data + " ");
        }
        if(found)
            return found;
        return false;
    }


}
