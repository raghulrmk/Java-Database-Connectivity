
import java.util.*;
import java.sql.*;

public class Procedures {

    public static void main(String a[]) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/testing";
        String root_name="root";
        String password="password_19";
        String query="Call get_soldProducts()";
        Connection connection=DriverManager.getConnection(url,root_name,password);
        Statement st=connection.createStatement();
        ResultSet result=st.executeQuery(query);
        System.out.println("Product id"+" "+"Productions"+" "+"Sold");
        while(result.next()) {
            System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3));
        }
        st.close();
        connection.close();
    }
}
