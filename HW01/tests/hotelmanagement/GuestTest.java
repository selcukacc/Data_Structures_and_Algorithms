package hotelmanagement;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GuestTest {
    /**
     * Her test icerisinde tekrar obje olusturmamak icin
     * ana objeleri burada tanimladım.
     */
    MyHotel hotel = new MyHotel();
    Guest guest = new Guest("goktan", hotel);

    /**
     * Once methoda oda numarasi olarak 5 daha sonra 10 gonderilir
     * ve sonuclar test edilir.
     */
    @Test
    public void takeRoomNo() {
        String data = "5";
        MyHotel.scan = new Scanner(data);
        assertEquals(5, guest.takeRoomNo());
        data = "10";
        MyHotel.scan = new Scanner(data);
        assertEquals(10, guest.takeRoomNo());
    }

    /**
     * Otelin kapasitesi test edilir.
     */
    @Test
    public void size() {
        assertNotEquals(11, guest.size());
        assertEquals(10, guest.size());
    }

    /**
     * Otelin 2 numarali odasina rezervasyon yapilir
     * ve islemin sonucu test edilir.
     */
    @Test
    public void bookingTheRoom() {
        hotel.cleanHotelMembers();
        String data = "2";
        MyHotel.scan = new Scanner(data);
        assertEquals(true, guest.bookingTheRoom());
        hotel.cleanHotelMembers();
    }

    /**
     * Oncelikle 5 numarali oda icin rezervasyon iclemi yapilir.
     * Daha sonra guest tarafından cancel isleminin yapilip
     * yapilmadigini test eder.
     */
    @Test
    public void cancelReservation() {
        hotel.cleanHotelMembers();
        String data = "5";
        MyHotel.scan = new Scanner(data);
        guest.bookingTheRoom();
        data = "5";
        MyHotel.scan = new Scanner(data);
        assertEquals(true, guest.cancelReservation());
    }
}