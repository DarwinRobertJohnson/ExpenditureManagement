import java.lang.*;
import java.sql.*;

public class dbConnect{

    Connection con;
    PreparedStatement writerStatement;
    String query;

    public dbConnect() throws Exception{
        Class.forName("org.sqlite.JDBC");
        con=DriverManager.getConnection("jdbc:sqlite:../database/Expenditure");
        // query="insert into "+"_08_07_2023" +" values(?,?)";
        // writerStatement=con.prepareStatement(query);   

    }

    public void updateDate(String date) throws Exception{
        query="insert into "+date+" values(?,?)";
        writerStatement=con.prepareStatement(query);
    }
}