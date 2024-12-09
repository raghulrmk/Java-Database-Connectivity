
import java.util.*;
import java.sql.*;

public class MultipleUpdates {

    public static void main(String a[]) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/testing";
        String root_name="root";
        String password="password_19";
        Connection connection=DriverManager.getConnection(url,root_name,password);
        Statement st=connection.createStatement();

        st.addBatch("Update Product_sold set Total_productions=150 where Product_id=2");
        st.addBatch("Update Product_sold set Total_productions=140 where Product_id=5");
        int[] arr=st.executeBatch();
        System.out.println("Updated the details successfully");
        st.close(); connection.close();
    }
}
