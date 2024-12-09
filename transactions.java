
import java.sql.*;
import java.util.*;


public class transactions {

    public static void main(String a[]) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/testing";
        String root_name="root";
        String password="password_19";
        Connection connection=DriverManager.getConnection(url,root_name,password);
        Statement st=connection.createStatement();
        ResultSet result=st.executeQuery("Select * from transactions trans inner join customers cst on trans.customer_id=cst.customer_id order by trans.amount DESC");
        while(result.next()) {
            System.out.println(result.getString(1)+","+result.getString(2)+","+result.getString(3)+","+result.getString(4)+","
            +result.getString(5)+" "+result.getString(6));
        }
        st.close();connection.close();
    }
}
