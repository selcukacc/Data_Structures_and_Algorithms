
public class mainTest {

    public static void main(String[] argv) {
        test2();
    }

    public static void test1() {
        DoubleHashingMap<Integer, Integer> map1 = new DoubleHashingMap<>();

        for (int i = 0; i < 20; i++) {
            Integer nextInt = (int) (32000 * Math.random());
            map1.put(i, nextInt);
        }

        System.out.println("Integer map for testing Double hashing Map: ");
        for(int i = 0; i < 20; i++)
            System.out.println("Key: " + i + ", Value: " + map1.get(i));
    }

    public static void test2() {
        DoubleHashingMap<String, String> map = new DoubleHashingMap<>();

        for (int i = 0; i < 20; i++) {
            Integer nextInt = (int) (32000 * Math.random());
            map.put("" + i, "" + nextInt);
        }

        System.out.println("\nString map for testing Double hashing Map: ");
        for(int i = 0; i < 20; i++)
            System.out.println("Key: " + i + ", Value: " + map.get(i + ""));
    }
}
