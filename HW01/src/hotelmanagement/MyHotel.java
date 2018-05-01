package hotelmanagement;

/**
 * @author Islam Goktan Selcuk
 * Student Number: 141044071
 * */

import java.io.*;
import java.util.Scanner;

public class MyHotel {
    private String[] allNames = new String[10];
    private String[] rooms = new String[10];

    public static Scanner scan = new Scanner(System.in);

    /**
     * Otelin odalarini ve isimleri tutan array'leri initialize eder.
     */
    public MyHotel() {
        for(int i = 0; i < 10; i++) {
            allNames[i] = "*";
            rooms[i] = "Empty";
        }
    }

    /**
     *Kullanici tipini alir ve input kontrolu yapar.
     *@param hotel Istenilen kullaniciya ait sinifin constructor'ına eklenir.
     *@return secilen kullanicinin turunden obje dondurur
     * */
    public SystemUser logInToSystem(MyHotel hotel) {
        System.out.println("Are you receptionist or guest?");
        System.out.println("'1' for receptionist");
        System.out.println("'2' for guest");
        //Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while(!input.equals("2") &&
                !input.equals("1")) {
            System.out.println("Invalid input.");
            System.out.println("'1' for receptionist");
            System.out.println("'2' for guest");
            input = scan.nextLine();
        }

        System.out.println("Enter your name: ");
        String name = scan.nextLine();
        if(input.equalsIgnoreCase("2")) {
            return new Guest(name, hotel);
        }
        else if(input.equalsIgnoreCase("1")) {
            return new Receptionist(name, hotel);
        }
        else
            return null;
    }

    /**
     * Kullanici yaptigi islemleri bitirdikten sonra programin
     * tekrar edip etmeyecegini belirler.
     * @return Eger true dondurulurse program tekrar kullanici alir,
     * aksi durumda program sonlandirilir.
     */
    public boolean programLoop() {
        System.out.println("'1' for continue.");
        System.out.println("'2' for ending program.");
        //Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        if(input.equalsIgnoreCase("2"))
            return false;
        else if(input.equalsIgnoreCase("1"))
            return true;
        else
            return false;
    }

    /**
     * Otele kayit edilen kullanicilari bir csv dosyasina kaydeder.
     */
    public void saveHotelMembers() {
        try(FileWriter fw = new FileWriter("myhotel.csv");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);) {

        for(int i = 0; i < 10; i++)
            out.println(getName(i) + "," + getRoom(i));
        }
        catch (IOException e) {
            System.out.println("File can not found.");
        }
    }

    /**
     * Otelin tum odalarini ve isimleri resetler.
     */
    public void cleanHotelMembers() {
        try(FileWriter fw = new FileWriter("myhotel.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);) {

            for(int i = 0; i < 10; i++) {
                out.println("*" + "," + "Empty");
                setRoom(i, "Empty");
                setName(i, "*");
            }
        }
        catch (IOException e) {
            System.out.println("File can not found.");
        }
    }

    /**
     * Program tekrar baslatildiginde kullanicilari dosyadan programa
     * aktarir.
     * @throws IOException Dosya bulunumazsa firlatilir.
     */
    public void syncHotelMembers() throws IOException {
        File f = new File("myhotel.csv");
        if(f.exists() && !f.isDirectory()) {
            Scanner scanner = new Scanner(f);
            for(int i = 0; i < 10 && scanner.hasNextLine(); i++){
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                setName(i, fields[0]);
                setRoom(i, fields[1]);
            }
            scanner.close();
        }
    }

    /**
     * Oteldeki odalarin tutuldugu arrayi duzenler.
     * @param index Gecerli index'deki odayi ayarlar.
     * @param value Ayarlanacak odanin degeri.
     */
    public void setRoom(int index, String value) {
        if(index >= 0 && index < 10)
            rooms[index] = value;
    }

    /**
     * @param index Istenilen odanin indeksi.
     * @return Odanin durumunu String olarak dondurur.
     */
    public String getRoom(int index) {
        if(index >= 0 && index < 10)
            return rooms[index];
        else
            return null;
    }

    /**
     * @return Tum odalarin tutuldugu array'i dondurur.
     */
    public String[] getRooms() { return rooms; }

    public void setName(int index, String name) {
        if(index >= 0 && index < 10)
            allNames[index] = name;
    }

    public String getName(int index) {
        return allNames[index];
    }
}
