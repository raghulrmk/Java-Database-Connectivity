
import java.util.*;
import java.sql.*;

public class StoredProceedures {

    public static void main(String a[]) throws Exception{

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/testing";
        String root_name= "root";
        String password="password_19";
        String query= """
        
         Create Procedure get_ProductUnsold()
         Begin
            Select * from Product_unsold;
         End;
      
        """;
        Connection connection=DriverManager.getConnection(url,root_name,password);
        Statement st=connection.createStatement();
        st.executeUpdate(query);
        System.out.println("Stored Procedure to display unsold product details is created successfully");
        st.close(); connection.close();
    }
}
