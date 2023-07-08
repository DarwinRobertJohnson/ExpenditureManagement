import java.lang.*;
import java.sql.*;

public class dbConnect{

    Connection con;
    PreparedStatement writerStatement;

    public dbConnect() throws Exception{
        Class.forName("org.sqlite.JDBC");
        con=DriverManager.getConnection("jdbc:sqlite:../database/Expenditure");
        String query="insert into "+"_08_07_2023" +" values(?,?)";
        writerStatement=con.prepareStatement(query);   

    }
}