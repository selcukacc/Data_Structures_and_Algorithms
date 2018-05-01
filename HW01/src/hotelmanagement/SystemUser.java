package hotelmanagement;

/**
 * @author Islam Goktan Selcuk
 * Student Number: 141044071
 * */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public abstract class SystemUser implements HotelSystem {



    /**
     * Otelin odalarini ve isimleri ayarlamak icin kullanilir.
     */
    protected MyHotel hotel;
    /**
     * Kullanicinin ismi.
     */
    private String name;
    /**
     * Kullanici turu(guest or receptionist)
     */
    private String userType;
    /**
     * Csv dosyasina kaydetmek icin islemin yapildigi zaman.
     */
    private Date date;
    /**
     * Kullanicinin sectigi oda numarasi.
     */
    private Integer roomNo;
    /**
     * Kullanicinin gerceklestirdigi islem.
     */
    private String process;

    public abstract void showOptions();

    /**
     * Booking isleminin iptal edilmesini saglar.
     * @return Islem basariliysa true dondurur.
     */
    public abstract boolean cancelReservation();

    /**
     * Otelin kapasitesini dondurur.
     * @return Otelin kapasitesini dondurur.
     */
    public int size() {
        return hotel.getRooms().length;
    }

    /**
     * Kullanicidan input kontrolu yaparak oda numarasi alir.
     * @return Oda numarasini dondurur.
     */
    protected int takeRoomNo() {
        while (true) {
            //Scanner scan = new Scanner(System.in);
            String input = MyHotel.scan.nextLine();
            for (Integer i = 1; i <= 10; i++)
                if (i.toString().equals(input))
                    return i;
            System.out.println("Wrong room number. Try again.");
        }
    }

    /**
     *
     * @return Kullanicinin gerceklestirdigi islemi dondurur.
     */
    public String getProcess() {
        return process;
    }

    /**
     * Kullanicinin gerceklestirdigi islemi kaydeder.
     * @param process Gerceklestirilen islem
     */
    public void setProcess(String process) {
        this.process = process;
    }

    /**
     * Eger oda bos ise booking islemini gerceklestirir.
     * @return Islem basariliysa true, degilse false dondurur.
     */
    public boolean bookingTheRoom() {
        System.out.println("Which room you want to make reservation?");
        System.out.println("Numbers of rooms: ");
        showTheRooms();
        /**
         * Oda numarasi kullanicidan alinir.
         */
        roomNo = takeRoomNo();
        /**
         * Eger gecerli oda bos ise booking islemi yapilir.
         */
        if(hotel.getRoom(roomNo-1).equalsIgnoreCase("Empty")) {
            hotel.setRoom(roomNo-1, "Booked");
            hotel.setName(roomNo-1, name);
            setProcess("Booked");
            showTheRooms();
            return true;
        }
        else {
            System.out.println("You do not have permission to do this.");
            showTheRooms();
            return false;
        }
    }

    /**
     * Kullanicinin yaptıgı islemi, kullanicinin tum bilgileriyle
     * birlikte dosyaya kaydeder.
     */
    public void writeTheFile() {
        try(FileWriter fw = new FileWriter("database.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            date = new Date();
            out.println(userType + "," + name + ",No:" + roomNo.toString() + "," + process + "," + date);
        } catch (IOException e) {
            //
        }
    }

    public void showTheRooms() {
        for (int i = 0; i < size(); i++)
            System.out.format("| %4s %-2d : %-8s |\n", "Room", (i+1), hotel.getRoom(i));
    }

    public String getName() {
        return name;
    }

    public String getUserType() {
        return userType;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }
}
