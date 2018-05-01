package hotelmanagement;

/*
 * @author Islam Goktan Selcuk
 * Student Number: 141044071
 * */

import java.util.Scanner;

public class Guest extends SystemUser {

    /**
     * Alinan parametrelere gore bir misafir objesi olusturulur.
     * @param name Misafirin ismi.
     * @param hotel Misafirin kaydinin tutuldugu hotel objesi
     */
    public Guest(String name, MyHotel hotel) {
        setName(name);
        this.hotel = hotel;
        setUserType("Guest");
    }

    @Override
    public void showOptions() {
        boolean result = false;
        boolean invalidInput = true;
        while(invalidInput) {
            System.out.println("What do you want to do?");
            System.out.println("'1' for booking.");
            System.out.println("'2' for cancel the booking.");
            //Scanner scan = new Scanner(System.in);
            String choice = MyHotel.scan.nextLine();
            if (choice.equals("1")) {
                result = bookingTheRoom();
                invalidInput = false;
            }
            else if (choice.equals("2")) {
                result = cancelReservation();
                invalidInput = false;
            }
            else
                System.out.println("Invalid input. Try again.");
        }

        if(result)
            writeTheFile();
    }

    @Override
    public boolean cancelReservation() {
        System.out.println("Which room you want to cancel reservation?");
        System.out.println("Numbers of rooms: ");
        showTheRooms();
        int tempRoomNo = takeRoomNo();

        if(hotel.getName(tempRoomNo-1).equals(getName()) == false) {
            System.out.println("Your name is wrong. You can't cancel reservation of others.");
            return false;
        }
        else {
            setRoomNo(tempRoomNo);
            if(hotel.getRoom(getRoomNo()-1).equalsIgnoreCase("Booked") &&
                    hotel.getName(getRoomNo() - 1).equals(getName())) {
                hotel.setRoom(getRoomNo() - 1, "Empty");
                hotel.setName(getRoomNo() - 1, "*");
                setProcess("Canceled");
            }
            else
                System.out.println("You do not have permission to do this.");
            showTheRooms();
            return true;
        }
    }

}
