package Part1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/** Hw3 Part1
 * @author Islam Goktan Selcuk
 * Number: 141044071
 * */
public class GtuCourseStructure {
    /**
     * Tum derslerin ve bilgilerinin tutuldugu icice linkedList.
     */
    private LinkedList< LinkedList<String> > courseList =
            new LinkedList< LinkedList<String> >();

    /**
     * Tum dersleri ve detaylarini dosyadan okuyarak
     * listeye kaydeder.
     * @param fileName bilgilerin alindigi dosya
     * @throws FileNotFoundException dosya bulunamazsa firlatilir
     */
    public void takeCoursesFromFile(String fileName)
            throws FileNotFoundException {
        File f = new File(fileName);

        if(!f.exists() || f.isDirectory())
            throw new FileNotFoundException();

        Scanner scanner = new Scanner(f);
        String line = scanner.nextLine();
        // dosya boyunca satir satir bilgiler okunur
        for (int i = 0; scanner.hasNextLine(); i++) {
            line = scanner.nextLine();
            // dosyadaki bilgiler ; sembolune gore ayirilir
            String[] fields = line.split(";");
            // her satir ayri bir listeye atanir
            LinkedList<String> tempList = new LinkedList<String>();
            // asil linkedList'e parcalara ayirilan bilgilerin tutuldugu
            // linkedlist eklenir
            for (int j = 0; j < fields.length; j++)
                tempList.add(fields[j]);
            courseList.add(tempList);
        }

    }

    /**
     * verilen listedeki bilgileri ekrana yazdirir
     * @param courseList // ekrana yazdirilacak liste
     */
    public void printCourseList(LinkedList< LinkedList<String> > courseList) {
        for(LinkedList<String> temp : courseList) {
            for(String x : temp)
                System.out.print(x + ",");
            System.out.println();
        }
    }

    /**
     * tum verileri tuttugumuz linkedList ekrana yazdirilir
     */
    public void printCourseList() {
        for(LinkedList<String> temp : courseList) {
            for(String x : temp)
                System.out.print(x + ",");
            System.out.println();
        }
    }

    /**
     * Verilen somestirdaki dersleri listeden alir.
     * @param semester // Istenilen derslere ait olan somestir kodu
     * @return // Somestir'a ait olan derslerin listesi return edilir
     * @throws CourseStructureException // eger verilen semester yoksa firlatilir
     */
    LinkedList<LinkedList<String>> listSemesterCourses(int semester)
                throws CourseStructureException {
        boolean found = false;
        LinkedList<LinkedList<String>> sameSemester = new LinkedList<>();
        //integer alinan degisken karsilastirma icin string'e donusturulur.
        String target = String.valueOf(semester);
        // istenen semester icin karsilastirma yapilir
        for(LinkedList<String> temp : courseList)
            if(temp.get(0).equals(target)) {
                sameSemester.add(temp);
                found = true;
            }
        // eger hic eslesme bulunmazsa exception firlatilir
        if(!found)
            throw new CourseStructureException("Semester can not found.");
        printCourseList(sameSemester);
        return sameSemester;
    }

    /**
     * Istenen ders koduna gore dersler bulunur
     * @param code // ders kodu
     * @return // bulunan ders kodlari bir linkedlist'e eklenerek return edilir
     * @throws CourseStructureException // eger ders bulunamazsa firlatilir
     */
    LinkedList<LinkedList<String>> getByCode(String code)
            throws CourseStructureException  {
        boolean found = false;
        LinkedList<LinkedList<String>> sameCourseCode = new LinkedList<>();
        // istenilen ders kodu icin karsilastirma yapilir
        for(LinkedList<String> temp : courseList)
            if(temp.get(1).equalsIgnoreCase(code)) {
                sameCourseCode.add(temp);
                found = true;
            }
        // eger hic eslesme bulunmazsa exception firlatilir
        if(!found)
            throw new CourseStructureException("Code can not found.");
        printCourseList(sameCourseCode);
        return sameCourseCode;
    }

    /**
     * Verilen index araligindaki dersler bulunur
     * @param start_index // baslangic indexi
     * @param last_index // bitis indexi
     * @return // istenilen araliktaki dersler linkedList'e eklenerek return edilir.
     * @throws IndexOutOfBoundsException // istenen index araligi listenin disindaysa firlatilir
     */
    LinkedList<LinkedList<String>> getByRange(int start_index, int last_index)
            throws IndexOutOfBoundsException {

        LinkedList<LinkedList<String>> sameCourseCode = new LinkedList<>();
        // index kontrolu
        if(start_index < 0 || start_index >= courseList.size() ||
                start_index > last_index)
            throw new IndexOutOfBoundsException();
        if(last_index >= courseList.size())
            throw new IndexOutOfBoundsException();

        // verilen araliktaki tum dersler linkedListe atanir
        ListIterator< LinkedList<String> > iter = courseList.listIterator(start_index - 1);
        for(int i = start_index; iter.hasNext() && i < last_index; i++)
            sameCourseCode.add(iter.next());
        printCourseList(sameCourseCode);
        return sameCourseCode;
    }
}
