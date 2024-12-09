
import java.sql.*;
import java.util.*;

class Main {

    public static void main(String a[]) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/mydb";
        String root_name="root";
        String password="password_19";
        String query="Select * from anime";

        try {
            Connection connection=DriverManager.getConnection(url,root_name,password);
            Statement st= connection.createStatement();
            ResultSet result=st.executeQuery(query);
            while(result.next()) {
                System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+result.getString(4));

            }
            st.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}