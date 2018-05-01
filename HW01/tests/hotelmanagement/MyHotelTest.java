package hotelmanagement;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class MyHotelTest {
    MyHotel hotel = new MyHotel();

    @Test
    public void setRoom() {
        hotel.setRoom(0, "Booked");
        assertEquals(hotel.getRoom(0), "Booked");
    }

    @Test
    public void setName() {
        hotel.setName(1, "kafka");
        assertEquals(hotel.getName(1), "kafka");
    }

    /**
     * Sisteme kafka isminde bir recepiyonist olarak girilir.
     * Constructor kullanilarak olusturulan baska bir resepsiyonist
     * ile isim karsirlastirmasi yapilarak method test edilir.
     */
    @Test
    public void logInToSystem() {
        String data = "1" + "\nkafka";
        MyHotel.scan = new Scanner(data);
        SystemUser user = hotel.logInToSystem(hotel);
        Receptionist rec = new Receptionist("kafka", hotel);
        assertEquals(rec.getName(), user.getName());
    }
}