package Part1;
import java.io.FileNotFoundException;
/** Hw3 Part1 MainTest
 * @author Islam Goktan Selcuk
 * Number: 141044071
 * */

public class Part1MainTest {
    public static void main(String args[]) throws FileNotFoundException {

        try {
            GtuCourseStructure obj = new GtuCourseStructure();
            obj.takeCoursesFromFile("Courses.csv");

            //obj.printCourseList();
            System.out.println("Part1: getByRange() test");
            obj.getByRange(12, 18);
            System.out.println("\nPart1: listSemesterCourses() test");
            obj.listSemesterCourses(3);
            System.out.println("\nPart1: getByCode() test");
            obj.getByCode("XXX XXX");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
