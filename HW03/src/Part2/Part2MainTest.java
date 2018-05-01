package Part2;
/** Hw3 Part2 MainTest
 * @author Islam Goktan Selcuk
 * Number: 141044071
 * */
public class Part2MainTest {
    public static void main(String args[]) {
        try {
            System.out.println("Part2: Test1");
            MyLinkedList<Integer> a = new MyLinkedList();
            a.add(12);
            a.add(21);
            a.add(42);
            a.add(94);
            a.add(18);
            a.add(23);
            System.out.println(a);
            a.disable(2);
            a.disable(4);
            System.out.println(a);
            a.enable(23);
            a.disable(2);
            a.enable(42);
            a.enable(94);
            System.out.println(a + "\n\nPart2: Test2");

            MyLinkedList<String> b = new MyLinkedList();
            b.add("gtu");
            b.add("CScience");
            b.add("test");
            b.add("part2");
            System.out.println(b);
            b.disable(3);
            b.disable(2);
            System.out.println(b);
            b.enable("test");
            b.enable("part2");
            System.out.println(b);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
