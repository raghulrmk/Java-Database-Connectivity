
import java.util.*;
import java.sql.*;

public class Database {

    public static void main(String a[]) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/mydb";
        String root_name="root";
        String password="password_19";
        Connection con=DriverManager.getConnection(url,root_name,password);
        Statement st=con.createStatement();
        ResultSet result=st.executeQuery("Select * from department");
        while(result.next()) {
            System.out.println(result.getString(1)+" "+result.getString(2));
        }
        st.close();con.close();
    }
}
