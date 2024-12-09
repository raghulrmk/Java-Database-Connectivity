
import java.util.*;
import java.sql.*;
public class Trigger {

    public static  void main(String a[]) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/testing";
        String root_name="root";
        String password="password_19";

        String sql= """
                
                Create Trigger update_Product_unsold
                Before Update on Product_sold
                for each row
                Begin
                
                  IF(OLD.Sold!=NEW.Sold or OLD.Total_productions!=NEW.Total_productions) THEN
                       Update Product_unsold set
                       Total_productions=NEW.Total_productions,
                       Unsold=NEW.Total_productions-NEW.Sold
                       Where Product_id=NEW.Product_id;
                  END IF;
                End;
               
                """;
        try {
            Connection connection = DriverManager.getConnection(url, root_name, password);
            Statement cst = connection.createStatement();
            cst.executeUpdate(sql);
            System.out.println("Trigger is executed successfully");
            cst.close();
            connection.close();
        } catch (SQLException s) {
            System.out.println(s);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
