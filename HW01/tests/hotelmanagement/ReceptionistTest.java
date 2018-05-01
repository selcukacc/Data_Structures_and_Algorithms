package hotelmanagement;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ReceptionistTest {
    /**
     * Her test icerisinde tekrar obje olusturmamak icin
     * ana objeleri burada tanimladım.
     */
    MyHotel hotel = new MyHotel();
    Receptionist receptionist = new Receptionist("selcuk", hotel);

    /**
     * 9 numarali oda icin rezervaston isleminin yapilip
     * yapilmadigini test eder.
     */
    @Test
    public void bookingTheRoom() {
        hotel.cleanHotelMembers();
        String data = "9";
        MyHotel.scan = new Scanner(data);
        assertEquals(true, receptionist.bookingTheRoom());
        hotel.cleanHotelMembers();
    }

    /**
     * Oncelikle 3 numarali oda icin rezervasyon iclemi yapilir.
     * Daha sonra resepsiyonist tarafından cancel isleminin yapilip
     * yapilmadigini test eder.
     */
    @Test
    public void cancelReservation() {
        hotel.cleanHotelMembers();
        String data = "3";
        MyHotel.scan = new Scanner(data);
        receptionist.bookingTheRoom();
        data = "3";
        MyHotel.scan = new Scanner(data);
        assertEquals(true, receptionist.cancelReservation());
    }

    /**
     * 4 numarali odanin check-in islemini yapar ve islemin
     * sonucunu test eder.
     */
    @Test
    public void checkIn() {
        hotel.cleanHotelMembers();
        String data = "4";
        MyHotel.scan = new Scanner(data);
        assertEquals(true, receptionist.checkIn());
        hotel.cleanHotelMembers();
    }

    /**
     * Oncelikle 7 numarali oda icin check-in yapar. Daha sonra
     * 7 numarali odanin check-out islemini gerceklestirir.
     * Islem sonucunu test eder.
     */
    @Test
    public void checkOut() {
        hotel.cleanHotelMembers();
        String data = "7";
        MyHotel.scan = new Scanner(data);
        receptionist.checkIn();
        data = "7";
        MyHotel.scan = new Scanner(data);
        assertEquals(true, receptionist.checkOut());
    }

}