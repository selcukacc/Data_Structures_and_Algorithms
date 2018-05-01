package hotelmanagement;

/*
 * @author Islam Goktan Selcuk
 * Student Number: 141044071
 * */

import java.util.Scanner;

public class Receptionist extends SystemUser {
    /**
     * Alinan parametrelere gore bir resepsiyonist objesi olusturulur.
     * @param name Resepsiyonistin ismi.
     * @param hotel Resepsiyonistin kaydinin tutuldugu hotel objesi.
     */
    public Receptionist(String name, MyHotel hotel) {
        setName(name);
        this.hotel = hotel;
        setUserType("Receptionist");
    }

    @Override
    public void showOptions() {
        /**
         * Eger secilen islem basarisiz olursa
         * dosyaya yazdirilmamasini saglar
         */
        boolean result = false;
        /**
         * Gecersiz imput alinirsa donguyu surdurur.
         */
        boolean invalidInput = true;
        while(invalidInput) {
            System.out.println("What do you want to do?");
            System.out.println("'1' for booking.");
            System.out.println("'2' for canceling.");
            System.out.println("'3' for check-in.");
            System.out.println("'4' for check-out.");
            String choice = MyHotel.scan.nextLine();

            if (choice.equals("1")) {
                result = bookingTheRoom();
                invalidInput = false;
            }
            else if (choice.equals("2")) {
                result = cancelReservation();
                invalidInput = false;
            }
            else if (choice.equals("3")) {
                result = checkIn();
                invalidInput = false;
            }
            else if (choice.equals("4")) {
                result = checkOut();
                invalidInput = false;
            }
            else {
                System.out.println("Invalid input. Try again.");
            }
        }
        /**
         * Eger yapilan islem basariliysa dosyaya yazdirilir.
         */
        if(result)
            writeTheFile();
    }

    @Override
    public boolean cancelReservation() {
        System.out.println("Which room you want to cancel reservation?");
        System.out.println("Numbers of rooms: ");
        showTheRooms();
        /**
         * Kullanicidan oda numarasi alinip objeye kaydedilir.
         */
        setRoomNo(takeRoomNo());
        /**
         * Eger istenilen oda girilen kullanici tarafindan booking yapılmıssa
         * islem gerceklestirilir.
         */
        if( hotel.getRoom(getRoomNo()-1).equalsIgnoreCase("Booked") ) {
            hotel.setRoom(getRoomNo() - 1, "Empty");
            hotel.setName(getRoomNo() - 1, "*");
            setProcess("Canceled");
            showTheRooms();
            return true;
        }
        else {
            System.out.println("Invalid operation.");
            showTheRooms();
            return false;
        }
    }

    /**
     * Resepsiyonist tarafindan checkin islemi yapmak icin kullanilir.
     * @return İslem basariliysa true dondurur, degilse false.
     */
    public boolean checkIn() {
        System.out.println("Which room you want to check in?");
        System.out.println("Numbers of rooms: ");
        showTheRooms();
        /**
         * Kullanicidan oda numarasi alinir.
         */
        setRoomNo(takeRoomNo());
        /**
         * Eger gecerli oda check-in edilmemisse, islem gerceklestirilir ve odalar gosterilir.
          */
        if( hotel.getRoom(getRoomNo()-1).equalsIgnoreCase("Check-in") == false ) {
            hotel.setRoom(getRoomNo() - 1, "Check-in");
            hotel.setName(getRoomNo() - 1, getName());
            setProcess("Check-in");
            showTheRooms();
            return true;
        }
        else {
            System.out.println("Invalid operation.");
            showTheRooms();
            return false;
        }
    }

    /**
     * Resepsiyonist tarafından checkout islemi yapmak icin kullanilir.
     * @return Eger islem basariliysa true dondurulur, degilse false.
     */
    public boolean checkOut() {
        System.out.println("Which room you want to check out?");
        System.out.println("Numbers of rooms: ");
        showTheRooms();
        /**
         * Kullanicidan oda numarasi alinir.
         */
        setRoomNo(takeRoomNo());
        /**
         * Eger gecerli oda check-in edilmisse, islem gerceklestirilir ve odalar gosterilir.
         */
        if( hotel.getRoom(getRoomNo()-1).equalsIgnoreCase("Check-in") ) {
            hotel.setRoom(getRoomNo() - 1, "Empty");
            hotel.setName(getRoomNo() - 1, getName());
            setProcess("Check-out");
            showTheRooms();
            return true;
        }
        else {
            System.out.println("Invalid operation.");
            showTheRooms();
            return false;
        }
    }

}
