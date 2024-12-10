package BusReservationDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.sql.*;
import java.time.LocalDate;

public class TicketBooking {
    private int ticket_id;
    private static Set<Integer> ticketIdGenerator=new HashSet<>();
    private String name;
    private int age;
    private int tickets;
    private boolean preferences;
    private java.sql.Date booking_date;
    private int bus_No;
    int[] init=new int[]{10,10,10};
    Scanner in=new Scanner(System.in);

    private void getPassengerDetails(int num) throws Exception  {

        System.out.print("Enter your sweet name: ");
        this.name=in.nextLine();
        System.out.print("\nEnter your age: ");
        this.age=in.nextInt();
        System.out.print("\nEnter Number of tickets you needed: ");
        this.tickets=in.nextInt();
        System.out.print("\nTravelling facilities option loading.........");
        Thread.sleep(2000);
        printPreferences();
        System.out.print("Enter you preferences sir/madam: ");
        int ch=in.nextInt();
        if(ch==1) {this.preferences=true;} else {this.preferences=false;}
        System.out.print("\nEnter your booking date(YYYY-MM-DD): ");
        in.nextLine();
        String dt=in.nextLine();
        this.booking_date=java.sql.Date.valueOf(dt);
        this.bus_No=num;

    }
    public  void ticketBooking(int num) throws Exception {
        getPassengerDetails(num);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/testing";
        String root_name="root";
        String password="password_19";
        Connection connection=DriverManager.getConnection(url,root_name,password);
        String query="Select Booking_Date,sum(Total_Tickets) from Ticket_Booking Where Booking_Date=? and Bus_no=? Group By Booking_Date;";
        PreparedStatement pst= connection.prepareStatement(query);
        pst.setDate(1,this.booking_date);
        pst.setInt(2,this.bus_No);
        ResultSet result=pst.executeQuery();

        if( verifySeatAvailability(result)) {
            registerInDataBase();
        } else {
            System.out.println("\n Dont worry if you dont get your desired seat. Please try with other buses or try to change the date and book");

        }


    }
    private void registerInDataBase() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/testing";
        String root_name="root";
        String password="password_19";
        Connection connection=DriverManager.getConnection(url,root_name,password);
        String query="Insert into Ticket_Booking values(?,?,?,?,?,?,?)";
        PreparedStatement pst= connection.prepareStatement(query);
        this.ticket_id=getTicketId();
        pst.setInt(1,this.ticket_id);
        pst.setString(2,this.name);
        pst.setInt(3,this.age);
        pst.setDate(4,this.booking_date);
        pst.setInt(5,this.bus_No);
        pst.setInt(6,this.tickets);
        if(preferences) {pst.setString(7,"A/C");} else {pst.setString(7,"Normal Sleeper");}
        int count=pst.executeUpdate();
        System.out.println("\nRegistering into our database.....");
        Thread.sleep(1000);
        System.out.println("Registered Successfully");
        generateTheBill();
    }
    private void generateTheBill() {
        System.out.print("\n--------------------\n");
        int fair=0;
        String pref="";
        if(this.preferences) {fair=this.tickets*400; pref="A/c";}
        if(!this.preferences) {fair=this.tickets*200; pref="Normal Sleeper";}
        System.out.println("Generating your Ticket\n");
        String options="Name: "+this.name+"\nTicket id: "+this.ticket_id+"\n" +
                "Age: "+this.age+"\nTotal tickets: "+this.tickets+"\n" +
                "Booking Date: "+this.booking_date+"\n" +"Journey Fair: "+fair+"\nPrefernces: "+pref+"\n"+
                "************HAPPY JOURNEY************\n";
        System.out.print(options);
        System.out.print("\n--------------------\n");
    }
    private boolean verifySeatAvailability(ResultSet result) throws Exception {
        int bookedTickets=0;
        if(result.next()) {
            bookedTickets=result.getInt(2);
            if(this.init[this.bus_No-1]-bookedTickets<this.tickets) return false;
        }
        return true;

    }
    private  void printPreferences() {
        System.out.print("\n--------------------\n");
        System.out.println("Choose your preferences:\n");
        String options="1.AC\n" +
                "2.Normal Sleeper";
        System.out.print(options);
        System.out.print("\n--------------------\n");
    }

   private int getTicketId() {
        Random random=new Random();
        int ticketId=0;
        while(true) {
            ticketId=random.nextInt(9000)+1000;
            if(!ticketIdGenerator.contains(ticketId)) {
                ticketIdGenerator.add(ticketId);
                return ticketId;
            }
        }

   }

}
