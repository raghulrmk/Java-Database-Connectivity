
import java.util.*;
import java.sql.*;

import static java.sql.Types.VARCHAR;

public class StoredProceedures {

    public static void main(String a[]) throws Exception{

        StoredProcedure6();

    }

    public static void StoredProcedure1() throws Exception {
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

    public static void StoredProcedure2() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/testing";
        String root_name= "root";
        String password="password_19";
        String query= """
        
         Create Procedure get_CustomerById(IN ids int)
         Begin
            Select * from Product_unsold where Product_id=ids;
         End;
      
        """;
        Connection connection=DriverManager.getConnection(url,root_name,password);
        Statement st=connection.createStatement();
        st.executeUpdate(query);
        System.out.println("Stored Procedure to display unsold product details is created successfully");
        st.close(); connection.close();
    }
    public static void StoredProcedure3() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/testing";
        String root_name= "root";
        String password="password_19";
        String query= "{Call get_CustomerById(?)}";
        Connection connection=DriverManager.getConnection(url,root_name,password);
        CallableStatement cst= connection.prepareCall(query);
        cst.setInt(1,4);
        ResultSet result=cst.executeQuery();
        while(result.next()) {
            System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3));
        }

        cst.close(); connection.close();
    }
    public static void StoredProcedure4() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/testing";
        String root_name = "root";
        String password = "password_19";
        String query = """
                
                 Create Procedure get_CustomerNameById(IN ids int, Out name varchar(30))
                 Begin
                    Select first_name from customers where customer_id=ids into name;
                 End;
                
                """;
        Connection connection = DriverManager.getConnection(url, root_name, password);
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        System.out.println("Stored Procedure to display unsold product details is created successfully");
        st.close();
        connection.close();
    }
    public static void StoredProcedure5() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/testing";
        String root_name= "root";
        String password="password_19";
        String query= "{Call get_CustomerNameById(?,?)}";
        Connection connection=DriverManager.getConnection(url,root_name,password);
        CallableStatement cst= connection.prepareCall(query);
        cst.setInt(1,4);
        cst.registerOutParameter(2,Types.VARCHAR);
        cst.execute();
        System.out.println("The first name of employee with id 4: "+cst.getString(2));
        cst.close(); connection.close();
    }
    public static void StoredProcedure6() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/testing";
        String root_name= "root";
        String password="password_19";
        String query= "Create Index Product_unsold_Productions on Product_unsold(Total_productions);";
        Connection connection=DriverManager.getConnection(url,root_name,password);
        Statement st=connection.createStatement();
        st.executeUpdate(query);
        System.out.println("Index created for productions column in Product_unsold");
        st.close(); connection.close();
    }
}
