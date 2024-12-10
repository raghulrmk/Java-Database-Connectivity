package BusReservationDatabase;

import java.util.*;
import java.util.concurrent.*;
public class Main {

    public static void main(String a[]) throws Exception {

        Scanner in=new Scanner(System.in);

        while(true) {
            System.out.println("Options are generating.....\n");
            Thread.sleep(2000);
            printOptions();
            System.out.print("Enter the option: ");
            int opt=in.nextInt(); System.out.print("\n");
            switch(opt) {
                case 1:
                    busOptions();
                    System.out.print("Enter the options: ");
                    int ch=in.nextInt();
                    System.out.print("\n");
                    TicketBooking tb=new TicketBooking();
                    try{tb.ticketBooking(ch);} catch(Exception e) {System.out.println(e);}
                    break;

                case 2:
                    System.out.println("Cancelling the Booking Process...."); Thread.sleep(2000);
                    System.out.println("Booking Process Cancelled");
                    break;
                default:
                    System.out.println("Enter the valid options");
                    break;
            }
            if(opt==2) break;

        }

    }

    private static void printOptions() {
        System.out.println("----------------------\n");
        System.out.println("Choose a option to fullfill your request\n");
        String options="1.Want to Book Tickets?\n" +
                "2.Want to exit from the Ticket Booking Portal?\n";
        System.out.println(options);
        System.out.println("----------------------\n");
    }
    private static void busOptions() {
        System.out.println("----------------------\n");
        System.out.println("Choose your desired bus\n");
        String options="1.Book Bus 1\n" +
                "2.Book Bus 2\n" +
                "3.Book Bus 3\n";
        System.out.println(options);
        System.out.println("----------------------\n");
    }
}
