import hotelmanagement.*;

/**
 * @author Islam Goktan Selcuk
 * Student Number: 141044071
 * */

import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String args[]) {
        try {
            /**
             * Main testlerin çalışması için gereken kod blogu
             * Scanner ile File degiskenlerini comment icerisine alırsanız
             * programi kendi inputlarinizla test edebilirsiniz.
             */
            File file = new File("test1.txt");
            //File file = new File("test2.txt");
            //File file = new File("test3.txt");
            //File file = new File("test4.txt");
            //File file = new File("test5.txt");
            //File file = new File("test6.txt");
            //File file = new File("test7.txt");
            MyHotel.scan = new Scanner(file);
            ////////////////////////////////////


            MyHotel hotel = new MyHotel();

            //Otel icerisindeki tüm verileri siler. Dogru test sonucuna ulasabilmek icin kullanilir.
            //!!!!!!!!!!!!!!!!!!!!!!!!!
            //hotel.cleanHotelMembers();

            boolean play = true;
            // Dosyadan verileri programa yukler.
            hotel.syncHotelMembers();
            while(play) {
                // Kullanicidan kullanici turu ve ismi alinir.
                SystemUser user = hotel.logInToSystem(hotel);
                // Kullanicinin yapabilecegi islemler gosterilir.
                user.showOptions();
                // Programin sonlandirilip sonlandirilmayacagi sorulur.
                play = hotel.programLoop();
            }
            // Tum islemler yapildiktan sonra otelin sahip oldugu veriler dosyaya kaydedilir.
            hotel.saveHotelMembers();
        } catch (IOException e) {
            System.out.println(e);
        }


    }
}
