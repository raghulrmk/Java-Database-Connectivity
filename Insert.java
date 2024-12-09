
import java.util.*;
import java.sql.*;

public class Insert {
    public static void main(String a[]) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/testing";
        String root_name="root";
        String password="password_19";
        Connection connection=DriverManager.getConnection(url,root_name,password);
        String query="Insert into Product_sold(Total_productions,Sold) values(?,?);";
        PreparedStatement statement=connection.prepareStatement(query);
        int total=200;
        statement.setInt(1,200); statement.setInt(2,70);
        int count=statement.executeUpdate();
        System.out.println("number of rows affected: "+count);
        statement.close();
        connection.close();
    }
}
